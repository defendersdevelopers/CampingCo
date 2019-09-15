package com.defenders.campingco.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.defenders.campingco.R;
import com.defenders.campingco.bookingActivity;
import com.defenders.campingco.moreInfoActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class campsiteAdapter extends RecyclerView.ViewHolder {



    View mView;

    public campsiteAdapter(@NonNull View itemView) {
        super(itemView);

        mView = itemView;
    }
    TextView btnMore;
    ImageView img;
    TextView TVtitle,TVdetails;

    String campsiteId;


    public void setDetails(Context ctx,String image,String title,String details){


        img = mView.findViewById(R.id.campsite_image);
        TVtitle =  mView.findViewById(R.id.campsite_title);
        TVdetails =  mView.findViewById(R.id.campsite_details);



        TVtitle.setText(title);
        TVdetails.setText(details);

        Picasso.get().load(image).into(img);

    }
    public void attachListeners(final Context ctx, final String campsiteId){

        this.campsiteId = campsiteId;

        btnMore = mView.findViewById(R.id.campsite_more);


        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(ctx, "available when you give us the project ;-)", Toast.LENGTH_LONG).show();
            }
        });

    }


}
