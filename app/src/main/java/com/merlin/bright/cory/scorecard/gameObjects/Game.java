package com.merlin.bright.cory.scorecard.gameObjects;

import java.util.ArrayList;

/**
 * Created by cory on 12/6/17.
 */

public class Game {
    private String mGameName;
    private Player mWinner;
    private boolean mWinnerHighest = true;
    private boolean mTeam = false;
    private boolean mTimer = false;
    private ArrayList<Score> mScores = new ArrayList<>();
    private ArrayList<Player> mPlayers = new ArrayList<>();

    public Game(String gameName, boolean winnerHighest, boolean team, boolean timer) {
        mGameName = gameName;
        mWinnerHighest = winnerHighest;
        mTeam = team;
        mTimer = timer;
        Player player = new Player("New Player");
        mPlayers.add(player);
        mScores.add(new Score(0, player, this));
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
        Score scoreWinner =
                new Score(0, new Player("No Winner"), this);
        if (mWinnerHighest) {
            for (Score s : mScores) {
                scoreWinner = (s.getScore() > scoreWinner.getScore()) ? s : scoreWinner;
            }
        }else {
            for (Score s :mScores){
                scoreWinner = (s.getScore() < scoreWinner.getScore()) ? s : scoreWinner;
            }
        }
        return mWinner;
    }

    public void setWinner(Player winner) {
        mWinner = winner;
    }

    public void setWinnerHighest(boolean winnerHighest) {
        mWinnerHighest = winnerHighest;
    }
}
