package com.defenders.campingco.utils;

import android.content.Context;
import android.graphics.Typeface;

import com.defenders.campingco.main.appMain;

public class FontManager {

    public static final String FONT_AWESOME = "font.ttf";

    private static Context context;
    private static FontManager mInstance;

    public FontManager() {
        FontManager.context = appMain.getContext();
    }

    public static FontManager getInstance() {
        if (mInstance == null) {
            mInstance = new FontManager();
        }
        return mInstance;
    }

    public Typeface getTypeFace() {
        return Typeface.createFromAsset(context.getAssets(), FONT_AWESOME);
    }

}
