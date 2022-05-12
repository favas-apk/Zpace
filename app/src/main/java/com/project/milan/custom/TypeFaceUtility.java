package com.project.milan.custom;

import android.content.Context;
import android.graphics.Typeface;

import java.io.Serializable;

/**
 * Created by afi-mac-001 on 31/03/17.
 */

public class TypeFaceUtility implements Serializable {

    public static Typeface play_REGULAR = null;
    public static Typeface play_BOLD = null;


    public static final int PLAY_REGULAR = 1;
    public static final int PLAY_BOLD = 2;

    public static Typeface getTypeFace(Context mContext, int typeface) {
        Typeface mTypeface = null;
        switch (typeface) {
            case PLAY_REGULAR:
                if (play_REGULAR != null)
                    mTypeface = play_REGULAR;
                else {
                    // play_REGULAR = Typeface.createFromAsset(mContext.getAssets(), "Play-Regular.ttf");
                    play_REGULAR = Typeface.createFromAsset(mContext.getAssets(), "montserrat.regular.ttf");
//                    play_REGULAR = Typeface.createFromAsset(mContext.getAssets(), "ave_rom.otf");
                    mTypeface = play_REGULAR;
                }
                break;
            case PLAY_BOLD:
                if (play_BOLD != null)
                    mTypeface = play_BOLD;
                else {
                    //play_BOLD = Typeface.createFromAsset(mContext.getAssets(), "Play-Bold.ttf");
                    play_BOLD = Typeface.createFromAsset(mContext.getAssets(), "Nunito-Bold.ttf");
//                    play_BOLD = Typeface.createFromAsset(mContext.getAssets(), "ave_rom_bold.ttf");
                    mTypeface = play_BOLD;
                }
                break;
            default:
                break;
        }
        return mTypeface;
    }
}
