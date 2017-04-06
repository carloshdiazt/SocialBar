package com.example.diazt.socialbar.fragments;

import android.annotation.TargetApi;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.diazt.socialbar.R;
import com.example.diazt.socialbar.main.OrderDivider;
import com.example.diazt.socialbar.models.Drink;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by diazt on 04-04-2017.
 */

public class DrinksFragment extends Fragment {

    public static DrinksFragment newInstance() {

        return new DrinksFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
               return inflater.inflate(R.layout.fragment_drinks, container, false);
    }


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        final RecyclerView recycler = (RecyclerView) view.findViewById(R.id.mainRv);
        recycler.addItemDecoration(new OrderDivider(getResources()));
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("drinks");


        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Drink, DrinkHolder>(Drink.class, android.R.layout.simple_list_item_1, DrinkHolder.class, reference) {
            @Override
            protected void populateViewHolder(DrinkHolder viewHolder, Drink model, int position) {
                viewHolder.setName(model.name);

            }
        };

        recycler.setAdapter(adapter);


    }

    public static class DrinkHolder extends RecyclerView.ViewHolder {
        View mView;

        public DrinkHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setName(final String name) {
            TextView field = (TextView) mView.findViewById(android.R.id.text1);
            field.setText(name);

            field.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    DatabaseReference favorites = FirebaseDatabase.getInstance().getReference().child("favorites").child(uid);
                    favorites.child(name).setValue(name);

                }
            });

        }
    }

}
