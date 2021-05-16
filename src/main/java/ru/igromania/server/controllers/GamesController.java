package ru.igromania.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.igromania.server.persistence.dto.GameDto;
import ru.igromania.server.services.common.GameService;
import ru.igromania.server.services.mappers.GameMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GamesController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameMapper gameMapper;

    @GetMapping("/games")
    public List<GameDto> allGames() {
        return gameService.getAll().stream().map(game -> gameMapper.mapToDto(game)).collect(Collectors.toList());
    }

    @GetMapping("/games/{id}")
    public GameDto singleGame(@PathVariable Long id) {
        return gameMapper.mapToDto(gameService.findById(id));
    }

}
