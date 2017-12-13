package com.merlin.bright.cory.scorecard;

import android.widget.ArrayAdapter;

/**
 * Created by cory on 12/12/17.
 */

class Team {
    private ArrayAdapter<Player> mPlayer;

    public Team(ArrayAdapter<Player> player) {
        mPlayer = player;
    }

    public ArrayAdapter<Player> getPlayer() {
        return mPlayer;
    }

    public void setPlayer(ArrayAdapter<Player> player) {
        mPlayer = player;
    }

    public void addPlayer (Player player){
        mPlayer.add(player);
    }
}
