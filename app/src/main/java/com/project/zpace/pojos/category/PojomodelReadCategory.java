
package com.project.zpace.pojos.category;

import java.util.List;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class PojomodelReadCategory {

    @SerializedName("details")
    private List<Detail> mDetails;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("result")
    private String mResult;

    public List<Detail> getDetails() {
        return mDetails;
    }

    public void setDetails(List<Detail> details) {
        mDetails = details;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getResult() {
        return mResult;
    }

    public void setResult(String result) {
        mResult = result;
    }

}
