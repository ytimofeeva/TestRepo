package com.example.julia.testrepo.data.converter;

import com.example.julia.testrepo.models.data.local.OperationListModel;
import com.example.julia.testrepo.models.data.local.OperationModel;
import com.example.julia.testrepo.models.data.net.OperationListResponse;
import com.example.julia.testrepo.models.data.net.OperationRaw;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by julia on 01.09.18.
 */

public class OperationConverter {

    public OperationListModel convertToModel(OperationListResponse response) {
        List<OperationRaw> operations = response.getOperations();
        List<OperationModel> opModels = new ArrayList<>();
        if (operations != null && !operations.isEmpty()) {
            for(OperationRaw op : operations) {
                OperationModel m = new OperationModel(op.getId(), op.getName(), op.isHidden());
                opModels.add(m);
            }
        }
        OperationListModel model = new OperationListModel(opModels);
        return model;
    }
}
