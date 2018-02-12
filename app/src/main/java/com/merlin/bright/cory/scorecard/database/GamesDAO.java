package com.merlin.bright.cory.scorecard.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.merlin.bright.cory.scorecard.gameObjects.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coryb on 1/30/2018.
 */
@Dao
public interface GamesDAO {
    @Query("SELECT * FROM game")
    LiveData<List<Game>> getAllGames();

    @Query("SELECT * FROM game WHERE id = :id")
    Game getGame(int id);
    @Insert
    void insert(Game... games);

    @Update
    void update(Game... games);

    @Delete
    void delete(Game... games);

    @Insert
    void insert(ArrayList<Game> mGames);
}
