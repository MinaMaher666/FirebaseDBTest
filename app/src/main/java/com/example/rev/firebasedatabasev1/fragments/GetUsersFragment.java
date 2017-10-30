package com.example.rev.firebasedatabasev1.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rev.firebasedatabasev1.R;
import com.example.rev.firebasedatabasev1.adapters.UsersAdapter;
import com.example.rev.firebasedatabasev1.data.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GetUsersFragment extends Fragment implements UsersAdapter.UserLongClickListener{
    private static final String TAG = GetUsersFragment.class.getSimpleName();
    private DatabaseReference userRef;
    private UsersAdapter rVAdapter;
    private List<String> mUserKeys;
    private Map<String, User> mUsersMap;

    RecyclerView usersRV;

    public GetUsersFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_getusers, container, false);
        usersRV = rootView.findViewById(R.id.users_rv);
        mUserKeys = new ArrayList<>();
        mUsersMap = new HashMap<>();
        rVAdapter = new UsersAdapter(mUserKeys, mUsersMap, this);
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

                mUserKeys.add(userId);
                mUsersMap.put(userId, newUser);

                rVAdapter.notifyDataSetChanged();

                Log.i(TAG, "onChildAdded: " + userId + "\nusername: " + newUser.getmUserName());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String userId = dataSnapshot.getKey();
                mUsersMap.remove(userId);
                mUserKeys.remove(userId);
                rVAdapter.notifyDataSetChanged();
                Log.i(TAG, "onChildAdded: " + userId + " has been removed");
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

    @Override
    public void onLongClick(int position) {
        showDialog(position);
    }

    private void showDialog(int position) {
        final String userId = mUserKeys.get(position);
        final User user = mUsersMap.get(userId);

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Remove");
        builder.setMessage("Delete " + user.getmUserName() +  " ?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                userRef.child(userId).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getContext(), user.getmUserName() + "has been deleted!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.show();
    }
}
