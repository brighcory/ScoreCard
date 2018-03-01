package com.merlin.bright.cory.scorecard.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.merlin.bright.cory.scorecard.R;
import com.merlin.bright.cory.scorecard.adapters.PlayAdapter;
import com.merlin.bright.cory.scorecard.database.repository.GameViewModel;
import com.merlin.bright.cory.scorecard.gameObjects.Game;
import com.merlin.bright.cory.scorecard.gameObjects.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayTeamGameActivity extends AppCompatActivity {
    Game playingGame;
    ArrayList<Player> aTeam = new ArrayList<>();
    ArrayList<Player> bTeam = new ArrayList<>();
    RecyclerView aTeamList;
    RecyclerView bTeamList;
    PlayAdapter aTeamPlayAdapter;
    PlayAdapter bTeamPlayAdapter;
    TextView aTeamNameView;
    TextView bTeamNameView;
    Button aTeamAddPlayer;
    Button bTeamAddPlayer;
    TextView aTeamScore;
    TextView bTeamScore;
    GameViewModel mGameViewModel;
    int gameNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_team_game);
        Intent data = getIntent();
        gameNumber = data.getIntExtra(MainActivity.NEW_GAME_INDEX, 0);
        playingGame = MainActivity.mGames.get(gameNumber);


        //Setup Views and Buttons
        aTeamNameView = findViewById(R.id.teamANameTextView);
        bTeamNameView = findViewById(R.id.teamBNameTextView);

        aTeamNameView.setText("Team A");
        bTeamNameView.setText("Team B");

        aTeamAddPlayer = findViewById(R.id.addPlayerToTeamAButton);
        bTeamAddPlayer = findViewById(R.id.addPlayerToTeamBButton);

        aTeamScore = findViewById(R.id.teamAScoreTextView);
        bTeamScore = findViewById(R.id.teamBScoreTextView);

        Button saveGameButton = findViewById(R.id.save_team_game_button);

        aTeamList = findViewById(R.id.teamAPlayerList);
        bTeamList = findViewById(R.id.teamBPlayerList);

        //Set setup layout Managers
        aTeamList.setLayoutManager(new LinearLayoutManager(this));
        bTeamList.setLayoutManager(new LinearLayoutManager(this));

        //Setup View Model and get players from Database
        mGameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
        mGameViewModel.getPlayers().observe(this, new Observer<List<Player>>() {
            @Override
            public void onChanged(@Nullable List<Player> players) {
                ArrayList<Player> aPlayerArrayList = new ArrayList<>();
                ArrayList<Player> bPlayerArrayList = new ArrayList<>();
                for (Player player : players) {
                    if (player.getGameId() == playingGame.getId()) {
                        if (player.getTeamName().equals(aTeamNameView.getText().toString()))
                            aPlayerArrayList.add(player);
                        else
                            bPlayerArrayList.add(player);
                    }
                }
                aTeam = aPlayerArrayList;
                aTeamPlayAdapter.setPlayers(aPlayerArrayList);
                bTeam = bPlayerArrayList;
                bTeamPlayAdapter.setPlayers(bPlayerArrayList);
            }
        });

        //Setup The adapters
        aTeamPlayAdapter = new PlayAdapter(this, playingGame, mGameViewModel);
        bTeamPlayAdapter = new PlayAdapter(this, playingGame, mGameViewModel);

        aTeamList.setAdapter(aTeamPlayAdapter);
        bTeamList.setAdapter(bTeamPlayAdapter);


        aTeamNameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(PlayTeamGameActivity.this);
                final EditText input = new EditText(PlayTeamGameActivity.this);
                builder.setView(input);

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = String.valueOf(input.getText());
                        for (Player player : aTeam) player.setTeamName(name);
                        aTeamNameView.setText(name);
                    }
                });

                builder.setTitle("Enter Name of Team").create().show();
            }
        });

        bTeamNameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(PlayTeamGameActivity.this);
                final EditText input = new EditText(PlayTeamGameActivity.this);
                builder.setView(input);

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = String.valueOf(input.getText());
                        for (Player player : bTeam) player.setTeamName(name);
                        bTeamNameView.setText(name);
                    }
                });

                builder.setTitle("Enter Name of Team").create().show();
            }
        });

        aTeamAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGameViewModel.insert(new Player("A Player" + (1 + aTeam.size()),
                        playingGame.getId()));
            }
        });

        bTeamAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGameViewModel.insert(new Player("B Player" + (1 + bTeam.size()),
                        playingGame.getId()));
            }
        });
        aTeamScore.setText(getTeamScore(aTeam));
        bTeamScore.setText(getTeamScore(bTeam));

        saveGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTheGame();
            }
        });

    }

    private void saveTheGame() {
        Intent replyIntent = getIntent();
        mGameViewModel.insert(aTeam);
        mGameViewModel.insert(bTeam);
        mGameViewModel.updateGame(playingGame);
        setResult(RESULT_OK, replyIntent);
        finish();
    }

    private String getTeamScore(ArrayList<Player> team) {
        int teamScore = 0;
        for (Player player : team) {
            teamScore = +player.getScore();
        }
        return teamScore + "";
    }
}
