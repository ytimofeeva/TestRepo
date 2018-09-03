package com.example.julia.testrepo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.julia.testrepo.data.repository.OperationsRepository;
import com.example.julia.testrepo.data.repository.OperationsRepositoryImpl;
import com.example.julia.testrepo.models.data.local.OperationListModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton;
    private BehaviorSubject<String> mSubject;
    private OperationsRepository mRepository;
    private Disposable mDisposable1;
    private Disposable mDisposable2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mSubject = BehaviorSubject.create();
        mRepository = new OperationsRepositoryImpl();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDisposable1 = mRepository.getOperations(1,10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::loadDataSuccess, this::error);
     /*   mSubject.startWith(Observable.just("just!"))
                .subscribe(this::success, this::error, this::complete); */
    }

    private void loadDataSuccess(OperationListModel model) {
        Log.d("TAG", "loadDataSuccess " + model.toString());
    }
    private void success(String data) {
        Log.d("TAG", "success " + data);
    }

    private void error(Throwable throwable) {
        Log.d("TAG", "error " + throwable.getMessage());
    }

    private void complete() {
        Log.d("TAG", "complete");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button) {
            mDisposable2 = mRepository.getOperations(1,10)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::loadDataSuccess, this::error);
        }
        Log.d("TAG", "disposable 1 " + mDisposable1.isDisposed() );
        Log.d("TAG", "disposable 2 " + mDisposable2.isDisposed() );
    }
}
