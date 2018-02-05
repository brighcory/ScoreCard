package com.merlin.bright.cory.scorecard.ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.merlin.bright.cory.scorecard.R;
import com.merlin.bright.cory.scorecard.adapters.PlayAdapter;
import com.merlin.bright.cory.scorecard.gameObjects.Player;

import java.util.ArrayList;

public class PlayTeamGameActivity extends AppCompatActivity {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_team_game);

        aTeamNameView = findViewById(R.id.teamANameTextView);
        bTeamNameView = findViewById(R.id.teamBNameTextView);

        aTeamAddPlayer = findViewById(R.id.addPlayerToTeamAButton);
        bTeamAddPlayer = findViewById(R.id.addPlayerToTeamBButton);

        if (aTeam.size() == 0) {
            aTeam.add(new Player("Player Team A", 0));
        }
        if (bTeam.size() == 0)
            bTeam.add(new Player("Player Team B", 0));

        aTeamList = findViewById(R.id.teamAPlayerList);
        bTeamList = findViewById(R.id.teamBPlayerList);

        aTeamList.setLayoutManager(new LinearLayoutManager(this));
        bTeamList.setLayoutManager(new LinearLayoutManager(this));

        aTeamPlayAdapter = new PlayAdapter(this, aTeam);
        bTeamPlayAdapter = new PlayAdapter(this, bTeam);

        aTeamList.setAdapter(aTeamPlayAdapter);
        bTeamList.setAdapter(bTeamPlayAdapter);

        aTeamNameView.setText("Team A");
        bTeamNameView.setText("Team B");

        aTeamNameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PlayTeamGameActivity.this);
                final EditText input = new EditText(PlayTeamGameActivity.this);
                builder.setView(input);

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = String.valueOf(input.getText());
                        aTeamNameView.setText(name);
                    }
                });

                builder.setTitle("Enter Name of Team").create().show();
            }
        });

        bTeamNameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PlayTeamGameActivity.this);
                final EditText input = new EditText(PlayTeamGameActivity.this);
                builder.setView(input);

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = String.valueOf(input.getText());
                        bTeamNameView.setText(name);
                    }
                });

                builder.setTitle("Enter Name of Team").create().show();
            }
        });

        aTeamAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aTeam.add(new Player("Player", 0));
                aTeamPlayAdapter.notifyDataSetChanged();
            }
        });

        bTeamAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bTeam.add(new Player("Player", 0));
                bTeamPlayAdapter.notifyDataSetChanged();
            }
        });
    }
}
