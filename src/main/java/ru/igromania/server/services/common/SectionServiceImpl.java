package ru.igromania.server.services.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.igromania.server.persistence.dao.SectionRepository;
import ru.igromania.server.persistence.domain.Section;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SectionServiceImpl implements SectionService{

    @Autowired
    SectionRepository sectionRepository;

    @Transactional
    public List<Section> getAll() {
        return sectionRepository.findAll();
    }

    @Transactional
    public void save(Section section) {
        sectionRepository.save(section);
    }

    @Transactional
    public Section findByName(String name) {
        return sectionRepository.findByName(name);
    }

}
