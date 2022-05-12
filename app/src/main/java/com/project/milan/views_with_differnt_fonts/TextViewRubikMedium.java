package com.project.milan.views_with_differnt_fonts;

import android.content.Context;
import android.graphics.Typeface;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by UITOUX5 on 06/03/18.
 */

public class TextViewRubikMedium extends AppCompatTextView {
    public TextViewRubikMedium(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/PTC55F.ttf"));
    }
    
}
