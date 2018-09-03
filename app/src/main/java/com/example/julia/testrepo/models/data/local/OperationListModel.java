package com.example.julia.testrepo.models.data.local;

import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * Created by julia on 01.09.18.
 */

public class OperationListModel {

    private List<OperationModel> mOperations;

    public OperationListModel(@NonNull List<OperationModel> operations) {
        mOperations = operations;
    }

    public List<OperationModel> getOperations() {
        return mOperations;
    }

    @Override
    public String toString() {
        return "OperationListModel{" +
                "mOperations=" + mOperations +
                '}';
    }
}
