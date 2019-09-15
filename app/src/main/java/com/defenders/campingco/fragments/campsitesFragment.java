package com.defenders.campingco.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.defenders.campingco.R;
import com.defenders.campingco.adapters.campsiteAdapter;
import com.defenders.campingco.adapters.vehicleViewHolder;
import com.defenders.campingco.imageSlider.AutoSlideBannerView;
import com.defenders.campingco.models.campsitesSmallList;
import com.defenders.campingco.models.vehiclesList;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;

public class campsitesFragment extends Fragment {


    View view;
    private FirebaseAuth firebaseAuth;
    String userID;
    RecyclerView recyclerView;


    DatabaseReference campsitesDatabaseRef;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_campsites,container,false);


        firebaseAuth = FirebaseAuth.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();
        recyclerView = view.findViewById(R.id.rv_campsites);

        //RecyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        campsitesDatabaseRef = FirebaseDatabase.getInstance().getReference().child("campsites").child("shortDetail");




        loadCampsites();






        return view;
    }
    private void loadCampsites(){

        FirebaseRecyclerAdapter<campsitesSmallList, campsiteAdapter> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<campsitesSmallList, campsiteAdapter>(
                        campsitesSmallList.class,
                        R.layout.campsite_cards,
                        campsiteAdapter.class,
                        campsitesDatabaseRef
                ) {
                    @Override
                    protected void populateViewHolder(campsiteAdapter viewHolder, campsitesSmallList model, int position) {



                        viewHolder.setDetails(getContext(),model.getImageUrl(),model.getTitle(),model.getDetails());

                        viewHolder.attachListeners(getContext(),model.getCampsiteId());

                    }
                };
        recyclerView.setAdapter(firebaseRecyclerAdapter);


    }


}
