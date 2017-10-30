package com.example.rev.firebasedatabasev1.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rev.firebasedatabasev1.R;
import com.example.rev.firebasedatabasev1.data.User;

import java.util.List;
import java.util.Map;


public class UsersAdapter extends  RecyclerView.Adapter<UsersAdapter.UserViewHolder>{
//    private List<User> mUsers;
    private List<String> mUserKeys;
    private Map<String, User> mUsers;
    private UserLongClickListener mLongClickListener;

    public interface UserLongClickListener {
        void onLongClick(int position);
    }


    public UsersAdapter(List<String> keys, Map<String, User> users, UserLongClickListener listener) {
        mUserKeys = keys;
        mUsers = users;
        mLongClickListener = listener;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        String userId = mUserKeys.get(position);
        holder.bind(userId);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        TextView userNameTV;
        TextView passwordTV;

        public UserViewHolder(View itemView) {
            super(itemView);

            userNameTV = itemView.findViewById(R.id.username_tv);
            passwordTV = itemView.findViewById(R.id.password_tv);

            itemView.setOnLongClickListener(this);
        }

        void bind(String userKey) {
            User currentUser = mUsers.get(userKey);
            userNameTV.setText(currentUser.getmUserName());
            passwordTV.setText(currentUser.getmPassword());
        }

        @Override
        public boolean onLongClick(View view) {
            mLongClickListener.onLongClick(getAdapterPosition());
            return true;
        }
    }
}
