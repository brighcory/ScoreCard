package com.merlin.bright.cory.scorecard.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.merlin.bright.cory.scorecard.gameObjects.Game;
import com.merlin.bright.cory.scorecard.gameObjects.Player;

/**
 * Created by coryb on 1/30/2018.
 */
@Database(entities = {Game.class, Player.class}, version = 1)
public abstract class GameDatabase extends RoomDatabase {
    public abstract GamesDAO daoGames();
    public abstract PlayersDAO daoPlayers();
}
