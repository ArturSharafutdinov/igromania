package ru.igromania.server.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.igromania.server.persistence.domain.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Game findByOriginalName(String name);


}
