package com.example.pengfei.androidpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Single.defer()
        Flowable.range(1, 10)
                .toObservable()
                .observeOn(Schedulers.computation())
                .map(v -> v * v)
                .blockingSubscribe(v -> {
                    Log.d("===", v + "");
                });

        Flowable.range(1, 10)
                .flatMap(v ->
                        Flowable.just(v)
                                .subscribeOn(Schedulers.computation())
                                .map(w -> w * w)
                )
                .blockingSubscribe(v -> {
                    Log.d("XXXX", v + "");
                });
    }
}
