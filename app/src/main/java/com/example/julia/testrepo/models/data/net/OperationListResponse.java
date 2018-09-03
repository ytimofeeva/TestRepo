package com.example.julia.testrepo.models.data.net;

import java.util.List;

/**
 * Created by julia on 01.09.18.
 */

public class OperationListResponse extends BaseResponse {

    private List<OperationRaw> mOperations;

    public OperationListResponse(int connectorStatus,
                                 int statusCode,
                                 List<String> errors,
                                 List<OperationRaw> mOperations) {
        super(connectorStatus, statusCode, errors);
        this.mOperations = mOperations;
    }

    public List<OperationRaw> getOperations() {
        return mOperations;
    }
}
