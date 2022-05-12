
package com.project.milan.pojos.banner;


import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class Detail {

    @SerializedName("fname")
    private String mFname;
    @SerializedName("slno")
    private String mSlno;

    public String getFname() {
        return mFname;
    }

    public void setFname(String fname) {
        mFname = fname;
    }

    public String getSlno() {
        return mSlno;
    }

    public void setSlno(String slno) {
        mSlno = slno;
    }

}
