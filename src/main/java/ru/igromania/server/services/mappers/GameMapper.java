package ru.igromania.server.services.mappers;


import org.springframework.stereotype.Component;
import ru.igromania.server.persistence.domain.Developer;
import ru.igromania.server.persistence.domain.Game;
import ru.igromania.server.persistence.domain.Genre;
import ru.igromania.server.persistence.domain.Platform;
import ru.igromania.server.persistence.dto.GameDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class GameMapper {


    public Game mapToEntity(GameDto gameDto) {

        Game game = new Game();

        game.setOriginalName(gameDto.getOriginalName());
        game.setDescription(gameDto.getDescription());
        game.setGamesSeries(gameDto.getGamesSeries());
        game.setImageLink(gameDto.getImageLink());
        game.setLink(gameDto.getLink());
        game.setMetacriticRating(gameDto.getMetacriticRating());
        game.setReleaseDate(gameDto.getReleaseDate());

        return game;

    }

    public GameDto mapToDto(Game game){

        GameDto gameDto = new GameDto();

        gameDto.setId(game.getId());
        gameDto.setOriginalName(game.getOriginalName());
        gameDto.setDescription(game.getDescription());
        gameDto.setImageLink(game.getImageLink());
        gameDto.setMetacriticRating(game.getMetacriticRating());
        gameDto.setLink(game.getLink());
        gameDto.setReleaseDate(game.getReleaseDate());

        List<String> developersForDto = new ArrayList<>();

        Set<Developer> developers = game.getDevelopers();
        for(Developer developer : developers){
            developersForDto.add(developer.getName());
        }

        gameDto.setDevelopers(developersForDto);

        List<String> genresForDto = new ArrayList<>();

        Set<Genre> genres = game.getGenres();
        for(Genre genre : genres){
           genresForDto.add(genre.getName());
        }

        gameDto.setGenres(genresForDto);

        List<String> platformsForDto = new ArrayList<>();

        Set<Platform> platforms = game.getPlatforms();
        for(Platform platform : platforms){
            platformsForDto.add(platform.getName());
        }

        gameDto.setPlatforms(platformsForDto);

        return gameDto;
    }


}
