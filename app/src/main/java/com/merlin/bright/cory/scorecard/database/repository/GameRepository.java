package com.merlin.bright.cory.scorecard.database.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.merlin.bright.cory.scorecard.database.GameDatabase;
import com.merlin.bright.cory.scorecard.database.GamesDAO;
import com.merlin.bright.cory.scorecard.database.PlayersDAO;
import com.merlin.bright.cory.scorecard.gameObjects.Game;
import com.merlin.bright.cory.scorecard.gameObjects.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coryb on 2/2/2018.
 */

class GameRepository {
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
    LiveData<List<Player>> getPlayers(){return mPlayers;}

    void insert(final Game game){
        new insertAT(mGamesDAO).execute(game);
    }

    void delete(Game game) {
        new deleteAT(mGamesDAO).execute(game);
    }

    void update(Game game) {
        new updateAT(mGamesDAO).execute(game);
    }

    void insert(Player player) {
        new insertPlayerAT(mPlayersDAO).execute(player);
    }

    void insert(ArrayList<Player> players) {
        for (Player player : players) {
            new insertPlayerAT(mPlayersDAO).execute(player);
        }
    }

    void update(Player player) {
        new updatePlayerAT(mPlayersDAO).execute(player);
    }

    private static class insertAT extends AsyncTask<Game, Void, Void>{
        GamesDAO mGamesDAO;
        insertAT(GamesDAO dao) {
            mGamesDAO = dao;
        }

        @Override
        protected Void doInBackground(Game... games) {
            mGamesDAO.insert(games);
            return null;
        }
    }
    private static class insertPlayerAT extends AsyncTask<Player, Void, Void>{
        PlayersDAO mDAO;
        insertPlayerAT(PlayersDAO dao) {
            mDAO = dao;
        }

        @Override
        protected Void doInBackground(Player... players) {
            mDAO.insert(players);
            return null;
        }
    }
    private static class deleteAT extends AsyncTask<Game, Void, Void>{
        GamesDAO mGamesDAO;
        deleteAT(GamesDAO dao) {
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
        updateAT(GamesDAO dao) {
            mGamesDAO = dao;
        }

        @Override
        protected Void doInBackground(Game... games) {
            mGamesDAO.update(games);
            return null;
        }
    }
    private static class updatePlayerAT extends AsyncTask<Player, Void, Void>{
        PlayersDAO mGamesDAO;
        updatePlayerAT(PlayersDAO dao) {
            mGamesDAO = dao;
        }

        @Override
        protected Void doInBackground(Player... players) {
            mGamesDAO.update(players);
            return null;
        }
    }
}
