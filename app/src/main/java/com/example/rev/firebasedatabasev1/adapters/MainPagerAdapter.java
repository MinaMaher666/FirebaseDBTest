package com.example.rev.firebasedatabasev1.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.rev.firebasedatabasev1.fragments.GetUsersFragment;
import com.example.rev.firebasedatabasev1.fragments.SignUpFragment;
import com.google.firebase.database.DatabaseReference;

public class MainPagerAdapter extends FragmentPagerAdapter {
    private DatabaseReference userRef;

    public static final String[] TABS = {"Sign Up", "Users"};
    public MainPagerAdapter(FragmentManager fm, DatabaseReference ref) {
        super(fm);
        userRef = ref;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return getSignUpFragment();
            case 1:
                return getUsersFragment();
        }
        return getSignUpFragment();
    }

    private Fragment getSignUpFragment() {
        SignUpFragment fragment = new SignUpFragment();
        fragment.setUserReference(userRef);
        return fragment;
    }

    private Fragment getUsersFragment() {
        GetUsersFragment fragment = new GetUsersFragment();
        fragment.setUserReference(userRef);
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TABS[position];
    }

    @Override
    public int getCount() {
        return 2;
    }
}
