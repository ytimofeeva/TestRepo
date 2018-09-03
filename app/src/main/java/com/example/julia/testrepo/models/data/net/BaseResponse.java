package com.example.julia.testrepo.models.data.net;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.Nullable;

/**
 * Created by julia on 01.09.18.
 */

public class BaseResponse {

    private int mConnectorStatus;
    private int mStatusCode;
    private List<String> mErrors = new ArrayList<>();

    public BaseResponse(
            int connectorStatus,
            int statusCode,
            @Nullable List<String> errors
    ) {
        mConnectorStatus = connectorStatus;
        mStatusCode = statusCode;
        if (errors != null) {
            mErrors = errors;
        }
    }

    public int getConnectorStatus() {
        return mConnectorStatus;
    }

    public int getStatusCode() {
        return mStatusCode;
    }

    public List<String> getErrors() {
        return mErrors;
    }

    public boolean isSuccess() {
        return mConnectorStatus == 0 &&
                mStatusCode == 0 &&
                mErrors.isEmpty();
    }
}
