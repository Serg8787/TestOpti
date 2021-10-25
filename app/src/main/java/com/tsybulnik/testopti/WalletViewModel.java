package com.tsybulnik.testopti;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.tsybulnik.testopti.database.DealsDatabase;
import com.tsybulnik.testopti.model.Deal;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class WalletViewModel extends AndroidViewModel{

    private DealsDatabase dealsDatabase;



    public WalletViewModel(Application application) {
        super(application);
        dealsDatabase = DealsDatabase.newInstance(application);
    }
    Flowable<List<Deal>> getList() {
        return dealsDatabase.dealDao().getAll();
    }

    void insert(Deal deal){
        dealsDatabase.dealDao().insert(deal).subscribeOn(Schedulers.io()).subscribe();
    }
}
