package com.merlin.bright.cory.scorecard.gameObjects;

/**
 * Created by cory on 12/12/17.
 */

public class Player {
    private String mPlayerName;
    private int mScore;

    public Player(String playerName, int score) {
        mPlayerName = playerName;
        mScore = score;
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
