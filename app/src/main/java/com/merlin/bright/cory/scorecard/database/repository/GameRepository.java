package com.merlin.bright.cory.scorecard.database.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.merlin.bright.cory.scorecard.database.GameDatabase;
import com.merlin.bright.cory.scorecard.database.GamesDAO;
import com.merlin.bright.cory.scorecard.database.PlayersDAO;
import com.merlin.bright.cory.scorecard.gameObjects.Game;
import com.merlin.bright.cory.scorecard.gameObjects.Player;

import java.util.List;

/**
 * Created by coryb on 2/2/2018.
 */

public class GameRepository {
    private GamesDAO mGamesDAO;
    private PlayersDAO mPlayersDAO;
    private LiveData<List<Game>> mGames;
    private LiveData<List<Player>> mPlayers;

    GameRepository(Application application) {
        GameDatabase database = GameDatabase.getDatabase(application);
        mGamesDAO = database.daoGames();
        mPlayersDAO = database.daoPlayers();
        mGames = mGamesDAO.getAllGames();
    }

    LiveData<List<Game>> getGames(){return mGames;}

    LiveData<List<Player>> getGamePlayers(int id){return mGamesDAO.getGamePlayers(id);}

    void insert(final Game game){
        new insertAT(mGamesDAO).execute(game);
    }

    public void delete(Game game) {
        new deleteAT(mGamesDAO).execute(game);
    }

    public void update(Game game) {
        new updateAT(mGamesDAO).execute(game);
    }

    private static class insertAT extends AsyncTask<Game, Void, Void>{
        GamesDAO mGamesDAO;
        public insertAT(GamesDAO dao) {
            mGamesDAO = dao;
        }

        @Override
        protected Void doInBackground(Game... games) {
            mGamesDAO.insert(games);
            return null;
        }
    }
    private static class deleteAT extends AsyncTask<Game, Void, Void>{
        GamesDAO mGamesDAO;
        public deleteAT(GamesDAO dao) {
            mGamesDAO = dao;
        }

        @Override
        protected Void doInBackground(Game... games) {
            mGamesDAO.delete(games);
            return null;
        }
    }
    private static class updateAT extends AsyncTask<Game, Void, Void>{
        GamesDAO mGamesDAO;
        public updateAT(GamesDAO dao) {
            mGamesDAO = dao;
        }

        @Override
        protected Void doInBackground(Game... games) {
            mGamesDAO.update(games);
            return null;
        }
    }
}
