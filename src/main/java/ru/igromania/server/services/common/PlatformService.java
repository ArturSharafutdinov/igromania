package ru.igromania.server.services.common;

import ru.igromania.server.persistence.domain.Platform;

import java.util.List;

public interface PlatformService {

    void save(Platform platform);

    List<Platform> getAll();

    Platform findByName(String name);

}
