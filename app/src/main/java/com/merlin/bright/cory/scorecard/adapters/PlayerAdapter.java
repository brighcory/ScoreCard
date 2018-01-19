package com.merlin.bright.cory.scorecard.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.merlin.bright.cory.scorecard.R;
import com.merlin.bright.cory.scorecard.gameObjects.Game;
import com.merlin.bright.cory.scorecard.gameObjects.Player;
import com.merlin.bright.cory.scorecard.gameObjects.Score;

import java.util.ArrayList;

/**
 * Created by cory on 12/13/17.
 */

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Player> mPlayers = new ArrayList<>();
    private ArrayList<Score> mScores = new ArrayList<>();
    private Game mGame;

    public PlayerAdapter(Context context, ArrayList<Player> players, ArrayList<Score> scores, Game game) {
        mContext = context;
        mPlayers = players;
        mScores = scores;
        mGame = game;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_play_game, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.playerEditText.setText(mPlayers.get(position).getPlayerName());
        holder.playerScore.setText(mScores.get(position).getScore());
    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        EditText playerEditText;
        TextView playerScore;
        public ViewHolder(View itemView) {
            super(itemView);
            playerEditText = itemView.findViewById(R.id.playerNameEditView);
            playerScore = itemView.findViewById(R.id.scoreTextView);
        }
    }
}
