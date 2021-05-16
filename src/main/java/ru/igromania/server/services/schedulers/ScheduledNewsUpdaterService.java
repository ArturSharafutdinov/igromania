package ru.igromania.server.services.schedulers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.igromania.server.services.newsDb.NewsDbService;

@Component
public class ScheduledNewsUpdaterService {

    @Autowired
    NewsDbService newsDbService;


    //  @Scheduled(fixedRate = 15000)
    public void updateViews() {
        newsDbService.updateAllArticlesViews();
    }
}