package ru.igromania.server.parsers.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.igromania.server.parsers.IgromaniaGamesParser;
import ru.igromania.server.persistence.dto.GameDto;

import java.util.List;
import java.util.concurrent.Callable;

public class GamesParserTask implements Callable<List<GameDto>> {


    private int pageNumber;

    public GamesParserTask(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public List<GameDto> call() throws Exception {
        List<GameDto> games = new IgromaniaGamesParser().getGamesInfoFromPageWithNumber(pageNumber);
        return games;
    }
}
