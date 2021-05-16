package ru.igromania.server.services.common;

import ru.igromania.server.persistence.domain.Game;

import java.util.List;

public interface GameService {

    void save(Game game);

    List<Game> getAll();

    Game findByName(String name);

   Game findById(Long id);

}
