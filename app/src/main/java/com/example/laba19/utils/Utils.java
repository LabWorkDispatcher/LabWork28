package com.example.laba19.utils;

import static com.example.laba19.data.Constants.APP_MEASUREMENT_TYPE_DEGREES;
import static com.example.laba19.data.Constants.APP_MEASUREMENT_TYPE_GRADIANS;
import static com.example.laba19.data.Constants.APP_MEASUREMENT_TYPE_RADIANS;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.laba19.R;
import com.example.laba19.UI.MainActivity;
import com.example.laba19.UI.SecondaryActivity;
import com.example.laba19.UI.TertiaryActivity;

public class Utils {
    public static void convertToReadableNumber(EditText editText) {
        String num = editText.getText().toString();
        Log.d("EDITTEXT_CHECKER", num);
        if (num.isEmpty()) {
            return;
        }

        if (num.charAt(0) == '.') {
            if (num.length() == 1) {
                num = "";
            } else {
                num = "0" + num;
            }
        }

        editText.setText(num);
    }

    public static void moveToActivity(AppCompatActivity currActivity, int itemId) {
        Class newActivity;
        if (R.id.calcButton == itemId) {
            newActivity = MainActivity.class;
        } else if (R.id.equalButton == itemId) {
            newActivity = SecondaryActivity.class;
        } else if (R.id.trigButton == itemId) {
            newActivity = TertiaryActivity.class;
        } else {
            throw(new RuntimeException("Unknown menu button was pressed."));
        }

        if (currActivity.getClass() == newActivity) {
            Log.d("APP_DEBUGGER", "This activity is already active.");
            return;
        }
        Intent i = new Intent(currActivity.getApplicationContext(), newActivity);
        currActivity.startActivity(i);
        currActivity.finish();
    }

    public static int getMeasurementString(int measurementType) {
        if (measurementType == APP_MEASUREMENT_TYPE_DEGREES) {
            return R.string.measure_type_degree;
        } else if (measurementType == APP_MEASUREMENT_TYPE_RADIANS) {
            return R.string.measure_type_radian;
        } else if (measurementType == APP_MEASUREMENT_TYPE_GRADIANS) {
            return R.string.measure_type_gradian;
        } else {
            throw(new RuntimeException("Unknown measurement type was chosen."));
        }
    }
}
