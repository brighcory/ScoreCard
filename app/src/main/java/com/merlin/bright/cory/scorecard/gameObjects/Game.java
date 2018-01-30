package com.merlin.bright.cory.scorecard.gameObjects;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

/**
 * Created by cory on 12/6/17.
 */
@Entity
public class Game {
    @PrimaryKey (autoGenerate = true)
    private int id;

    private String mGameName;
    private Player mWinner;
    private boolean mWinnerHighest = true;
    private boolean mTeam = false;
    @Ignore
    private ArrayList<Player> mPlayers = new ArrayList<>();

    public Game(String gameName, boolean winnerHighest, boolean team) {
        mGameName = gameName;
        mWinnerHighest = winnerHighest;
        mTeam = team;
        Player player = new Player("New Player", 0);
        mPlayers.add(player);
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

    public Player getWinner() {
        mWinner = mPlayers.get(0);
        if (mWinnerHighest) {
            for (Player player : mPlayers) {
                mWinner = (mWinner.getScore() > player.getScore()) ? mWinner : player;
            }
        } else {
            for (Player player : mPlayers) {
                mWinner = (mWinner.getScore() < player.getScore()) ? mWinner : player;
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

    public ArrayList<Player> getPlayers() {
        return mPlayers;
    }
}
