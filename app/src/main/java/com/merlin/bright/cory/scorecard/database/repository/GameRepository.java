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
        mPlayers = mPlayersDAO.getAllPlayers();
    }

    LiveData<List<Game>> getGames(){return mGames;}

    void insert(final Game game){
        new insertAT(mGamesDAO);
    }

    private class insertAT extends AsyncTask<Game, Void, Void>{
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
}
