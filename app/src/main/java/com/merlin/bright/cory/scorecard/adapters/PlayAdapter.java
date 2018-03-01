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
import com.merlin.bright.cory.scorecard.database.repository.GameViewModel;
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
    GameViewModel mGameViewModel;

    public PlayAdapter(Context context, Game playingGame, GameViewModel gameViewModel) {
        mContext = context;
        mGame = playingGame;
        mPlayers = playingGame.getPlayers();
        mGameViewModel = gameViewModel;
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
        holder.playNameTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Delete Player");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mGameViewModel.deletePlayer(mPlayers.get(holder.getAdapterPosition()));
                        mPlayers.remove(holder.getAdapterPosition());
                        notifyDataSetChanged();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
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
        int scoreDelta;
        if (s.equals("-")) scoreDelta = -1;
        else
            try {
                scoreDelta = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                scoreDelta = 1;
                Toast.makeText(mContext, "Add 1", Toast.LENGTH_SHORT)
                        .show();
            }
        int scoreUpdate = mPlayers.get(holder.getAdapterPosition())
                .getScore() + scoreDelta;
        mPlayers.get(holder.getAdapterPosition()).setScore(scoreUpdate);
        holder.playerScore.setText(String.valueOf(scoreUpdate));
    }


    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    public void setPlayers(ArrayList<Player> players) {
        mPlayers.clear();
        mPlayers = players;
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
