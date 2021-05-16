package ru.igromania.server.services.schedulers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.igromania.server.services.newsDb.NewsDbService;
import ru.igromania.server.services.newsDb.NewsDbServiceImpl;

import java.io.IOException;
import java.text.ParseException;

@Component
public class ScheduledNewsService {

    @Autowired
    NewsDbService newsDbService;


    @Scheduled(fixedRate = 600000)
    public void retakeNews() {
        boolean isDropped = false;
        while (!isDropped)
            try {
                newsDbService.parse();
                isDropped = true;
                newsDbService.saveArticlesToDb();
            } catch (IOException e) {
            } catch (ParseException e) {
            }

    }
}
