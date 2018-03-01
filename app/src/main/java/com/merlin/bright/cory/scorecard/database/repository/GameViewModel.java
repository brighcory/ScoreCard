package com.merlin.bright.cory.scorecard.database.repository;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.merlin.bright.cory.scorecard.gameObjects.Game;
import com.merlin.bright.cory.scorecard.gameObjects.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coryb on 2/2/2018.
 */

public class GameViewModel extends AndroidViewModel {
    private GameRepository mRepository;
    private LiveData<List<Game>> mGames;
    private LiveData<List<Player>> mPlayers;

    public GameViewModel(@NonNull Application application) {
        super(application);
        mRepository = new GameRepository(application);
        mGames = mRepository.getGames();
        mPlayers = mRepository.getPlayers();
    }

    public LiveData<List<Game>> getGames() {
        return mGames;
    }

    public LiveData<List<Player>> getPlayers() {
        return mPlayers;
    }

    public void insert(Game game) {
        mRepository.insert(game);
    }

    public void deleteGame(Game game) {
        mRepository.delete(game);
    }

    public void updateGame(Game game) {
        mRepository.update(game);
    }

    public void insert(Player player) {
        mRepository.insert(player);
    }

    public void insert(ArrayList<Player> players) {
        mRepository.insert(players);
    }

    public void deletePlayer(Player player) {
        mRepository.deletePlayer(player);
    }
}
