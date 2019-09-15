package com.defenders.campingco;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.defenders.campingco.imageSlider.AutoSlideBannerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class moreInfoActivity extends AppCompatActivity {



    DatabaseReference vehicleInfoRef;

    TextView vname,vdetails;
    @BindView(R.id.vehicle_image_slider)
    AutoSlideBannerView imageSlider;
    String imageUrl1,imageUrl2,imageUrl3;
    ArrayList<String> imageUrls = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_info_layout);
        ButterKnife.bind(this);


       vname = findViewById(R.id.v_name);
       vdetails = findViewById(R.id.v_details);






        String vehicleId = getIntent().getExtras().get("vehicleId").toString();


        vehicleInfoRef = FirebaseDatabase.getInstance().getReference().child("vehicles").child("fullInfo").child(vehicleId);


        vehicleInfoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                imageUrl1 = dataSnapshot.child("imageUrl1").getValue().toString();
                imageUrl2 = dataSnapshot.child("imageUrl2").getValue().toString();
                imageUrl3 = dataSnapshot.child("imageUrl3").getValue().toString();

                imageUrls.add(imageUrl1);
                imageUrls.add(imageUrl2);
                imageUrls.add(imageUrl3);
                imageSlider.setImageUrls(imageUrls);



                vname.setText(dataSnapshot.child("vname").getValue().toString());
                vdetails.setText(dataSnapshot.child("vdetails").getValue().toString());



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });






    }


}
