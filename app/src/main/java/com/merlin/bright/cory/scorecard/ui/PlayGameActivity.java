package com.merlin.bright.cory.scorecard.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.merlin.bright.cory.scorecard.R;
import com.merlin.bright.cory.scorecard.adapters.PlayAdapter;
import com.merlin.bright.cory.scorecard.database.repository.GameViewModel;
import com.merlin.bright.cory.scorecard.gameObjects.Game;
import com.merlin.bright.cory.scorecard.gameObjects.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayGameActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = PlayGameActivity.class.getSimpleName();
    private Game playingGame;
    private RecyclerView mRecyclerView;
    private PlayAdapter mPlayAdapter;
    int gameNumber;
    GameViewModel mGameViewModel;
    ArrayList<Player> mPlayers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        mRecyclerView = findViewById(R.id.listOfPlayers);
        Button saveGame = findViewById(R.id.save_game_button);

        Intent data = getIntent();
        gameNumber = data.getIntExtra(MainActivity.NEW_GAME_INDEX, 0);
        playingGame = MainActivity.mGames.get(gameNumber);

        mGameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
        mGameViewModel.getPlayers().observe(this, new Observer<List<Player>>() {
            @Override
            public void onChanged(@Nullable List<Player> players) {
                ArrayList<Player> playerArrayList = new ArrayList<>();
                for (Player player : players) {
                    if (player.getGameId() == playingGame.getId()) {
                        playerArrayList.add(player);
                    }
                }
                mPlayers = playerArrayList;
                mPlayAdapter.setPlayers(playerArrayList);
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPlayAdapter = new PlayAdapter(this, playingGame, mGameViewModel);
        mRecyclerView.setAdapter(mPlayAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGameViewModel.insert(new Player("Player " + (1 + mPlayers.size()),
                        playingGame.getId()));
            }
        });

        saveGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTheGame();
            }
        });
    }

    private void saveTheGame() {
        Intent replyIntent = new Intent();
        mGameViewModel.insert(mPlayers);
        mGameViewModel.updateGame(playingGame);
        replyIntent.putExtra(EXTRA_REPLY, gameNumber);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}
