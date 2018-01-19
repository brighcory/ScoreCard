package com.merlin.bright.cory.scorecard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.merlin.bright.cory.scorecard.R;
import com.merlin.bright.cory.scorecard.gameObjects.Player;
import com.merlin.bright.cory.scorecard.gameObjects.Score;

import java.util.ArrayList;

/**
 * Created by cory on 12/13/17.
 */

public class PlayerAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Player> mPlayers;
    private ArrayList<Score> mScores;

    public PlayerAdapter(Context context, ArrayList<Player> players) {
        mContext = context;
        mPlayers = players;
    }

    @Override
    public int getCount() {
        return mPlayers.size();
    }

    @Override
    public Object getItem(int position) {
        return mPlayers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final PlayerAdapter.ViewHolder holder;

        if (convertView == null){
            holder = new PlayerAdapter.ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.player_item, null);
            holder.playersName = convertView.findViewById(R.id.playerNameEditView);
            holder.scoreTotal = convertView.findViewById(R.id.scoreTextView);
            holder.addButton = convertView.findViewById(R.id.addButton);
            holder.score = convertView.findViewById(R.id.scoreEditText);
            holder.subtractButton = convertView.findViewById(R.id.subtractButton);

            convertView.setTag(holder);
        }else {
            holder = (PlayerAdapter.ViewHolder) convertView.getTag();
        }
        holder.playersName.setText(mPlayers.get(position).getPlayerName());
//        holder.scoreTotal.setText(mPlayers.getItem(position).getPlayerName());
        return convertView;
    }

    class ViewHolder{
        EditText playersName;
        TextView scoreTotal;
        Button addButton;
        EditText score;
        Button subtractButton;
    }
}
