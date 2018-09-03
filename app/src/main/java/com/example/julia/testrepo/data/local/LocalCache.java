package com.example.julia.testrepo.data.local;

import android.util.Log;

import com.example.julia.testrepo.models.data.local.OperationListModel;
import com.google.common.base.Objects;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.reactivex.annotations.NonNull;

/**
 * Created by julia on 01.09.18.
 */

public class LocalCache {

    private Map<LocalCacheKey,OperationListModel> mStorage;

    public LocalCache() {
        mStorage = new HashMap<>();
    }

    public void putModel(@NonNull LocalCacheKey key,
                         @NonNull OperationListModel model) {
        mStorage.put(key, model);
    }

    public OperationListModel getModel(@NonNull LocalCacheKey key) {
        return mStorage.get(key);
    }

    public void print() {
        Log.d("TAG", "-----START PRINT CACHE -----");
        if (mStorage.isEmpty()) {
            Log.d("TAG", "cache is empty");
            return;
        }
        Set<LocalCacheKey> keySet = mStorage.keySet();
        for (LocalCacheKey key : keySet) {
            Log.d("TAG", "key: " + key.toString()
                    + " op: " + mStorage.get(key).toString());
        }
        Log.d("TAG", "-----END PRINT CACHE -----");
    }

    public void clear() {
        mStorage.clear();
    }

    public static final class LocalCacheKey {
        private int mFrom;
        private int mTo;

        public LocalCacheKey(int from, int to) {
            mFrom = from;
            mTo = to;
        }

        @Override
        public String toString() {
            return "LocalCacheKey{" +
                    "mFrom=" + mFrom +
                    ", mTo=" + mTo +
                    '}';
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            LocalCacheKey that = (LocalCacheKey) obj;
            if (mFrom != that.mFrom) {
                return false;
            }
            return mTo == that.mTo;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(mFrom, mTo);
        }
    }
}
