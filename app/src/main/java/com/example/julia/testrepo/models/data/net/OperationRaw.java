package com.example.julia.testrepo.models.data.net;

/**
 * Created by julia on 01.09.18.
 */

public class OperationRaw {

    private int mId;
    private String mName;
    private boolean mIsHidden;

    public OperationRaw(int mId, String mName, boolean mIsHidden) {
        this.mId = mId;
        this.mName = mName;
        this.mIsHidden = mIsHidden;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public boolean isHidden() {
        return mIsHidden;
    }
}
