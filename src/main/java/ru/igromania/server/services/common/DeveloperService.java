package ru.igromania.server.services.common;

import ru.igromania.server.persistence.domain.Developer;

import java.util.List;

public interface DeveloperService {

    void save(Developer developer);

    List<Developer> getAll();

    Developer findByName(String name);

}
