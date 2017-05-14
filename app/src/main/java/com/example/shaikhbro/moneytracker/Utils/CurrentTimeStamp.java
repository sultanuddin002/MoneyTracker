package com.example.shaikhbro.moneytracker.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ShaikhBro on 5/13/2017.
 */
public class CurrentTimeStamp {

    public String getCurrentTimeStamp() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(new Date());

    }

}
