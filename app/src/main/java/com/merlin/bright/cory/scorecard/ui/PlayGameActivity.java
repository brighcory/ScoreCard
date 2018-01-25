package com.merlin.bright.cory.scorecard.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.merlin.bright.cory.scorecard.R;
import com.merlin.bright.cory.scorecard.adapters.PlayAdapter;
import com.merlin.bright.cory.scorecard.gameObjects.Game;
import com.merlin.bright.cory.scorecard.gameObjects.Player;

import java.util.ArrayList;

public class PlayGameActivity extends Activity {

    private Game mGame;
    private ArrayList<Player> mPlayers = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private PlayAdapter mPlayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        Intent data = getIntent();
        int gameNumber = data.getIntExtra(MainActivity.NEW_GAME_INDEX, 0);
        mGame = MainActivity.mGames.get(gameNumber);
        mPlayers.add(new Player("Player 1", 0));

        mRecyclerView = findViewById(R.id.listOfPlayers);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPlayAdapter = new PlayAdapter(this, mPlayers);
        mRecyclerView.setAdapter(mPlayAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayers.add(new Player("Player 2", 0));
                mPlayAdapter.notifyDataSetChanged();
            }
        });

    }

}
