package com.aqua30.whatsappanimation;

import android.view.View;


/**
 * Created by Saurabh(aqua) on 02-11-2016.
 */

public class ViewHelper {

    static class Font {
       /*
        * Custom fonts typeface
        * */
        public static final String Font_Regular = "font_medium.ttf";
        public static final String Font_Light = "font_light.ttf";
        public static final String Font_Bold = "font_bold.ttf";
    }

    public static void setViewVisibility(View v, boolean isVisible){
        v.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

}