package com.merlin.bright.cory.scorecard.gameObjects;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.merlin.bright.cory.scorecard.ui.MainActivity;

import java.util.ArrayList;

/**
 * Created by cory on 12/6/17.
 */
@Entity
public class Game {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String mGameName;
    private String mWinner;
    private boolean mWinnerHighest = true;
    private boolean mTeam = false;
    @Ignore
    private ArrayList<Player> mPlayers = new ArrayList<>();

    public Game(String gameName, boolean winnerHighest, boolean team) {
        mGameName = gameName;
        mWinnerHighest = winnerHighest;
        mTeam = team;
        for (Player player: MainActivity.mPlayers){
            if (player.getGameId()==id){
                mPlayers.add(player);
            }
        }
    }

    public void addPlayerToGame() {
        Player player = new Player("Player", getId());
        mPlayers.add(player);
    }

    public String getWinner() {
        return getWinningPlayer().getPlayerName();
    }

    private Player getWinningPlayer() {
        if (mPlayers.isEmpty()) {
            return new Player("New Game", getId());
        }
        Player winner = mPlayers.get(0);
        if (mWinnerHighest) {
            for (Player player : mPlayers) {
                winner = (winner.getScore() > player.getScore()) ? winner : player;
            }
        } else {
            for (Player player : mPlayers) {
                winner = (winner.getScore() < player.getScore()) ? winner : player;
            }
        }
        return winner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGameName() {
        return mGameName;
    }

    public void setGameName(String gameName) {
        mGameName = gameName;
    }

    public void setWinner(String winner) {
        mWinner = winner;
    }

    public boolean isWinnerHighest() {
        return mWinnerHighest;
    }

    public void setWinnerHighest(boolean winnerHighest) {
        mWinnerHighest = winnerHighest;
    }

    public boolean isTeam() {
        return mTeam;
    }

    public void setTeam(boolean team) {
        mTeam = team;
    }

    public ArrayList<Player> getPlayers() {
        return mPlayers;
    }

    public void setPlayers(ArrayList<Player> players) {
        mPlayers = players;
    }

    public int getWinningScore() {
        return getWinningPlayer().getScore();
    }
}
