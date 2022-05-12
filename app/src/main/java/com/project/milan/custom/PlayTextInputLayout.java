package com.project.milan.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.google.android.material.textfield.TextInputLayout;

import java.io.Serializable;

/**
 * Created by afi-mac-001 on 03/04/17.
 */

public class PlayTextInputLayout extends TextInputLayout implements Serializable {
    private final Context mContext;

    public PlayTextInputLayout(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public PlayTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public PlayTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface typeface = TypeFaceUtility.getTypeFace(mContext, TypeFaceUtility.PLAY_REGULAR);
            setTypeface(typeface);
        }
    }
}
