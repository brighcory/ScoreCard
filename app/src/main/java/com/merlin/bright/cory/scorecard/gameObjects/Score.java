package com.merlin.bright.cory.scorecard.gameObjects;

/**
 * Created by cory on 12/12/17.
 */

public class Score {
    private Game mGame;
    private int mScore;
    private Player mPlayer;

    public Score(int score, Player player, Game game) {
        mScore = score;
        mPlayer = player;
        mGame = game;
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

    public Game getGame() {
        return mGame;
    }

    public void setGame(Game game) {
        mGame = game;
    }
}
