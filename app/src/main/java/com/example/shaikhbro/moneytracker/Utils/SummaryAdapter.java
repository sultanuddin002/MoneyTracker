package com.example.shaikhbro.moneytracker.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shaikhbro.moneytracker.R;

import java.util.ArrayList;

/**
 * Created by ShaikhBro on 5/14/2017.
 */
public class SummaryAdapter extends ArrayAdapter<WalletSummary> {
    private ArrayList<WalletSummary> arrayList;
    Context mContext;

    // viewholder class which points the reference of elements inside summary_row.xml
    private static class ViewHolder {
        TextView txtDate;
        TextView txtStatus;
        TextView listExpenses;
        TextView txtAmount;
        TextView txtTotal;
    }

    public SummaryAdapter(ArrayList<WalletSummary> arrayList, Context context) {
        super(context, R.layout.summary_row, arrayList);
        this.arrayList = arrayList;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder viewHolder = new ViewHolder();
        WalletSummary walletSummary = getItem(position);
        // first lest verify convertView is not null
        if (convertView == null) {
            // This is a new view we inflate the new layout
//            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.summary_row, parent, false);
            // Now we can fill the layout with right values
            viewHolder.txtDate = (TextView) convertView.findViewById(R.id.current_date);
            viewHolder.txtStatus = (TextView) convertView.findViewById(R.id.funds_status);
            viewHolder.listExpenses = (TextView) convertView.findViewById(R.id.expense_category);
            viewHolder.txtAmount = (TextView) convertView.findViewById(R.id.amount_value);
            viewHolder.txtTotal = (TextView) convertView.findViewById(R.id.total_amount);
            v = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            v = convertView;
        }
        viewHolder.txtDate.setText(walletSummary.getDate());
        viewHolder.txtStatus.setText(walletSummary.getMoneyStatus());
        viewHolder.listExpenses.setText(walletSummary.getExpenseCat());
        viewHolder.txtAmount.setText(String.valueOf(walletSummary.getCurrentMoney()));
        viewHolder.txtTotal.setText(String.valueOf(walletSummary.getTotalMoney()));
        return v;

    }
}
