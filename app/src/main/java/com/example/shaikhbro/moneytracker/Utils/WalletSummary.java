package com.example.shaikhbro.moneytracker.Utils;

/**
 * Created by ShaikhBro on 5/13/2017.
 */
public class WalletSummary {
    private String date;
    private String moneyStatus;
    private int currentMoney;
    private int totalMoney;
    private String expenseCat;
    public WalletSummary() {

    }

    public WalletSummary(String date, String moneyStatus, int currentMoney, int totalMoney) {
        this.date = date;
        this.moneyStatus = moneyStatus;
        this.currentMoney = currentMoney;
        this.totalMoney = totalMoney;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getMoneyStatus() {
        return moneyStatus;
    }

    public void setMoneyStatus(String moneyStatus) {
        this.moneyStatus = moneyStatus;
    }

    public int getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(int currentMoney) {
        this.currentMoney = currentMoney;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExpenseCat() {
        return expenseCat;
    }

    public void setExpenseCat(String expenseCat) {
        this.expenseCat = expenseCat;
    }
}
