package com.merlin.bright.cory.scorecard;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;

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
