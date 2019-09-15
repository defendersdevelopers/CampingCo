package com.defenders.campingco.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.defenders.campingco.R;
import com.defenders.campingco.adapters.vehicleViewHolder;
import com.defenders.campingco.imageSlider.AutoSlideBannerView;
import com.defenders.campingco.models.vehiclesList;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class homeFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    String userID;


    View view;
    RecyclerView recyclerView;
    //String[] places = {"Meghalaya","Assam","Sikkim","Arunachal Pradesh","Nagaland"};
    @BindView(R.id.bannerSlider)
    AutoSlideBannerView bannerSlider;

    @BindView(R.id.pb_home)
    ProgressBar pbHome;
    @BindView(R.id.bottom_card)
    CardView bottomCard;

    DatabaseReference vehicleDatabaseRef;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.content_homepage,container,false);
        ButterKnife.bind(this, view);


        pbHome.setVisibility(View.VISIBLE);
        bannerSlider.setVisibility(View.GONE);
        bottomCard.setVisibility(View.GONE);

       // Spinner MySpinner = (Spinner)view.findViewById(R.id.spinner);
       //ArrayAdapter<String> myAdapter = new
       //        ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, places);
       //myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      // MySpinner.setAdapter(myAdapter);
        firebaseAuth = FirebaseAuth.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();
        recyclerView = view.findViewById(R.id.rv_vehicles);

        //RecyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        vehicleDatabaseRef = FirebaseDatabase.getInstance().getReference().child("vehicles").child("shortDetail");




        loadVehicles();

        return view;
    }


    private void loadVehicles(){

        FirebaseRecyclerAdapter<vehiclesList, vehicleViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<vehiclesList, vehicleViewHolder>(
                        vehiclesList.class,
                        R.layout.vehicle_item,
                        vehicleViewHolder.class,
                        vehicleDatabaseRef
                ) {
                    @Override
                    protected void populateViewHolder(vehicleViewHolder viewHolder, vehiclesList model, int position) {


                        //Toast.makeText(myBookingsActivity.this, "first", Toast.LENGTH_SHORT).show();
                        viewHolder.setDetails(getContext(),model.getImageUrl(),model.getVname(),model.getWheelDriven(),model.getPrice());

                        viewHolder.attachListeners(getContext(),model.getVehicleId());

                        pbHome.setVisibility(View.GONE);
                        bannerSlider.setVisibility(View.VISIBLE);
                        bottomCard.setVisibility(View.VISIBLE);


                    }
                };
        recyclerView.setAdapter(firebaseRecyclerAdapter);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        showDefaultBanner();
    }

    private void showDefaultBanner() {

        ArrayList<String> imageUrls = new ArrayList<>();
        String[] default_banners = getContext().getResources().getStringArray(R.array.featured_image);
        for (int i = 0; i < default_banners.length; i++) {
            imageUrls.add(default_banners[i]);
        }
        bannerSlider.setImageUrls(imageUrls);

    }




}
