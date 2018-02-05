package com.merlin.bright.cory.scorecard.gameObjects;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by cory on 12/12/17.
 */

@Entity
public class Player {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String mPlayerName;
    private int mScore;

    public Player(String playerName, int score) {
        mPlayerName = playerName;
        mScore = score;
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
}
