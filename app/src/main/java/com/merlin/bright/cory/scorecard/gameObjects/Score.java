package com.merlin.bright.cory.scorecard.gameObjects;

/**
 * Created by cory on 12/12/17.
 */

public class Score {
    private int mScore;
    private Player mPlayer;
    private Team mTeam;

    public Score(int score, Player player, Team team) {
        mScore = score;
        mPlayer = player;
        mTeam = team;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        mScore = score;
    }

    public Player getPlayer() {
        return mPlayer;
    }

    public void setPlayer(Player player) {
        mPlayer = player;
    }

    public Team getTeam() {
        return mTeam;
    }

    public void setTeam(Team team) {
        mTeam = team;
    }
}
