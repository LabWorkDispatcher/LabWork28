package com.example.laba19.UI;

import static com.example.laba19.utils.Utils.moveToActivity;
import static com.example.laba19.data.Constants.relativity;
import static com.example.laba19.data.Constants.units;
import static com.example.laba19.utils.Utils.convertToReadableNumber;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.laba19.R;
import com.example.laba19.databinding.ActivitySecondaryBinding;


public class SecondaryActivity extends AppCompatActivity {
    private ActivitySecondaryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayAdapter adapter1 = new ArrayAdapter(this, R.layout.spinner_item, units);
        adapter1.setDropDownViewResource(R.layout.spinner_dropdown_item);

        ArrayAdapter adapter2 = new ArrayAdapter(this, R.layout.spinner_item, units);
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);

        binding.num1Spinner.setAdapter(adapter1);
        binding.num2Spinner.setAdapter(adapter2);

        binding.performEqualization.setOnClickListener(view -> {
            if (!performEmptinessCheck()) {
                return;
            }
            performEqualization();
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

    public void clearTextNum1(View view) {
        convertToReadableNumber(binding.num1);
    }

    @SuppressLint("SetTextI18n")
    private void performEqualization() {
        String unit1 = binding.num1Spinner.getSelectedItem().toString();
        String unit2 = binding.num2Spinner.getSelectedItem().toString();

        int pos1 = -1, pos2 = -1;
        for (int i = 0; i < units.length; i++) {
            if (unit1.equals(units[i])) {
                pos1 = i;
            }
            if (unit2.equals(units[i])) {
                pos2 = i;
            }
        }

        if (pos1 == -1 || pos2 == -1) {
            throw(new IllegalStateException("Unable to find chosen units in the units array!"));
        }

        double conversionIndex = 1;
        if (pos1 != pos2) {
            if (pos1 < pos2) {
                for (int i = pos1; i < pos2; i++) {
                    conversionIndex *= relativity[i];
                }
            } else {
                for (int i = pos2; i < pos1; i++) {
                    conversionIndex /= relativity[i];
                }
            }
        }

        binding.num2.setText("" + (Float.parseFloat(binding.num1.getText().toString()) * conversionIndex));
    }

    private boolean performEmptinessCheck() {
        if (binding.num1.getText().toString().isEmpty() || binding.num1.getText().toString().isEmpty()) {
            Toast.makeText(this, "At least one of the number fields is empty.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
