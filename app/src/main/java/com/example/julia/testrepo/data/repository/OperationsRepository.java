package com.example.julia.testrepo.data.repository;

import com.example.julia.testrepo.models.data.local.OperationListModel;

import io.reactivex.Observable;

/**
 * Created by julia on 01.09.18.
 */

public interface OperationsRepository {

    Observable<OperationListModel> getOperations(
                                                 int from,
                                                 int to);

    void clearCache();
}
