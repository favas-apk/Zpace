
package com.project.milan.pojos.category;


import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class Detail {

    @SerializedName("category")
    private String mCategory;
    @SerializedName("fname")
    private String mFname;

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public String getFname() {
        return mFname;
    }

    public void setFname(String fname) {
        mFname = fname;
    }

}
