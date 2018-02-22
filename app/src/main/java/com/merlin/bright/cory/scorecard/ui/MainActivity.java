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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.merlin.bright.cory.scorecard.R;
import com.merlin.bright.cory.scorecard.adapters.GamesAdapter;
import com.merlin.bright.cory.scorecard.database.repository.GameViewModel;
import com.merlin.bright.cory.scorecard.gameObjects.Game;
import com.merlin.bright.cory.scorecard.gameObjects.Player;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int GAME_REQUEST_CODE = 1;
    public static ArrayList<Game> mGames = new ArrayList<>();
    public static ArrayList<Player> mPlayers = new ArrayList<>();
    private GamesAdapter gamesAdapter;
    public static final String NEW_GAME_INDEX = "New_Game_Index";
    static GameViewModel mGameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = findViewById(R.id.toolbar);

        RecyclerView recyclerView = findViewById(R.id.gameList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        gamesAdapter = new GamesAdapter(this, mGames);
        recyclerView.setAdapter(gamesAdapter);

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
                startActivityForResult(newGameIntent, GAME_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("Individual", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mGameViewModel.insert(new Game("No Team Game",
                        true, false));
                Intent newGameIntent = new Intent(MainActivity.this,
                        PlayGameActivity.class);
                newGameIntent.putExtra(NEW_GAME_INDEX, mGames.size()-1);
                startActivityForResult(newGameIntent, GAME_REQUEST_CODE);
            }
        });
        builder.setTitle("Does the Game have Teams");
        AlertDialog dialog = builder.create();

        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int gameNumber = data.getIntExtra(PlayGameActivity.EXTRA_REPLY,0);
        if(requestCode == GAME_REQUEST_CODE && resultCode == RESULT_OK){
            updateGame(gameNumber);
        }else{
            deleteGame(gameNumber);
        }
    }

    public static void updateGame(int gameNumber) {
        mGameViewModel.updateGame(mGames.get(gameNumber));
    }

    public static void deleteGame(int gameNumber) {
        mGameViewModel.deleteGame(mGames.get(gameNumber));
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

    public static void insert(ArrayList<Player> players) {
        mGameViewModel.insert(players);
    }

    public static void getPlayers(int id) {
        mGameViewModel.getGamePlayers(id);
    }

    public static void deletePlayer(Player player) {
        mGameViewModel.deletePlayer(player);
    }
}
