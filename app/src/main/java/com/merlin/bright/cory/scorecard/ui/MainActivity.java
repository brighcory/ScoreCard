package com.merlin.bright.cory.scorecard.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.merlin.bright.cory.scorecard.R;
import com.merlin.bright.cory.scorecard.adapters.GamesAdapter;
import com.merlin.bright.cory.scorecard.gameObjects.Game;

import java.util.ArrayList;

public class MainActivity extends Activity {
    public static ArrayList<Game> mGames = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private GamesAdapter gamesAdapter;
    public static final String NEW_GAME_INDEX = "New_Game_Index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        mGames.add(new Game("Game", false, false));
        mRecyclerView = findViewById(R.id.gameList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        gamesAdapter = new GamesAdapter(this, mGames);
        mRecyclerView.setAdapter(gamesAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addGame();
            }
        });


    }

    private void addGame() {
        mGames.add(new Game("Game's Name", false,false));
        gamesAdapter.notifyDataSetChanged();
        Intent newGameIntent = new Intent(this, PlayGameActivity.class);
        newGameIntent.putExtra(NEW_GAME_INDEX, mGames.size()-1);
        startActivity(newGameIntent);
    }

    private void removeGame(int position) {
        mGames.remove(position);
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
