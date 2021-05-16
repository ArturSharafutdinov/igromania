package ru.igromania.server.services.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.igromania.server.persistence.dao.GameRepository;
import ru.igromania.server.persistence.domain.Game;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    GameRepository gameRepository;

    @Transactional
    public void save(Game game) {
        gameRepository.save(game);
    }

    @Transactional
    public List<Game> getAll() {
        return gameRepository.findAll();
    }

    @Transactional
    public Game findById(Long id){
        return gameRepository.findById(id).orElse(null);
    }

    @Transactional
    public Game findByName(String name) {
        return gameRepository.findByOriginalName(name);
    }


}
