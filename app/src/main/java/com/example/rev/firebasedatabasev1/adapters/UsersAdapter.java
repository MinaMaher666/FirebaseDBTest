package com.example.rev.firebasedatabasev1.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rev.firebasedatabasev1.R;
import com.example.rev.firebasedatabasev1.data.User;

import java.util.List;


public class UsersAdapter extends  RecyclerView.Adapter<UsersAdapter.UserViewHolder>{
    private List<User> mUsers;

    public UsersAdapter(List<User> users) {
        mUsers = users;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        TextView userNameTV;
        TextView passwordTV;

        public UserViewHolder(View itemView) {
            super(itemView);

            userNameTV = itemView.findViewById(R.id.username_tv);
            passwordTV = itemView.findViewById(R.id.password_tv);
        }

        void bind(int position) {
            User currentUser = mUsers.get(position);
            userNameTV.setText(currentUser.getmUserName());
            passwordTV.setText(currentUser.getmPassword());
        }
    }
}
