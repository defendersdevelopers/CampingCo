package com.defenders.campingco;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.defenders.campingco.Menu.DrawerAdapter;
import com.defenders.campingco.Menu.DrawerItem;
import com.defenders.campingco.Menu.SimpleItem;
import com.defenders.campingco.fragments.aboutFragment;
import com.defenders.campingco.fragments.campsitesFragment;
import com.defenders.campingco.fragments.faqFragment;
import com.defenders.campingco.fragments.homeFragment;
import com.defenders.campingco.utils.ConnectionUtils;
import com.defenders.campingco.utils.ConnectivityErrorDialog;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;
import java.util.Arrays;

public class tabsActivity extends AppCompatActivity implements ConnectivityErrorDialog.TryAgainListener,TabLayout.OnTabSelectedListener,DrawerAdapter.OnItemSelectedListener{

    private SlidingRootNav slidingRootNav;
    private static final int POS_HOME = 0;
    private static final int POS_CAMPSITES = 1;
    private static final int POS_ABOUT = 2;
    private static final int POS_FAQ = 3;
    FrameLayout fl1,fl2,fl3,fl4;
    private String[] screenTitles;
    private Drawable[] screenIcons;
    View contextView;
    Boolean doubleBackToExitPressedOnce = false;
    private ConnectivityErrorDialog connectivityErrorDialog;
    private tabsActivity.NetworkChangeReceiver networkChangeReceiver;
    private boolean networkChangeReceiverRegistered;
    DrawerAdapter drawadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contextView = findViewById(android.R.id.content);
        networkChangeReceiver = new tabsActivity.NetworkChangeReceiver();
        connectivityErrorDialog = new ConnectivityErrorDialog(this);
        connectivityErrorDialog.setCancelable(false);
        connectivityErrorDialog.setTryAgainListener(this);

        fl1 = findViewById(R.id.frame_tabs1);
        fl2 = findViewById(R.id.frame_tabs2);
        fl3 = findViewById(R.id.frame_tabs3);
        fl4 = findViewById(R.id.frame_tabs4);
        fl1.setVisibility(View.VISIBLE);
        fl2.setVisibility(View.GONE);
        fl3.setVisibility(View.GONE);
        fl4.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_tabs1,new homeFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_tabs2,new campsitesFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_tabs3,new aboutFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_tabs4,new faqFragment()).commit();


        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(true)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();


        drawadapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_HOME).setChecked(true),
                createItemFor(POS_CAMPSITES),
                createItemFor(POS_ABOUT),
                createItemFor(POS_FAQ)));
        drawadapter.setListener(this);

        RecyclerView list = findViewById(R.id.list_navigation);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(drawadapter);

        drawadapter.setSelected(POS_HOME);


    }


    @Override
    public void onItemSelected(int position) {
        if (position == POS_HOME) {
            fl1.setVisibility(View.VISIBLE);
            fl2.setVisibility(View.GONE);
            fl3.setVisibility(View.GONE);
            fl4.setVisibility(View.GONE);


        }
        if (position == POS_CAMPSITES) {
            fl1.setVisibility(View.GONE);
            fl2.setVisibility(View.VISIBLE);
            fl3.setVisibility(View.GONE);
            fl4.setVisibility(View.GONE);
        }

        if (position == POS_ABOUT) {

            fl1.setVisibility(View.GONE);
            fl2.setVisibility(View.GONE);
            fl3.setVisibility(View.VISIBLE);
            fl4.setVisibility(View.GONE);
        }

        if (position == POS_FAQ) {
            fl1.setVisibility(View.GONE);
            fl2.setVisibility(View.GONE);
            fl3.setVisibility(View.GONE);
            fl4.setVisibility(View.VISIBLE);
        }

        slidingRootNav.closeMenu();

    }



    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.textColorSecondary))
                .withTextTint(color(R.color.textColorSecondary))
                .withSelectedIconTint(color(R.color.navfun))
                .withSelectedTextTint(color(R.color.navfun));
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }
    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }
    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    @Override
    public void onBackPressed() {

        drawadapter.setSelected(POS_HOME);

        if (doubleBackToExitPressedOnce) {
            //finishAffinity();
            finishAffinity();
            return;
        }
        slidingRootNav.closeMenu();
        this.doubleBackToExitPressedOnce = true;
        Snackbar.make(contextView, "Press again to exit", 2000).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);



    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!networkChangeReceiverRegistered) {
            registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
            networkChangeReceiverRegistered = true;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (networkChangeReceiverRegistered) {
            unregisterReceiver(networkChangeReceiver);
            networkChangeReceiverRegistered = false;
        }

    }


    @Override
    public void onTryAgain() {
        if (ConnectionUtils.isConnected(this)) {
            hideConnectivityDialog();
        }
    }


    public class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {

                if (ConnectionUtils.isConnected(tabsActivity.this)) {
                    hideConnectivityDialog();
                } else {
                    showConnectivityErrorDialog();
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

    }

    private void showConnectivityErrorDialog() {

        if (connectivityErrorDialog != null) {
            connectivityErrorDialog.show();
        }
    }

    private void hideConnectivityDialog() {
        if (connectivityErrorDialog != null) {
            connectivityErrorDialog.dismiss();
        }
    }

}


