package com.merlin.bright.cory.scorecard.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.merlin.bright.cory.scorecard.gameObjects.Player;

import java.util.List;

/**
 * Created by coryb on 1/30/2018.
 */
@Dao
public interface PlayersDAO {
    @Query("SELECT * FROM player")
    List<Player> getAllPlayers();

    @Insert
    void insert(Player player);

    @Update
    void update(Player player);

    @Delete
    void delete(Player player);
}
