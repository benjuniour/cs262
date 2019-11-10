package edu.calvin.cs262.bkb5;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder> {

    private final LayoutInflater mInflater;
    private List<Player> _Players; // Cached copy of words

    PlayerListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new PlayerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        if (_Players != null) {
            Player current = _Players.get(position);
            holder.playerItemView.setText(current.getId());
        } else {
            // Covers the case of data not being ready yet.
            holder.playerItemView.setText("No Id");
        }
    }

    void setWords(List<Player> players){
        _Players = players;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (_Players != null)
            return _Players.size();
        else return 0;
    }

    class PlayerViewHolder extends RecyclerView.ViewHolder {
        private final TextView playerItemView;

        private PlayerViewHolder(View itemView) {
            super(itemView);
            playerItemView = itemView.findViewById(R.id.textView);
        }
    }
}

