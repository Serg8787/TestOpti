package com.tsybulnik.testopti;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tsybulnik.testopti.database.DealsDatabase;
import com.tsybulnik.testopti.model.Deal;

import java.util.ArrayList;
import java.util.List;

public class ViewModelDeal extends AndroidViewModel {
    public static DealsDatabase database;
    private MutableLiveData<ArrayList<Deal>> dealsLivedata;
    private ArrayList<Deal> dealArrayList;

    public ViewModelDeal(Application application) {
        super(application);
        dealsLivedata = new MutableLiveData<>();
        database = DealsDatabase.newInstance(getApplication());


    }
    MutableLiveData<ArrayList<Deal>> getUserMutableLiveData() {
        return dealsLivedata;
    }
    private void init(){
       dealArrayList = (ArrayList<Deal>) database.dealDao().getAll();
        dealsLivedata.setValue(dealArrayList);


    }
}
