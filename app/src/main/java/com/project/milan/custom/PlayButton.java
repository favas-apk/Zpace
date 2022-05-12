package com.project.milan.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import java.io.Serializable;

/**
 * Created by afi-mac-001 on 31/03/17.
 */

public class PlayButton extends androidx.appcompat.widget.AppCompatButton implements Serializable {
    private Context mContext;

    public PlayButton(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public PlayButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public PlayButton(Context context, AttributeSet attrs, int defStyleAttr) {
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
