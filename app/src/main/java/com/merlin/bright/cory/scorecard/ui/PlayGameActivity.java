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

public class PlayGameActivity extends Activity {

    public static final String EXTRA_REPLY = PlayGameActivity.class.getSimpleName();
    private Game playingGame;
    private RecyclerView mRecyclerView;
    private PlayAdapter mPlayAdapter;
    private Button saveGame;
    private Button deleteGame;
    int gameNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        mRecyclerView = findViewById(R.id.listOfPlayers);
        saveGame = findViewById(R.id.save_game_button);
        deleteGame = findViewById(R.id.delete_game_button);

        Intent data = getIntent();
        gameNumber = data.getIntExtra(MainActivity.NEW_GAME_INDEX, 0);
        playingGame = MainActivity.mGames.get(gameNumber);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPlayAdapter = new PlayAdapter(this, playingGame);
        mRecyclerView.setAdapter(mPlayAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayAdapter.addPlayer();
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
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, gameNumber);
        MainActivity.updateGame(gameNumber);
        MainActivity.insert(playingGame.getPlayers());
        setResult(RESULT_OK, replyIntent);
        finish();
    }

    private void deleteTheGame() {
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, gameNumber);
        MainActivity.deleteGame(gameNumber);
        setResult(RESULT_CANCELED, replyIntent);
        finish();
    }


}
