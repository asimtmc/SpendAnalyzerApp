
package com.spendanalyzerapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView tvTotalBudget;
    private LinearLayout categoryList;
    private LinearLayout transactionList;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = AppDatabase.getDatabase(this);
        
        tvTotalBudget = findViewById(R.id.tvTotalBudget);
        categoryList = findViewById(R.id.categoryList);
        transactionList = findViewById(R.id.transactionList);

        // Display dummy data for now
        displayDummyData();

        Button btnSetBudget = findViewById(R.id.btnSetBudget);
        btnSetBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BudgetActivity.class);
                startActivity(intent);
            }
        });
    }

    private void displayDummyData() {
        // Dummy Budget Data
        double totalBudget = 2500.00;
        double spentAmount = 780.00;
        tvTotalBudget.setText(String.format("Total Budget: AED %.2f (Spent: AED %.2f)", totalBudget, spentAmount));

        // Dummy Spending by Category Data
        Map<Category, Double> categorySpending = new HashMap<>();
        categorySpending.put(Category.FOOD, 300.00);
        categorySpending.put(Category.SHOPPING, 250.00);
        categorySpending.put(Category.ENTERTAINMENT, 150.00);
        categorySpending.put(Category.UTILITIES, 80.00);

        displayCategorySpending(categorySpending);

        // Dummy Transaction Data
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("Bank", "AED 45.50 spent at McDonald's", System.currentTimeMillis(), 45.50, "DEBIT", "McDonald's", Category.FOOD));
        transactions.add(new Transaction("Bank", "AED 120.00 spent at Mall of Emirates", System.currentTimeMillis() - 86400000, 120.00, "DEBIT", "Mall of Emirates", Category.SHOPPING));
        transactions.add(new Transaction("Bank", "AED 25.00 spent on Uber ride", System.currentTimeMillis() - 172800000, 25.00, "DEBIT", "Uber", Category.TRANSPORT));

        displayRecentTransactions(transactions);
    }

    private void displayCategorySpending(Map<Category, Double> categorySpending) {
        categoryList.removeAllViews();
        for (Map.Entry<Category, Double> entry : categorySpending.entrySet()) {
            TextView categoryView = new TextView(this);
            categoryView.setText(String.format("%s: AED %.2f", entry.getKey().getDisplayName(), entry.getValue()));
            categoryView.setPadding(0, 8, 0, 8);
            categoryList.addView(categoryView);
        }
    }

    private void displayRecentTransactions(List<Transaction> transactions) {
        transactionList.removeAllViews();
        for (Transaction transaction : transactions) {
            TextView transactionView = new TextView(this);
            transactionView.setText(String.format("AED %.2f at %s (%s)", 
                transaction.getAmount(), transaction.getMerchant(), transaction.getCategory().getDisplayName()));
            transactionView.setPadding(0, 8, 0, 8);
            transactionList.addView(transactionView);
        }
    }
}
