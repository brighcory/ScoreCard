package com.merlin.bright.cory.scorecard.ui;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.merlin.bright.cory.scorecard.adapters.PlayerAdapter;
import com.merlin.bright.cory.scorecard.R;
import com.merlin.bright.cory.scorecard.gameObjects.Player;

import java.util.ArrayList;

public class PlayGameActivity extends ListActivity {
    private ArrayList<Player> mPlayers = new ArrayList<>();
    private PlayerAdapter playerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        mPlayers.add(new Player("Player 1"));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayers.add(new Player("Player"));
                playerAdapter.notifyDataSetChanged();
            }
        });

        playerAdapter = new PlayerAdapter(this, mPlayers);
        setListAdapter(playerAdapter);

    }

}
