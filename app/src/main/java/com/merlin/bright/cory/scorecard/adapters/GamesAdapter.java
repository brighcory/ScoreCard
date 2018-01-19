package com.merlin.bright.cory.scorecard.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.merlin.bright.cory.scorecard.R;
import com.merlin.bright.cory.scorecard.gameObjects.Game;

import java.util.ArrayList;

/**
 * Created by cory on 12/6/17.
 */

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {
    private ArrayList<Game> mGames = new ArrayList<>();
    private Context mContext;

    public GamesAdapter(Context context, ArrayList<Game> games) {
        mGames = games;
        mContext = context;
    }

    @Override
    public GamesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GamesAdapter.ViewHolder holder, int position) {
        holder.mGameName.setText(mGames.get(position).getGameName());
        holder.mWinnerName.setText("Holder");
        holder.mScoreOfWinner.setText("0");
    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mGameName;
        TextView mWinnerName;
        TextView mScoreOfWinner;

        public ViewHolder(View itemView) {
            super(itemView);
            mGameName = itemView.findViewById(R.id.gameNameTextView);
            mWinnerName = itemView.findViewById(R.id.winnerTextView);
            mScoreOfWinner = itemView.findViewById(R.id.scoreTextView);
        }
    }
}
