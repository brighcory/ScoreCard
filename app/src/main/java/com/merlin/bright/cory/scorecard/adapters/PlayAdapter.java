package com.merlin.bright.cory.scorecard.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.merlin.bright.cory.scorecard.R;
import com.merlin.bright.cory.scorecard.gameObjects.Game;
import com.merlin.bright.cory.scorecard.gameObjects.Player;

import java.util.ArrayList;

/**
 * Created by cory on 12/13/17.
 * Player adapter to build list of players for each game and two lists for a team game.
 */

public class PlayAdapter extends RecyclerView.Adapter<PlayAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Player> mPlayers = new ArrayList<>();
    private Game mGame;

    public PlayAdapter(Context context, Game playingGame) {
        mContext = context;
        mGame = playingGame;
        mPlayers = playingGame.getPlayers();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.playNameTextView.setText(mPlayers.get(position).getPlayerName());
        holder.playNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                final EditText input = new EditText(mContext);
                builder.setView(input);

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = String.valueOf(input.getText());
                        mPlayers.get(holder.getAdapterPosition()).setPlayerName(name);
                        holder.playNameTextView.setText(name);
                    }
                });

                builder.setTitle("Enter Name of Player");
                AlertDialog dialog = builder.create();

                dialog.show();
            }
        });
        String playersScore = String.valueOf(mPlayers.get(position).getScore());
        holder.playerScore.setText(playersScore);
        holder.plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addScore(holder, String.valueOf(holder.scoreAddText.getText()));
            }
        });
        holder.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addScore(holder, String.valueOf("-" + holder.scoreAddText.getText()));
            }
        });
    }

    private void addScore(ViewHolder holder, String s) {
        try {
            int scoreDelta = Integer.parseInt(s);
            int scoreUpdate = mPlayers.get(holder.getAdapterPosition())
                    .getScore() + scoreDelta;
            mPlayers.get(holder.getAdapterPosition()).setScore(scoreUpdate);
            holder.playerScore.setText(String.valueOf(scoreUpdate));
        } catch (NumberFormatException e) {
            Toast.makeText(mContext, "Need a number", Toast.LENGTH_SHORT)
                    .show();
        }
    }


    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    public void addPlayer() {
        mGame.addPlayerToGame();
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView playNameTextView;
        TextView playerScore;
        Button plusButton;
        EditText scoreAddText;
        Button minusButton;

        ViewHolder(View itemView) {
            super(itemView);
            playNameTextView = itemView.findViewById(R.id.playerNameTextView);
            playerScore = itemView.findViewById(R.id.playerScoreTextView);
            plusButton = itemView.findViewById(R.id.addButton);
            scoreAddText = itemView.findViewById(R.id.scoreEditText);
            minusButton = itemView.findViewById(R.id.subtractButton);
        }
    }
}
