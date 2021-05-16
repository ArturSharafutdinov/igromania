package ru.igromania.server.services.gamesDb;


import java.util.List;
import java.util.concurrent.ExecutionException;

public interface GamesDbService {

    void savePlatformToDb();

    void saveDeveloperToDb(List<String> developers);

    void saveGenreToDb();

    void saveGamesToDb(int firstPageNumber, int lastPageNumber) throws ExecutionException, InterruptedException;


}
