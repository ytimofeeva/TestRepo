package com.example.julia.testrepo.data.remote;

import android.util.Log;

import com.example.julia.testrepo.models.data.net.OperationListResponse;
import com.example.julia.testrepo.models.data.net.OperationRaw;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by julia on 01.09.18.
 */

public class OperationsApiMapper {

    private  int counter = 0;

    public OperationListResponse getOperationsFromNetwork(int from,
                                                          int to) {
        Log.d("TAG", "-----START NETWORK REQUEST-----" + Thread.currentThread());
        try {
            Thread.sleep(1000);
            counter++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("TAG", "-----STOP NETWORK REQUEST-----");
        if (counter == 1) {
            return generateNetworkFailureResponse();
        } else {
            return generateOperationsList();
        }
    }

    private OperationRaw generateOperation(int id,
                                          String name,
                                          boolean hidden) {
        return new OperationRaw(id, name, hidden);
    }

    private OperationListResponse generateOperationsList() {
        List<OperationRaw> operations = new ArrayList<>();
        operations.add(generateOperation(1, "Train ticket", false));
        operations.add(generateOperation(2, "Coffee", false));
        operations.add(generateOperation(3, "Chocolate", false));
        OperationListResponse response = new OperationListResponse(0,
                0,
                null,
                operations);
        return response;
    }

    private OperationListResponse generateNetworkFailureResponse() {
        return new OperationListResponse(1,0, null, null);
    }

    private OperationListResponse generateServerFailureResponse() {
        List<String> errors = new ArrayList<>();
        errors.add("Access Denied");
        return new OperationListResponse(0,1, errors, null);
    }
}
