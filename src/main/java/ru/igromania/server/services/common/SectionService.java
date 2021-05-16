package ru.igromania.server.services.common;

import ru.igromania.server.persistence.domain.Section;

import java.util.List;

public interface SectionService {

    List<Section> getAll();

    void save(Section section);

    Section findByName(String name);

}
