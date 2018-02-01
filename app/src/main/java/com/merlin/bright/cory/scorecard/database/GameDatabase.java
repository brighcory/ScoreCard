package com.merlin.bright.cory.scorecard.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.merlin.bright.cory.scorecard.gameObjects.Game;
import com.merlin.bright.cory.scorecard.gameObjects.Player;

/**
 * Created by coryb on 1/30/2018.
 */
@Database(entities = {Game.class, Player.class}, version = 1)
public abstract class GameDatabase extends RoomDatabase {
    public abstract GamesDAO daoGames();
    public abstract PlayersDAO daoPlayers();

    private static GameDatabase INSTANCE;

    static GameDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (GameDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            GameDatabase.class, "games").build();
                }
            }
        }
        return INSTANCE;
    }

}
