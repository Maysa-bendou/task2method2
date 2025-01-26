package com.ln1.task2method2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Conversion rates: 1 Euro = 141 DZD, and vice versa
    private static final double EURO_TO_DZD_RATE = 141.0;
    private static final double DZD_TO_EURO_RATE = 1 / EURO_TO_DZD_RATE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI Elements
        EditText inputField = findViewById(R.id.inputValue);
        RadioGroup currencySelectionGroup = findViewById(R.id.radioGroup);
        RadioButton euroToDzdRadioButton = findViewById(R.id.rbEuroToDzd);
        RadioButton dzdToEuroRadioButton = findViewById(R.id.rbDzdToEuro);
        Button convertButton = findViewById(R.id.btnConvert);
        TextView resultTextView = findViewById(R.id.resultText);

        // Button click listener for performing conversion
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = inputField.getText().toString();

                // Input validation: Check if the input field is empty
                if (inputText.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a value to convert.", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double valueToConvert = Double.parseDouble(inputText);
                    double conversionResult = 0.0;

                    // Get the selected radio button from the RadioGroup
                    int selectedCurrencyId = currencySelectionGroup.getCheckedRadioButtonId();
                    if (selectedCurrencyId == R.id.rbEuroToDzd) {
                        // Euro to DZD conversion
                        conversionResult = valueToConvert * EURO_TO_DZD_RATE;
                        resultTextView.setText(String.format("Result: %.2f DZD", conversionResult));
                    } else if (selectedCurrencyId == R.id.rbDzdToEuro) {
                        // DZD to Euro conversion
                        conversionResult = valueToConvert * DZD_TO_EURO_RATE;
                        resultTextView.setText(String.format("Result: %.2f Euro", conversionResult));
                    } else {
                        // No currency selected
                        Toast.makeText(MainActivity.this, "Please select a conversion option.", Toast.LENGTH_SHORT).show();
                    }

                } catch (NumberFormatException e) {
                    // Handle invalid input
                    Toast.makeText(MainActivity.this, "Invalid input. Please enter a valid number.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

