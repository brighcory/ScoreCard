package com.merlin.bright.cory.scorecard.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.merlin.bright.cory.scorecard.R;
import com.merlin.bright.cory.scorecard.gameObjects.Game;
import com.merlin.bright.cory.scorecard.gameObjects.Player;

import java.util.ArrayList;

/**
 * Created by cory on 12/13/17.
 */

public class PlayAdapter extends RecyclerView.Adapter<PlayAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Player> mPlayers = new ArrayList<>();
    private Game mGame;

    public PlayAdapter(Context context, ArrayList<Player> player) {
        mContext = context;
        mPlayers = player;
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
        String playersScore = String.valueOf(mPlayers.get(position).getScore());
        holder.playerScore.setText(playersScore);
        holder.plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int scoreDelta = Integer.parseInt(String.valueOf(holder.scoreAddText.getText()));
                int scoreUpdate = mPlayers.get(holder.getAdapterPosition()).getScore()+scoreDelta;
                mPlayers.get(holder.getAdapterPosition()).setScore(scoreUpdate);
                holder.playerScore.setText(String.format("%d", scoreUpdate));
            }
        });
        holder.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int scoreDelta = Integer.parseInt(String.valueOf(holder.scoreAddText.getText()));
                int scoreUpdate = mPlayers.get(holder.getAdapterPosition()).getScore()-scoreDelta;
                mPlayers.get(holder.getAdapterPosition()).setScore(scoreUpdate);
                holder.playerScore.setText(String.format("%d", scoreUpdate));
            }
        });
    }


    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView playNameTextView;
        TextView playerScore;
        Button plusButton;
        EditText scoreAddText;
        Button minusButton;

        public ViewHolder(View itemView) {
            super(itemView);
            playNameTextView = itemView.findViewById(R.id.playerNameTextView);
            playerScore = itemView.findViewById(R.id.playerScoreTextView);
            plusButton = itemView.findViewById(R.id.addButton);
            scoreAddText = itemView.findViewById(R.id.scoreEditText);
            minusButton = itemView.findViewById(R.id.subtractButton);
        }
    }
}
