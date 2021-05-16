package ru.igromania.server.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.igromania.server.persistence.domain.Platform;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, Long> {
    Platform findByName(String name);
}
