package com.merlin.bright.cory.scorecard.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.merlin.bright.cory.scorecard.R;
import com.merlin.bright.cory.scorecard.adapters.PlayAdapter;
import com.merlin.bright.cory.scorecard.gameObjects.Game;
import com.merlin.bright.cory.scorecard.gameObjects.Player;

import java.util.ArrayList;

public class PlayGameActivity extends Activity {

    private Game playingGame;
    private ArrayList<Player> mPlayers = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private PlayAdapter mPlayAdapter;
    private Button saveGame;
    private Button deleteGame;
    int gameNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        saveGame = findViewById(R.id.save_game_button);
        deleteGame = findViewById(R.id.delete_game_button);

        Intent data = getIntent();
        gameNumber = data.getIntExtra(MainActivity.NEW_GAME_INDEX, 0);
        playingGame = MainActivity.mGames.get(gameNumber);
        mPlayers = playingGame.getPlayers();
        if (mPlayers.size() == 0) {
            mPlayers.add(new Player("Player 1", 0));
        }

        mRecyclerView = findViewById(R.id.listOfPlayers);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPlayAdapter = new PlayAdapter(this, mPlayers, playingGame);
        mRecyclerView.setAdapter(mPlayAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPlayers.add(
                        new Player("Player " + (mPlayers.size() + 1), 0));
                mPlayAdapter.notifyDataSetChanged();
            }
        });

        saveGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTheGame();
            }
        });
        deleteGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteTheGame();
            }
        });

    }

    private void saveTheGame() {
        Intent replyIntent = getIntent();
//        replyIntent.putExtra();
        setResult(RESULT_OK, replyIntent);
        finish();
    }

    private void deleteTheGame() {
        MainActivity.removeGame(gameNumber);
        setResult(RESULT_CANCELED);
        finish();
    }


}
