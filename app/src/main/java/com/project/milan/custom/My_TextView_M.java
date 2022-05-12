package com.project.milan.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.core.content.res.ResourcesCompat;

import com.project.milan.R;

import java.io.Serializable;

/**
 * Created by afi-mac-001 on 31/03/17.
 * Custom textview with play_regular typeface
 */

public class My_TextView_M extends androidx.appcompat.widget.AppCompatTextView implements Serializable {
    private Context mContext;

    public My_TextView_M(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public My_TextView_M(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public My_TextView_M(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface typeface = ResourcesCompat.getFont(mContext, R.font.montserrat_subrayada);
            setTypeface(typeface);
        }
    }
}
