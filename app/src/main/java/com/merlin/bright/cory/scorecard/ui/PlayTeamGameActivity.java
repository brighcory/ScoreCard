package com.merlin.bright.cory.scorecard.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import com.merlin.bright.cory.scorecard.gameObjects.Game;
import com.merlin.bright.cory.scorecard.gameObjects.Player;

import java.util.ArrayList;

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
    Button saveGameButton;
    Button deleteGameButton;
    int gameNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_team_game);
        Intent data = getIntent();
        gameNumber = data.getIntExtra(MainActivity.NEW_GAME_INDEX, 0);
        playingGame = MainActivity.mGames.get(gameNumber);


        aTeamNameView = findViewById(R.id.teamANameTextView);
        bTeamNameView = findViewById(R.id.teamBNameTextView);

        aTeamAddPlayer = findViewById(R.id.addPlayerToTeamAButton);
        bTeamAddPlayer = findViewById(R.id.addPlayerToTeamBButton);

        aTeamScore = findViewById(R.id.teamAScoreTextView);
        bTeamScore = findViewById(R.id.teamBScoreTextView);

        saveGameButton = findViewById(R.id.save_team_game_button);
        deleteGameButton = findViewById(R.id.delete_team_game_button);


        //todo add players to both teams

        aTeamList = findViewById(R.id.teamAPlayerList);
        bTeamList = findViewById(R.id.teamBPlayerList);

        aTeamList.setLayoutManager(new LinearLayoutManager(this));
        bTeamList.setLayoutManager(new LinearLayoutManager(this));

        aTeamPlayAdapter = new PlayAdapter(this, playingGame);
        bTeamPlayAdapter = new PlayAdapter(this, playingGame);

        aTeamList.setAdapter(aTeamPlayAdapter);
        bTeamList.setAdapter(bTeamPlayAdapter);

        aTeamNameView.setText("Team A");
        bTeamNameView.setText("Team B");

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
                //todo add player to team a
            }
        });

        bTeamAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo add player to team b
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

        deleteGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteTheGame();
            }
        });

    }

    private void saveTheGame() {
        MainActivity.updateGame(gameNumber);
        Intent replyIntent = getIntent();
        setResult(RESULT_OK, replyIntent);
        finish();
    }

    private void deleteTheGame() {
        MainActivity.deleteGame(gameNumber);
        setResult(RESULT_CANCELED);
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
