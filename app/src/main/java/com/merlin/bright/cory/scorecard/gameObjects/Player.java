package com.merlin.bright.cory.scorecard.gameObjects;

/**
 * Created by cory on 12/12/17.
 */

public class Player {
    private String mPlayerName;

    public Player(String playerName) {
        mPlayerName = playerName;
    }

    public String getPlayerName() {
        return mPlayerName;
    }

    public void setPlayerName(String playerName) {
        mPlayerName = playerName;
    }
}
