package com.example.shaikhbro.moneytracker.Utils;

import android.content.Context;
import android.icu.math.BigDecimal;

import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;

/**
 * Created by ShaikhBro on 5/6/2017.
 */
public class DBCreate {

    private DB snappyDB;
    final String USER_ONE = "USER_ONE";

    final String DB_NAME = "MONEY_TRACKER";

    public void createDB(Context context) {
        try {
            snappyDB = DBFactory.open(context, DB_NAME);
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    public void closeDB(Context context) {
        try {
            snappyDB = DBFactory.open(context, DB_NAME);
            snappyDB.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    public void saveAmount(Context context, int amount) {

        try {
            snappyDB = DBFactory.open(context, DB_NAME);
            if (snappyDB.exists(USER_ONE)) {
                snappyDB.putInt(USER_ONE, amount + snappyDB.getInt(USER_ONE));
            } else {
                snappyDB.putInt(USER_ONE, amount);
            }

        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    public int getAmount(Context context) {
        int amount = 0;
        try {
            snappyDB = DBFactory.open(context, DB_NAME);
            if (snappyDB.exists(USER_ONE)) {
                return snappyDB.getInt(USER_ONE);
            }


        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return amount;
    }

    public boolean userExists(Context context) {
        boolean isExists = false;
        try {
            snappyDB = DBFactory.open(context, DB_NAME);
            isExists = snappyDB.exists(USER_ONE);
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return isExists;
    }

    public int subtractAmount(Context context, int amountSubtract) {
        int currentAmount = getAmount(context);

        try {
            snappyDB = DBFactory.open(context);
            if (snappyDB.exists(USER_ONE)) {
                if (currentAmount <= 0) {
                    currentAmount = currentAmount - amountSubtract;
                    return currentAmount;
                } else {
                    currentAmount = 0;
                    return currentAmount;
                }
            }
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return currentAmount;

    }
}