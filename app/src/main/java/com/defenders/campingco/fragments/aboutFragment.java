package com.defenders.campingco.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.defenders.campingco.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class aboutFragment extends Fragment {


    View view;
    private boolean rotate = false;
    private View lyt_web;
    private View lyt_call;
    private View back_drop;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_about,container,false);


        back_drop = view.findViewById(R.id.back_drop);

        final FloatingActionButton fab_web = (FloatingActionButton) view.findViewById(R.id.fab_web);
        final FloatingActionButton fab_call = (FloatingActionButton) view.findViewById(R.id.fab_call);
        final FloatingActionButton fab_add = (FloatingActionButton) view.findViewById(R.id.fab_add);
        lyt_web = view.findViewById(R.id.lyt_web);
        lyt_call = view.findViewById(R.id.lyt_call);
        initShowOut(lyt_web);
        initShowOut(lyt_call);
        back_drop.setVisibility(View.GONE);

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFabMode(v);
            }
        });

        back_drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFabMode(fab_add);
            }
        });

        fab_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.getContext().startActivity(
                        new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.camping-co.com")));
            }
        });

        fab_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "number not available", Toast.LENGTH_SHORT).show();
            }
        });

        view.findViewById(R.id.youtube).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.getContext().startActivity(
                        new Intent(Intent.ACTION_VIEW, Uri.parse("http://https://www.youtube.com/channel/UC6mvUrWd2m4KbokMSALM0Hw")));
            }
        });
        view.findViewById(R.id.facebook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.getContext().startActivity(
                        new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/thecampingco")));
            }
        });
        view.findViewById(R.id.instagram).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.getContext().startActivity(
                        new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/camping_co")));
            }
        });

        return view;
    }


    private void toggleFabMode(View v) {
        rotate = rotateFab(v, !rotate);
        if (rotate) {
            showIn(lyt_web);
            showIn(lyt_call);
            back_drop.setVisibility(View.VISIBLE);
        } else {
            showOut(lyt_web);
            showOut(lyt_call);
            back_drop.setVisibility(View.GONE);
        }
    }

    public static void initShowOut(final View v) {
        v.setVisibility(View.GONE);
        v.setTranslationY(v.getHeight());
        v.setAlpha(0f);
    }
    public static void showIn(final View v) {
        v.setVisibility(View.VISIBLE);
        v.setAlpha(0f);
        v.setTranslationY(v.getHeight());
        v.animate()
                .setDuration(200)
                .translationY(0)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                })
                .alpha(1f)
                .start();
    }
    public static void showOut(final View v) {
        v.setVisibility(View.VISIBLE);
        v.setAlpha(1f);
        v.setTranslationY(0);
        v.animate()
                .setDuration(200)
                .translationY(v.getHeight())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        v.setVisibility(View.GONE);
                        super.onAnimationEnd(animation);
                    }
                }).alpha(0f)
                .start();
    }
    public static boolean rotateFab(final View v, boolean rotate) {
        v.animate().setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                })
                .rotation(rotate ? 135f : 0f);
        return rotate;
    }




}
