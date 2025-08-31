
package com.spendanalyzerapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class BudgetActivity extends AppCompatActivity {

    private EditText etTotalBudget;
    private LinearLayout categoryBudgetList;
    private Map<Category, EditText> categoryBudgetInputs;
    private Budget currentBudget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        etTotalBudget = findViewById(R.id.etTotalBudget);
        categoryBudgetList = findViewById(R.id.categoryBudgetList);
        categoryBudgetInputs = new HashMap<>();
        
        // Initialize with default budget
        currentBudget = new Budget(2500.00);
        
        setupCategoryBudgetInputs();
        loadCurrentBudget();

        Button btnSaveBudget = findViewById(R.id.btnSaveBudget);
        btnSaveBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBudget();
            }
        });

        Button btnCancelBudget = findViewById(R.id.btnCancelBudget);
        btnCancelBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupCategoryBudgetInputs() {
        categoryBudgetList.removeAllViews();
        
        for (Category category : Category.values()) {
            LinearLayout categoryRow = new LinearLayout(this);
            categoryRow.setOrientation(LinearLayout.HORIZONTAL);
            categoryRow.setPadding(0, 8, 0, 8);

            TextView categoryLabel = new TextView(this);
            categoryLabel.setText(category.getDisplayName() + ":");
            categoryLabel.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

            EditText categoryBudgetInput = new EditText(this);
            categoryBudgetInput.setHint("0.00");
            categoryBudgetInput.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
            categoryBudgetInput.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

            categoryBudgetInputs.put(category, categoryBudgetInput);

            categoryRow.addView(categoryLabel);
            categoryRow.addView(categoryBudgetInput);
            categoryBudgetList.addView(categoryRow);
        }
    }

    private void loadCurrentBudget() {
        etTotalBudget.setText(String.valueOf(currentBudget.getTotalBudget()));
        
        for (Map.Entry<Category, EditText> entry : categoryBudgetInputs.entrySet()) {
            double categoryBudget = currentBudget.getCategoryBudget(entry.getKey());
            entry.getValue().setText(categoryBudget > 0 ? String.valueOf(categoryBudget) : "");
        }
    }

    private void saveBudget() {
        try {
            String totalBudgetText = etTotalBudget.getText().toString();
            if (!totalBudgetText.isEmpty()) {
                double totalBudget = Double.parseDouble(totalBudgetText);
                currentBudget.setTotalBudget(totalBudget);
            }

            for (Map.Entry<Category, EditText> entry : categoryBudgetInputs.entrySet()) {
                String categoryBudgetText = entry.getValue().getText().toString();
                if (!categoryBudgetText.isEmpty()) {
                    double categoryBudget = Double.parseDouble(categoryBudgetText);
                    currentBudget.setCategoryBudget(entry.getKey(), categoryBudget);
                }
            }

            Toast.makeText(this, "Budget saved successfully!", Toast.LENGTH_SHORT).show();
            finish();
            
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }
}
package com.spendanalyzerapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class BudgetActivity extends AppCompatActivity {

    private EditText etBudgetAmount;
    private LinearLayout budgetCategoryList;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        database = AppDatabase.getDatabase(this);
        
        etBudgetAmount = findViewById(R.id.etBudgetAmount);
        budgetCategoryList = findViewById(R.id.budgetCategoryList);

        Button btnSaveBudget = findViewById(R.id.btnSaveBudget);
        btnSaveBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBudget();
            }
        });

        displayBudgetCategories();
    }

    private void saveBudget() {
        String budgetText = etBudgetAmount.getText().toString();
        if (!budgetText.isEmpty()) {
            try {
                double budget = Double.parseDouble(budgetText);
                // Save budget logic here
                Toast.makeText(this, "Budget saved: AED " + budget, Toast.LENGTH_SHORT).show();
                etBudgetAmount.setText("");
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void displayBudgetCategories() {
        // Display budget categories with current spending
        for (Category category : Category.values()) {
            View categoryView = getLayoutInflater().inflate(R.layout.budget_category_item, null);
            TextView tvCategoryName = categoryView.findViewById(R.id.tvCategoryName);
            TextView tvCategoryAmount = categoryView.findViewById(R.id.tvCategoryAmount);
            
            tvCategoryName.setText(category.name());
            tvCategoryAmount.setText("AED 0.00"); // Placeholder for actual spending
            
            budgetCategoryList.addView(categoryView);
        }
    }
}
