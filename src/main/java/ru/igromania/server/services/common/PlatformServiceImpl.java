package ru.igromania.server.services.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.igromania.server.persistence.dao.PlatformRepository;
import ru.igromania.server.persistence.domain.Platform;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PlatformServiceImpl implements PlatformService {

    @Autowired
    PlatformRepository platformRepository;

    @Transactional
    public void save(Platform platform) {
        platformRepository.save(platform);
    }

    @Transactional
    public List<Platform> getAll() {
        return platformRepository.findAll();
    }

    @Transactional
    public Platform findByName(String name) {
        return platformRepository.findByName(name);
    }

}
