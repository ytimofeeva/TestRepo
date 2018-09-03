package com.example.julia.testrepo.models.data.local;

/**
 * Created by julia on 01.09.18.
 */

public class OperationModel {

    private int mId;
    private String mName;
    private boolean mIsHidden;

    public OperationModel(int mId, String mName, boolean mIsHidden) {
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

    @Override
    public String toString() {
        return "OperationModel{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mIsHidden=" + mIsHidden +
                '}';
    }
}
