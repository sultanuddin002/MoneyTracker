package com.example.shaikhbro.moneytracker.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.shaikhbro.moneytracker.R;
import com.example.shaikhbro.moneytracker.Utils.DBCreate;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainMenuFragment extends Fragment {

    Button addMoneyBtn, addExpenseBtn, showSummaryBtn;
    TextView amountText;
    DBCreate dbCreate;

    public MainMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        addMoneyBtn = (Button) view.findViewById(R.id.add_money_btn);
        amountText = (TextView) view.findViewById(R.id.budget_id);
        dbCreate = new DBCreate();
        dbCreate.createDB(getContext());

        int number = (dbCreate.userExists(getContext())) ? dbCreate.getAmount(getContext()) : 0;
        amountText.setText("Balance Rs" + Integer.toString(number));
        addMoneyBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, new AddMoneyFragment()).commit();
            }
        });
        return view;
    }

}
