package com.merlin.bright.cory.scorecard.database;

import android.provider.BaseColumns;

/**
 * Created by cory on 12/12/17.
 */

public final class GameDBContentBuilder {
    public GameDBContentBuilder() {
    }

    public static class GameEntry implements BaseColumns{
        public static final String TABLE_NAME = "games";
        public static final String COLUMN_NAME_GAME_NAME = "gameName";
        public static final String COLUMN_NAME_TIMER = "hasTimer";
        public static final String COLUMN_NAME_WINNER = "winner";

    }

    public static class PlayerEntry implements BaseColumns{
        public static final String TABLE_NAME = "players";
        public static final String COLUMN_NAME_NAME = "name";
    }

    public static class TeamEntry implements BaseColumns{
        public static final String TABLE_NAME = "teams";
        public static final String COLUMN_NAME_NAME = "teamName";
    }

    public static class ScoreEntry implements BaseColumns{
        public static final String TABLE_NAME = "score";
        public static final String COLUMN_NAME_GAME = "game_id";
        public static final String COLUMN_NAME_PLAYER = "player_id";
        public static final String COLUMN_NAME_TEAM = "team_id";
    }
}
