package com.merlin.bright.cory.scorecard.gameObjects;

/**
 * Created by cory on 12/6/17.
 */

public class Game {
    private String mGameName;
    private Player mWinner;
    private boolean mWinnerHighest = true;
    private boolean mTeam = false;
    private boolean mTimer = false;

    public Game(String gameName, boolean winnerHighest, boolean team, boolean timer) {
        mGameName = gameName;
        mWinnerHighest = winnerHighest;
        mTeam = team;
        mTimer = timer;
    }

    public Game(String gameName, boolean team, boolean timer) {
        mGameName = gameName;
        mTeam = team;
        mTimer = timer;
    }

    public Game(String gameName, boolean winnerHighest) {
        mGameName = gameName;
        mWinnerHighest = winnerHighest;
    }

    public Game(String gameName) {
        mGameName = gameName;
    }


    public String getGameName() {
        return mGameName;
    }

    public void setGameName(String gameName) {
        mGameName = gameName;
    }

    public boolean isTeam() {
        return mTeam;
    }

    public void setTeam(boolean team) {
        mTeam = team;
    }

    public boolean isTimer() {
        return mTimer;
    }

    public void setTimer(boolean timer) {
        mTimer = timer;
    }

    public Player getWinner() {
        //Todo calculate winner
        return mWinner;
    }

    public void setWinner(Player winner) {
        mWinner = winner;
    }

    public boolean isWinnerHighest() {
        return mWinnerHighest;
    }

    public void setWinnerHighest(boolean winnerHighest) {
        mWinnerHighest = winnerHighest;
    }
}
