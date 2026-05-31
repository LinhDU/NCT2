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

public class AdminUserAdapter extends RecyclerView.Adapter<AdminUserAdapter.UserViewHolder> {

    private Context context;
    private ArrayList<User> userList;
    private OnUserClickListener listener;

    public interface OnUserClickListener {
        void onEditClick(User user, int position);
        void onDeleteClick(User user, int position);
    }

    public AdminUserAdapter(Context context, ArrayList<User> userList, OnUserClickListener listener) {
        this.context = context;
        this.userList = userList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.admin_user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.tvUsername.setText(user.getUsername());
        holder.tvEmail.setText(user.getEmail());
        holder.tvRole.setText(user.getRole().toUpperCase());
        
        if (user.getRole().equals("admin")) {
            holder.tvRole.setBackgroundResource(android.R.color.holo_orange_light);
        } else {
            holder.tvRole.setBackgroundResource(android.R.color.holo_blue_light);
        }

        holder.btnEdit.setOnClickListener(v -> {
            if (listener != null) listener.onEditClick(user, position);
        });

        holder.btnDelete.setOnClickListener(v -> {
            if (listener != null) listener.onDeleteClick(user, position);
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void updateList(ArrayList<User> newList) {
        this.userList = newList;
        notifyDataSetChanged();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsername, tvEmail, tvRole;
        ImageButton btnEdit, btnDelete;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tv_username_admin);
            tvEmail = itemView.findViewById(R.id.tv_email_admin);
            tvRole = itemView.findViewById(R.id.tv_role_badge);
            btnEdit = itemView.findViewById(R.id.btn_edit_user);
            btnDelete = itemView.findViewById(R.id.btn_delete_user);
        }
    }
}
