package com.merlin.bright.cory.scorecard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by cory on 12/6/17.
 */

public class GamesAdapter extends BaseAdapter {
    private ArrayList<Game> mGames;
    private Context mContext;

    public GamesAdapter(Context context, ArrayList<Game> games) {
        mGames = games;
        mContext = context;
    }

    public void addGame(Game game){
        mGames.add(game);
    }

    @Override
    public int getCount() {
        return mGames.size();
    }

    @Override
    public Object getItem(int position) {
        return mGames.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;

        if (convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.game_item, null);
            holder.gameName = convertView.findViewById(R.id.gameNameTextView);
            holder.winnerName = convertView.findViewById(R.id.winnerTextView);
            holder.score = convertView.findViewById(R.id.scoreTextView);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.gameName.setText(mGames.get(position).getGameName());
        //holder.winnerName.setText(mGames.get(position).getWinner().getPlayerName());
        //holder.score.setText(String.format(""));
        return convertView;
    }

    class ViewHolder{
        TextView gameName;
        TextView winnerName;
        TextView score;
    }
}
