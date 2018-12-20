package com.example.ravikiran.hellodoctor;

import android.content.Context;

import java.util.ArrayList;

public class hours {

    public ArrayList<String> getHoursFromBinary(Context context, String bin) {
        String[] hours = bin.split("");

        for (int i=1; i<hours.length; i++)  {
            if (hours[i].equals("1"))   {
                if      (i == 1)    hours[i] = context.getString(R.string.hour_01);
                else if (i == 2)    hours[i] = context.getString(R.string.hour_02);
                else if (i == 3)    hours[i] = context.getString(R.string.hour_03);
                else if (i == 4)    hours[i] = context.getString(R.string.hour_04);
                else if (i == 5)    hours[i] = context.getString(R.string.hour_05);
                else if (i == 6)    hours[i] = context.getString(R.string.hour_06);
                else if (i == 7)    hours[i] = context.getString(R.string.hour_07);
                else if (i == 8)    hours[i] = context.getString(R.string.hour_08);
                else if (i == 9)    hours[i] = context.getString(R.string.hour_09);
                else if (i == 10)   hours[i] = context.getString(R.string.hour_10);
                else if (i == 11)   hours[i] = context.getString(R.string.hour_11);
                else if (i == 12)   hours[i] = context.getString(R.string.hour_12);
            }
        }

        ArrayList<String> hours_available = new ArrayList<>();
        for (String s : hours) {
            if (!(s.equals("0") || s.equals("")))  hours_available.add(s);
        }
        return hours_available;
    }

    public String getNumberFromHours(String hour, String old_binary) {
        StringBuilder new_binary = new StringBuilder();
        int hour_hour = Integer.valueOf(hour.substring(0,2));
        if      (hour_hour >= 9)   hour_hour -= 8;
        else                        hour_hour += 4;

        String[] hours_char = old_binary.split("");
        hours_char[hour_hour] = "0";

        for (String s : hours_char) {
            new_binary.append(s);
        }
        return new_binary.toString();
    }
}
