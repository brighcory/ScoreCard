package com.merlin.bright.cory.scorecard.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
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
    LiveData<List<Player>> getAllPlayers();


    @Delete
    void delete(Player player);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Player... players);

    @Query("SELECT * FROM player WHERE gameId IS :id")
    List<Player> getGamePlayers(Integer... id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Player... players);

}
