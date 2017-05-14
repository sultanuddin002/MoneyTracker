package com.example.shaikhbro.moneytracker.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.shaikhbro.moneytracker.R;
import com.example.shaikhbro.moneytracker.Utils.CurrentTimeStamp;
import com.example.shaikhbro.moneytracker.Utils.DBCreate;
import com.example.shaikhbro.moneytracker.Utils.WalletSummary;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddExpenseFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    EditText expenseAmount;
    Button saveBtn;
    DBCreate dbCreate;

    public AddExpenseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_expense, container, false);
        spinner = (Spinner) view.findViewById(R.id.expense_spinner);
        saveBtn = (Button) view.findViewById(R.id.add_amount_expense_save_btn);
        expenseAmount = (EditText) view.findViewById(R.id.amount_expense);

        spinner.setOnItemSelectedListener(this);
        spinner.setPrompt(getResources().getString(R.string.select_expense_text));
        //create an ArrayAdapter using the string array and default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.category_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //save amount for the expense
        saveBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int amount = 0;
                dbCreate = new DBCreate();
                if (!(expenseAmount.getText().toString().matches(""))) {
                    amount = new Integer((expenseAmount).getText().toString()).intValue();
                }

                int tempAmount = dbCreate.getAmount(getContext());
                if (amount != 0) {
                    dbCreate.saveExpense(getContext(), amount);
                    if (tempAmount != dbCreate.getAmount(getContext())) {


                        // when the money is stored successfully, add date,money which is add and current money in the wallet in the Walletsummary Object inside the database
                        WalletSummary walletSummary = new WalletSummary();
                        walletSummary.setDate(new CurrentTimeStamp().getCurrentTimeStamp());
                        walletSummary.setCurrentMoney(amount);
                        walletSummary.setMoneyStatus("Expense Added");
                        walletSummary.setTotalMoney(dbCreate.getAmount(getContext()));
                        walletSummary.setExpenseCat(spinner.getSelectedItem().toString());
                        // save the WalletSummary object in DB
                        dbCreate.saveWalletSummary(getContext(), walletSummary);

                        Toast.makeText(getContext(), "your Expense is added successfully", Toast.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, new MainMenuFragment()).commit();

                            }
                        }, 2000);
                    }
                }
                if (tempAmount == dbCreate.getAmount(getContext())) {
                    Toast.makeText(getContext(), "You DONT have The That much Budget. Please Add more cash to your account", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "No amount is entered", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getContext(), adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
