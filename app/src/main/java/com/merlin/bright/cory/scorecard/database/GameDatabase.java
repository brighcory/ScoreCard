package com.merlin.bright.cory.scorecard.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

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

    public static GameDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (GameDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            GameDatabase.class, "Games")
                            .addCallback(sData)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sData = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDB(INSTANCE).execute();
        }
    };

    private static class PopulateDB extends AsyncTask<Void, Void, Void> {
        private final GamesDAO mGamesDAO;

        private PopulateDB(GameDatabase database) {
            mGamesDAO = database.daoGames();
        }


        @Override
        protected Void doInBackground(Void... voids) {


            return null;
        }
    }
}
