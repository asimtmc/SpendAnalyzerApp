
package com.spendanalyzerapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ManageCategoriesActivity extends AppCompatActivity {

    private LinearLayout categoryManagementList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_categories);

        categoryManagementList = findViewById(R.id.categoryManagementList);
        
        displayCategories();

        Button btnAddCategory = findViewById(R.id.btnAddCategory);
        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ManageCategoriesActivity.this, "Add Category feature coming soon!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayCategories() {
        categoryManagementList.removeAllViews();
        
        for (Category category : Category.values()) {
            LinearLayout categoryRow = new LinearLayout(this);
            categoryRow.setOrientation(LinearLayout.HORIZONTAL);
            categoryRow.setPadding(0, 16, 0, 16);

            TextView categoryName = new TextView(this);
            categoryName.setText(category.getDisplayName());
            categoryName.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            categoryName.setTextSize(16);

            Button editButton = new Button(this);
            editButton.setText("Edit");
            editButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ManageCategoriesActivity.this, "Edit " + category.getDisplayName(), Toast.LENGTH_SHORT).show();
                }
            });

            categoryRow.addView(categoryName);
            categoryRow.addView(editButton);
            categoryManagementList.addView(categoryRow);
        }
    }
}
package com.spendanalyzerapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ManageCategoriesActivity extends AppCompatActivity {

    private LinearLayout categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_categories);

        categoryList = findViewById(R.id.categoryList);
        displayCategories();
    }

    private void displayCategories() {
        for (Category category : Category.values()) {
            View categoryView = getLayoutInflater().inflate(R.layout.category_item, null);
            TextView tvCategoryName = categoryView.findViewById(R.id.tvCategoryName);
            tvCategoryName.setText(category.name());
            categoryList.addView(categoryView);
        }
    }
}
