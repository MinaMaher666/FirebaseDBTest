package com.example.rev.firebasedatabasev1.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rev.firebasedatabasev1.R;
import com.example.rev.firebasedatabasev1.adapters.UsersAdapter;
import com.example.rev.firebasedatabasev1.data.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;


public class GetUsersFragment extends Fragment {
    private static final String TAG = GetUsersFragment.class.getSimpleName();
    private DatabaseReference userRef;
    private UsersAdapter rVAdapter;

    RecyclerView usersRV;

    private ArrayList<User> mUsers;
    public GetUsersFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_getusers, container, false);
        usersRV = rootView.findViewById(R.id.users_rv);
        mUsers = new ArrayList<>();
        rVAdapter = new UsersAdapter(mUsers);
        usersRV.setAdapter(rVAdapter);
        usersRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        getUsers();

        return rootView;
    }

    void getUsers() {
        userRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String userId = dataSnapshot.getKey();
                User newUser = dataSnapshot.getValue(User.class);

                if (newUser != null) {
                    mUsers.add(newUser);
                    rVAdapter.notifyDataSetChanged();
                }

                Log.i(TAG, "onChildAdded: " + userId + "\nusername: " + newUser.getmUserName());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i(TAG, "onCancelled: why ?");
            }
        });
    }

    public void setUserReference(DatabaseReference reference) {
        this.userRef = reference;
    }
}
