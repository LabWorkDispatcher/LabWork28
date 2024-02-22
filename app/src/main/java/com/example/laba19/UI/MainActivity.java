package com.example.laba19.UI;

import static com.example.laba19.utils.Utils.moveToActivity;
import static com.example.laba19.utils.Utils.convertToReadableNumber;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItemView;

import com.example.laba19.R;
import com.example.laba19.databinding.ActivityMainBinding;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.sum.setOnClickListener(view -> {
            if (!performEmptinessCheck()) {
                return;
            }
            confirmNumbers();
            doSum();
        });
        binding.sub.setOnClickListener(view -> {
            if (!performEmptinessCheck()) {
                return;
            }
            confirmNumbers();
            doSub();
        });
        binding.mul.setOnClickListener(view -> {
            if (!performEmptinessCheck()) {
                return;
            }
            confirmNumbers();
            doMul();
        });
        binding.div.setOnClickListener(view -> {
            if (!performEmptinessCheck()) {
                return;
            }
            confirmNumbers();
            doDiv();
        });
        binding.pow.setOnClickListener(view -> {
            if (!performEmptinessCheck()) {
                return;
            }
            confirmNumbers();
            doPow();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        moveToActivity(this, item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("SetTextI18n")
    private void doSum() {
        float num1 = Float.parseFloat(binding.num1.getText().toString());
        float num2 = Float.parseFloat(binding.num2.getText().toString());

        binding.result.setText(num1 + " + " + num2 + " = " + (num1+num2));
    }

    private void doSub() {
        float num1 = Float.parseFloat(binding.num1.getText().toString());
        float num2 = Float.parseFloat(binding.num2.getText().toString());

        binding.result.setText(num1 + " - " + num2 + " = " + (num1-num2));
    }

    private void doMul() {
        float num1 = Float.parseFloat(binding.num1.getText().toString());
        float num2 = Float.parseFloat(binding.num2.getText().toString());

        binding.result.setText(num1 + " * " + num2 + " = " + (num1*num2));
    }

    private void doDiv() {
        float num1 = Float.parseFloat(binding.num1.getText().toString());
        float num2 = Float.parseFloat(binding.num2.getText().toString());

        if (num2 == 0.0F) {
            Toast.makeText(this, "Can not divide by 0.", Toast.LENGTH_SHORT).show();
            return;
        }

        binding.result.setText(num1 + " / " + num2 + " = " + (num1/num2));
    }

    private void doPow() {
        float num1 = Float.parseFloat(binding.num1.getText().toString());
        float num2 = Float.parseFloat(binding.num2.getText().toString());

        binding.result.setText(num1 + "^" + num2 + " = " + Math.pow(num1, num2));
    }


    public void clearTextNum1(View view) {
        convertToReadableNumber(binding.num1);
    }

    public void clearTextNum2(View view) {
        convertToReadableNumber(binding.num2);
    }

    public void confirmNumbers() {
        clearTextNum1(binding.num1);
        clearTextNum2(binding.num2);
    }

    private boolean performEmptinessCheck() {
        if (binding.num1.getText().toString().isEmpty() || binding.num2.getText().toString().isEmpty()) {
            Toast.makeText(this, "At least one of the number fields is empty.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}