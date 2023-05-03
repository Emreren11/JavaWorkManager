package com.emre.javaworkmanager;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class RefreshDatabase extends Worker {

    Context myContext;

    public RefreshDatabase(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.myContext = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        //Arka planda yapılacaklar için kod alanı
        Data data = getInputData(); // verilen inputu alır
        int myNumber = data.getInt("intKey", 0);
        refreshDatabase(myNumber);
        return Result.success();
    }
    private void refreshDatabase(int myNumber) {
        SharedPreferences sharedPreferences = myContext.getSharedPreferences("com.emre.javaworkmanager", Context.MODE_PRIVATE);
        int mySavedNo = sharedPreferences.getInt("myNumber", 0);
        mySavedNo += myNumber;
        System.out.println(mySavedNo);
        sharedPreferences.edit().putInt("myNumber", mySavedNo).apply();
    }
}
