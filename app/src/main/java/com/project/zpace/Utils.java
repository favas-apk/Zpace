package com.project.zpace;

import android.net.Uri;

public class Utils {


    public static String GVCOT(String search_s) {
        if (search_s == null) {

        } else {
            search_s = "'" + search_s.replaceAll("'", "''") + "'";
        }

        return search_s;
    }

    public static Float GetFloat(String str) {
        float flt=0;
        try
        {
          flt=  Float.parseFloat(str);
        }
        catch (Exception e)
        {
            flt=0;
        }
        return flt;

    }

    public static int GetInt(String str) {
        int intz = 0;

        try {
           intz= Integer.parseInt(str);
        }
        catch (Exception e)
        {

        }
        return intz;

    }


    public static String getURLForResource(int resourceId) {
        //use BuildConfig.APPLICATION_ID instead of R.class.getPackage().getName() if both are not same
        return Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + resourceId).toString();
    }

}
