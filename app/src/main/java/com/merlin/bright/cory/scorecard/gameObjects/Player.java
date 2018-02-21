package com.merlin.bright.cory.scorecard.gameObjects;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by cory on 12/12/17.
 */

@Entity(foreignKeys = @ForeignKey(
        entity = Game.class,
        parentColumns = "id",
        childColumns = "gameId",
        onDelete = CASCADE
))
public class Player {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int gameId;
    private String mPlayerName;
    private int mScore = 0;
    private String teamName;

    public Player(String playerName, int gameId) {
        mPlayerName = playerName;
        this.gameId = gameId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerName() {
        return mPlayerName;
    }

    public void setPlayerName(String playerName) {
        mPlayerName = playerName;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        mScore = score;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int id){gameId = id;}
}
