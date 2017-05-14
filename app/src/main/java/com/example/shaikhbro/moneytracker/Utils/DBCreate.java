package com.example.shaikhbro.moneytracker.Utils;

import android.content.Context;
import android.icu.math.BigDecimal;

import com.example.shaikhbro.moneytracker.fragment.SummaryFragment;
import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;

/**
 * Created by ShaikhBro on 5/6/2017.
 */
public class DBCreate {

    private DB snappyDB;
    final String USER_ONE = "USER_FIVE";

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
                snappyDB.close();
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
                amount = snappyDB.getInt(USER_ONE);
                return amount;
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

    public void saveExpense(Context context, int amountSubtract) {
        // WORKING FINE WHEN NEW USER KEY IS MADE
        int currentAmount = getAmount(context);
        try {

            snappyDB = DBFactory.open(context, DB_NAME);
            if (snappyDB.exists(USER_ONE)) {
                if (currentAmount >= amountSubtract) {
                    snappyDB.putInt(USER_ONE, currentAmount - amountSubtract);
                    snappyDB.close();
                }
            }
        } catch (SnappydbException e) {
            e.printStackTrace();

        }


    }

    public void saveWalletSummary(Context context, WalletSummary walletSummary) {
        try {
            snappyDB = DBFactory.open(context);
            if (snappyDB.exists(USER_ONE)) {
                snappyDB.put(USER_ONE, walletSummary);
            }
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    public WalletSummary getWalletSummary(Context context) {
        WalletSummary walletSummary = new WalletSummary();
        try {
            snappyDB = DBFactory.open(context);
            if (snappyDB.exists(USER_ONE)) {
                walletSummary = snappyDB.getObject(USER_ONE, WalletSummary.class);
            }
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return walletSummary;
    }
}