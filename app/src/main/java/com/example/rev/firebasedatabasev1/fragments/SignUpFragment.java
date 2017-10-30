package com.example.rev.firebasedatabasev1.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rev.firebasedatabasev1.R;
import com.example.rev.firebasedatabasev1.data.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

public class SignUpFragment extends Fragment implements View.OnClickListener {
    Button signUpButton;
    EditText userNameET;
    EditText passwordET;

    private DatabaseReference mUsersRef;

    public void setUserReference(DatabaseReference reference) {
        mUsersRef = reference;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_signup, container, false);

        signUpButton = rootView.findViewById(R.id.signup_button);
        userNameET = rootView.findViewById(R.id.username_et);
        passwordET = rootView.findViewById(R.id.password_et);

        signUpButton.setEnabled(false);
        signUpButton.setOnClickListener(this);

        userNameET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable.toString()) && !TextUtils.isEmpty(passwordET.getText().toString())) {
                    signUpButton.setEnabled(true);
                } else {
                    signUpButton.setEnabled(false);
                }
            }
        });


        passwordET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable.toString()) && !TextUtils.isEmpty(userNameET.getText().toString())) {
                    signUpButton.setEnabled(true);
                } else {
                    signUpButton.setEnabled(false);
                }
            }
        });



        return rootView;
    }

    @Override
    public void onClick(View view) {
        final String userName = userNameET.getText().toString();
        String password = passwordET.getText().toString();
        User newUser = new User(userName, password);

        final String userId = mUsersRef.push().getKey();
//        mUsersRef.setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                Toast.makeText(getContext(), "new user: \"" + userName + "\" has been added", Toast.LENGTH_SHORT).show();
//                userNameET.setText("");
//                passwordET.setText("");
//            }
//        });
        mUsersRef.child(userId).setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getContext(), "new user: \"" + userId + "\" has been added", Toast.LENGTH_SHORT).show();
                userNameET.setText("");
                passwordET.setText("");
            }
        });
    }
}
