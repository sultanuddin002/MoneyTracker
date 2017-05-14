package com.example.shaikhbro.moneytracker.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.shaikhbro.moneytracker.R;
import com.example.shaikhbro.moneytracker.Utils.DBCreate;
import com.example.shaikhbro.moneytracker.Utils.SummaryAdapter;
import com.example.shaikhbro.moneytracker.Utils.WalletSummary;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {

    ListView summaryListView;
    DBCreate dbCreate;
    WalletSummary walletSummary;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_summary, container, false);
        summaryListView = (ListView) view.findViewById(R.id.summary_items);

        //check if WalletSummary have data or not
        dbCreate = new DBCreate();
        walletSummary = new WalletSummary();
        walletSummary = dbCreate.getWalletSummary(getContext());
        if (walletSummary != null) {
            ArrayList<WalletSummary> summaries = new ArrayList<>();
            String moneyStatusChange = !walletSummary.getExpenseCat().isEmpty() ? "EXPENSES" : "MONEY ADDED";
            walletSummary.setMoneyStatus(moneyStatusChange);
            summaries.add(walletSummary);
            SummaryAdapter summaryAdapter = new SummaryAdapter(summaries, getContext());
            summaryListView.setAdapter(summaryAdapter);
        }
        return view;
    }

}
