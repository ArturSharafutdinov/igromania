package ru.igromania.server.services.gamesDb;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.igromania.server.parsers.GamesParserService;
import ru.igromania.server.persistence.domain.Developer;
import ru.igromania.server.persistence.domain.Game;
import ru.igromania.server.persistence.domain.Genre;
import ru.igromania.server.persistence.domain.Platform;
import ru.igromania.server.persistence.dto.GameDto;
import ru.igromania.server.services.common.DeveloperService;
import ru.igromania.server.services.common.GameService;
import ru.igromania.server.services.common.GenreService;
import ru.igromania.server.services.common.PlatformService;
import ru.igromania.server.services.mappers.GameMapper;
import ru.igromania.server.utils.Constraints;

import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutionException;

@Service
public class GamesDbServiceImpl implements GamesDbService {

    @Autowired
    private GameService gameService;

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private PlatformService platformService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private GamesParserService gamesParserService;

    @Autowired
    private GameMapper gameMapper;


    @Override
    public void savePlatformToDb() {
        for (String platform : Constraints.platforms) {
            platformService.save(new Platform(platform));
        }
    }

    @Override
    public void saveDeveloperToDb(List<String> developers) {
        for (String developer : developers) {
            if (developerService.findByName(developer) == null) {
                developerService.save(new Developer(developer));
            }
        }

    }

    @Override
    public void saveGenreToDb() {
        for (String genre : Constraints.genres) {
            genreService.save(new Genre(genre));
        }
    }

    @Override
    public void saveGamesToDb(int firstPageNumber, int lastPageNumber) throws ExecutionException, InterruptedException {

        List<GameDto> games = gamesParserService.getAllGamesFromPages(firstPageNumber, lastPageNumber);


        if (platformService.getAll().isEmpty() && genreService.getAll().isEmpty()) {
            saveGenreToDb();
            savePlatformToDb();
        }


        //Сразу с бд подгружаем, чтобы не отправлять запросы findBy
        List<Platform> platformsFromDb = platformService.getAll();
        List<Genre> genresFromDb = genreService.getAll();

        // Через итератор, чтобы опять не словить OutOfMemory : собрали инфу -> сохранили -> удалили
        ListIterator<GameDto> gamesIterator = games.listIterator();
        while (gamesIterator.hasNext()) {
            GameDto gameDto = gamesIterator.next();
            if (gameService.findByName(gameDto.getOriginalName()) == null) {
                Game game = gameMapper.mapToEntity(gameDto);
                saveDeveloperToDb(gameDto.getDevelopers());
                for (String platform : gameDto.getPlatforms()) {
                    Platform platformFromDb = platformsFromDb.stream().filter(x -> x.getName().equals(platform)).findFirst().orElse(null);
                    game.addPlatform(platformFromDb);
                }
                for (String developer : gameDto.getDevelopers()) {
                    Developer developerFromDb = developerService.findByName(developer);
                    game.addDeveloper(developerFromDb);
                }

                for (String genre : gameDto.getGenres()) {
                    Genre genreFromDb = genresFromDb.stream().filter(x -> x.getName().equals(genre)).findFirst().orElse(null);
                    game.addGenre(genreFromDb);
                }
                gameService.save(game);
            }
            gamesIterator.remove();
        }

    }
}
