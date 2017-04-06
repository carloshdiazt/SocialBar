package com.example.diazt.socialbar.adapter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.diazt.socialbar.R;
import com.example.diazt.socialbar.fragments.DrinksFragment;
import com.example.diazt.socialbar.fragments.FavoritesFragment;

public class ActivityLoged extends FragmentPagerAdapter {


    private Context context;



     public ActivityLoged(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return DrinksFragment.newInstance();
            case 1:
                return FavoritesFragment.newInstance();
            default:
                return DrinksFragment.newInstance();

        }
    }





    @Override
    public int getCount() {
        return 2;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.Drink);
            case 1:
                return context.getString(R.string.Favorites);

            default:
                return context.getString(R.string.Drink);
        }


    }


}
