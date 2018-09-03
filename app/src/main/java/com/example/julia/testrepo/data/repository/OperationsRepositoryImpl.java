package com.example.julia.testrepo.data.repository;

import android.util.Log;

import com.example.julia.testrepo.data.converter.OperationConverter;
import com.example.julia.testrepo.data.local.LocalCache;
import com.example.julia.testrepo.data.remote.OperationsApiMapper;
import com.example.julia.testrepo.models.data.local.OperationListModel;
import com.example.julia.testrepo.models.data.net.OperationListResponse;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by julia on 01.09.18.
 */

public class OperationsRepositoryImpl implements OperationsRepository {

    private Map<LocalCache.LocalCacheKey, Subject> mSubjectMap;
    private LocalCache mCache;
    private OperationsApiMapper mApiMapper;
    private OperationConverter mOperationConverter;

    public OperationsRepositoryImpl() {
        mCache = new LocalCache();
        mApiMapper = new OperationsApiMapper();
        mSubjectMap = new HashMap<>();
        mOperationConverter = new OperationConverter();
    }

    @Override
    public Observable<OperationListModel> getOperations(int from, int to) {
        LocalCache.LocalCacheKey key = new LocalCache.LocalCacheKey(from, to);
        return getSubject(key).startWith(getFromCache(key).toObservable()
                .onErrorResumeNext(error -> {
                    return getOperationsFromNetwork(from, to)
                            .map(response -> {
                                if (response.isSuccess()) {
                                    return mOperationConverter.convertToModel(response);
                                }
                                throw new Exception("error");
                            })
                            .doOnSuccess(successResponse -> cacheOperations(key, (OperationListModel) successResponse))
                            .toObservable();
                }));
    }


    private Single<OperationListModel> getFromCache(LocalCache.LocalCacheKey key) {
        return Single.fromCallable(() -> {
            Log.d("TAG", "-----START GET FROM CACHE -----");
            mCache.print();
            OperationListModel model = mCache.getModel(key);
            if (model == null) {
                throw new Exception();
            }
            Log.d("TAG", model.toString() + "\n" + "-----STOP GET FROM CACHE -----");
            return model;
        });
    }

    private Single<OperationListResponse> getOperationsFromNetwork(int from, int to) {
        return Single.fromCallable(() -> {
            OperationListResponse response = mApiMapper.getOperationsFromNetwork(from, to);
            return response;
        });
    }

    private void cacheOperations(@NonNull LocalCache.LocalCacheKey key,
                                        @NonNull OperationListModel response) {

            mCache.putModel(key, response);
            getSubject(key).onNext(response);

    }

    private Subject<OperationListModel> getSubject(LocalCache.LocalCacheKey key) {
        Subject subject = mSubjectMap.get(key);
        if (subject == null) {
            subject = PublishSubject.create();
            mSubjectMap.put(key, subject);
        }
        return subject;
    }

    @Override
    public void clearCache() {
        mCache.clear();
    }
}
