package com.example.shaikhbro.moneytracker.fragment;


import android.icu.math.BigDecimal;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shaikhbro.moneytracker.R;
import com.example.shaikhbro.moneytracker.Utils.DBCreate;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddMoneyFragment extends Fragment {

    Button saveAmountBtn;
    EditText amount_value;
    DBCreate dbCreate;

    public AddMoneyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_money, container, false);
        saveAmountBtn = (Button) view.findViewById(R.id.add_amount_save_btn);
        amount_value = (EditText) view.findViewById(R.id.add_amount);
        amount_value.setText("0");

        saveAmountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int amount = 0;
                if (!(amount_value.getText().toString().matches(""))) {
                    amount = new Integer((amount_value).getText().toString()).intValue();
                }
                if ( amount != 0) {
                    dbCreate = new DBCreate();
                    dbCreate.saveAmount(getContext(), amount);
                    Toast.makeText(getContext(), "your Money is added successfully", Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, new MainMenuFragment()).commit();
                        }
                    }, 2000);
                } else {
                    Toast.makeText(getContext(), "No amount is entered", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}
