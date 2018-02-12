package com.merlin.bright.cory.scorecard.ui;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.merlin.bright.cory.scorecard.R;
import com.merlin.bright.cory.scorecard.adapters.GamesAdapter;
import com.merlin.bright.cory.scorecard.database.repository.GameViewModel;
import com.merlin.bright.cory.scorecard.gameObjects.Game;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int NEW_GAME_ACTIVITY_REQUEST_CODE = 1;
    public static ArrayList<Game> mGames = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private GamesAdapter gamesAdapter;
    public static final String NEW_GAME_INDEX = "New_Game_Index";
    public static GameViewModel mGameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        mRecyclerView = findViewById(R.id.gameList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        gamesAdapter = new GamesAdapter(this, mGames);
        mRecyclerView.setAdapter(gamesAdapter);

        mGameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
        mGameViewModel.getGames().observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(@Nullable List<Game> games) {
                gamesAdapter.setGames(games);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addGame();
            }
        });
    }

    private void addGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("Team", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mGameViewModel.insert(new Game("Game's Name",
                        true, true));
                Intent newGameIntent =
                        new Intent(MainActivity.this,
                                PlayTeamGameActivity.class);
                newGameIntent.putExtra(NEW_GAME_INDEX, mGames.size()-1);
                startActivityForResult(newGameIntent, NEW_GAME_ACTIVITY_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("Individual", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mGameViewModel.insert(new Game("Game's Name no Team",
                        true, false));
                Intent newGameIntent = new Intent(MainActivity.this,
                        PlayGameActivity.class);
                startActivityForResult(newGameIntent, NEW_GAME_ACTIVITY_REQUEST_CODE);
            }
        });
        builder.setTitle("Does the Game have Teams");
        AlertDialog dialog = builder.create();

        dialog.show();
    }


    public static void removeGame(int position) {
        mGameViewModel.deleteGame(mGames.get(position));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
