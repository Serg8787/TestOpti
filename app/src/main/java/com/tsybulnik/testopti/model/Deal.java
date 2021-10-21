package com.tsybulnik.testopti.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "deals")
public class Deal {
    @PrimaryKey(autoGenerate=true)
    public long id;
    private String NameTransaction;
    private String autoComplete;
    private String sum;
    private String date;

    public Deal() {
    }

    public Deal(String nameTransaction, String autoComplete, String sum, String date) {
        NameTransaction = nameTransaction;
        this.autoComplete = autoComplete;
        this.sum = sum;
        this.date = date;
    }



    public String getNameTransaction() {
        return NameTransaction;
    }

    public void setNameTransaction(String nameTransaction) {
        NameTransaction = nameTransaction;
    }

    public String getAutoComplete() {
        return autoComplete;
    }

    public void setAutoComplete(String autoComplete) {
        this.autoComplete = autoComplete;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
