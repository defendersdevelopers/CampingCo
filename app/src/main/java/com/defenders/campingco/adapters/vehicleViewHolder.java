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
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class vehicleViewHolder extends RecyclerView.ViewHolder {



    View mView;
    Button btnBook,btnMore;
    ImageView img;
    TextView TVname,TVwheelDriven,TVprice;

    String vehicleId;

    public vehicleViewHolder(@NonNull View itemView) {
        super(itemView);

        mView = itemView;
    }

    public void setDetails(Context ctx,String image,String name,String wheelDriven,String price){


        img = mView.findViewById(R.id.v_image);
        TVname =  mView.findViewById(R.id.v_name);
        TVwheelDriven =  mView.findViewById(R.id.v_wheelDriven);
        TVprice =  mView.findViewById(R.id.v_price);



        TVname.setText(name);
        TVwheelDriven.setText(wheelDriven);
        TVprice.setText("INR "+price+"*");
        Picasso.get().load(image).into(img);

    }


    public void attachListeners(final Context ctx, final String vehicleId){

        this.vehicleId = vehicleId;

        btnBook = mView.findViewById(R.id.btn_book);
        btnMore = mView.findViewById(R.id.btn_more);


        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(ctx, bookingActivity.class);
               // intent.putExtra("vehicleId",vehicleId);
                //ctx.startActivity(intent);
                Toast.makeText(ctx, "available when you give us the project ;-)", Toast.LENGTH_LONG).show();

            }
        });

        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, moreInfoActivity.class);
                intent.putExtra("vehicleId",vehicleId);
                ctx.startActivity(intent);
            }
        });

    }

}
