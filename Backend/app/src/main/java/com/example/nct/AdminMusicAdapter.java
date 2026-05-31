package com.example.nct;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdminMusicAdapter extends RecyclerView.Adapter<AdminMusicAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<MusicFiles> mFiles;
    private OnSongClickListener listener;

    public interface OnSongClickListener {
        void onEditClick(int position);
        void onDeleteClick(int position);
    }

    public AdminMusicAdapter(Context mContext, ArrayList<MusicFiles> mFiles, OnSongClickListener listener) {
        this.mContext = mContext;
        this.mFiles = mFiles;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.admin_song_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.song_name.setText(mFiles.get(position).getTitle());
        holder.song_artist.setText(mFiles.get(position).getArtist());

        holder.btn_edit.setOnClickListener(v -> {
            if (listener != null) listener.onEditClick(position);
        });

        holder.btn_delete.setOnClickListener(v -> {
            if (listener != null) listener.onDeleteClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return mFiles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView song_name, song_artist;
        ImageButton btn_edit, btn_delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            song_name = itemView.findViewById(R.id.tv_song_name_admin);
            song_artist = itemView.findViewById(R.id.tv_song_artist_admin);
            btn_edit = itemView.findViewById(R.id.btn_edit_song);
            btn_delete = itemView.findViewById(R.id.btn_delete_song);
        }
    }
}
