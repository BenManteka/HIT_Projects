package com.example.hitfirstprojectcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView result;

    double num1 = 0, num2 = 0; // Parameters for calculating

    private char operator = '\0'; // Default operator

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        result = findViewById(R.id.textViewResult); // Finding the the current screen for the result
        result.setText(""); // Reset the text on the main screen
    }

    public void numFunction(View view) { // function for adding number to the text
        Button button = (Button) view;

        result.append(button.getText().toString());
    }
    public void operatorFunction(View view) { // Function to select an operator
        Button button = (Button) view;
        if (operator == '\0') { // Only allow operator input if none has been selected yet
            operator = button.getText().toString().charAt(0); // Store the operator
            num1 = Double.parseDouble(result.getText().toString()); // Parse the first number
            result.setText(""); // Clear the display for the second number
        }
    }
    public void calculateFunction(View view) { // function that used to perform calculate action
        if (operator != '\0') {
            num2 = Double.parseDouble(result.getText().toString()); // Parse the second number

            double resultValue = 0;

            switch (operator) {
                case '+':
                    resultValue = num1 + num2;
                    break;
                case '-':
                    resultValue = num1 - num2;
                    break;
                case '*':
                    resultValue = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        resultValue = num1 / num2;
                        break;
                    } else {
                        result.setText("Error"); // Handle division by zero
                        operator = '\0'; // Reset operator
                        return;
                    }
                case '%':
                    resultValue = (num1 * num2) / 100; // Calculate percentage
                    break;
            }

            result.setText(String.valueOf(resultValue)); // Display the result
            operator = '\0'; // Reset operator for the next calculation
            num1 = resultValue; // Store the result as the new num1 for chained operations
        }
    }
    public void changeSignFunction(View view) { // Function to change the value sign
        if (!result.getText().toString().isEmpty()) {
            double currentValue = Double.parseDouble(result.getText().toString());
            result.setText(String.valueOf(currentValue * -1));
        }
    }

    public void clearFunction(View view) { // Function to clear the inputs and reset everything
        num1 = 0;
        num2 = 0;
        operator = '\0';
        result.setText("");
    }
}
