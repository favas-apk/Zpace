package com.project.zpace.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;

/**
 * Created by afi-mac-001 on 31/03/17.
 * Custom textview with play_regular typeface
 */

public class My_TextView extends androidx.appcompat.widget.AppCompatTextView implements Serializable {
    private Context mContext;

    public My_TextView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public My_TextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public My_TextView(Context context, AttributeSet attrs, int defStyleAttr) {
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
