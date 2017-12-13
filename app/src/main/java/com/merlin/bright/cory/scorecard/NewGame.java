package com.merlin.bright.cory.scorecard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class NewGame extends AppCompatActivity {
    private Context mContext;
    private ArrayAdapter<Player> mPlayers;
    private EditText gameNameEditText;
    private boolean hasTimer;
    private boolean highestWins;
    private boolean hasTeam;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        // add a spinner to make the number of dice a game will have.
//        Spinner spinner = findViewById(R.id.diceSpinner);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.dice, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
        gameNameEditText = findViewById(R.id.gameNameEditText);

    }

    public void beginGame(View view) {
        //Todo Make Game
        game = new Game(gameNameEditText.getText().toString(), highestWins, hasTeam, hasTeam);
        String string = String.format("Begin Game: %s", gameNameEditText.getText().toString());
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
        Intent playGameIntent = new Intent(this, PlayGameActivity.class);
        startActivity(playGameIntent);
    }

    public void addTimer(View view) {
        //Todo Make Game with Timer
        hasTimer = ((CheckBox) view).isChecked();
        Toast.makeText(this, "Added A Timer", Toast.LENGTH_SHORT).show();
    }

//    public void addDice(View view) {
//        Toast.makeText(this, "Add A Dice or more", Toast.LENGTH_SHORT).show();
//    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.highestRadioButton:
                if (checked) {
                    //Todo Make Highest Scoring Winning Game
                    highestWins = true;
                    Toast.makeText(this, "Highest Score will win "+highestWins, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.lowestRadioButton:
                if (checked) {
                    //Todo make Lowest Score Winning Game
                    highestWins = false;
                    Toast.makeText(this, "Lowest Score will win "+highestWins, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.individualRadioButton:
                if (checked) {
                    //Todo make individual game
                    hasTeam = false;
                    Toast.makeText(this, "Each Player"+hasTeam, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.teamRadioButton:
                if (checked) {
                    //Todo make Team game
                    hasTeam = true;
                    Toast.makeText(this, "Teams "+hasTeam, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}
