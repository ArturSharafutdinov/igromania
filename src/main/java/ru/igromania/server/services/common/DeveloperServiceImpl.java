package ru.igromania.server.services.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.igromania.server.persistence.dao.DeveloperRepository;
import ru.igromania.server.persistence.domain.Developer;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    @Autowired
    DeveloperRepository developerRepository;

    @Transactional
    public void save(Developer developer) {
        developerRepository.save(developer);
    }

    @Transactional
    public List<Developer> getAll() {
        return developerRepository.findAll();
    }

    @Transactional
    public Developer findByName(String name) {
        return developerRepository.findByName(name);
    }

}
