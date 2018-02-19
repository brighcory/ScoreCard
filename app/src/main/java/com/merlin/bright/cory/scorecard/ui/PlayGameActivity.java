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

public class PlayGameActivity extends Activity {

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

        saveGame = findViewById(R.id.save_game_button);
        deleteGame = findViewById(R.id.delete_game_button);

        Intent data = getIntent();
        gameNumber = data.getIntExtra(MainActivity.NEW_GAME_INDEX, 0);
        playingGame = MainActivity.mGames.get(gameNumber);

        mRecyclerView = findViewById(R.id.listOfPlayers);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPlayAdapter = new PlayAdapter(this, playingGame);
        mRecyclerView.setAdapter(mPlayAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //todo add player
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
        MainActivity.mGameViewModel.updateGame(playingGame);
        for(Player player : playingGame.getPlayers()){
            player.setGameId(playingGame.getId());
            MainActivity.mGameViewModel.updatePlayer(player);
        }
        MainActivity.mGameViewModel.insert(playingGame.getPlayers());
        finish();
    }

    private void deleteTheGame() {
        MainActivity.removeGame(gameNumber);
        setResult(RESULT_CANCELED);
        finish();
    }


}
