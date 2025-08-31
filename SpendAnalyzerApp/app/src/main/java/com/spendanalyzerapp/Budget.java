package com.spendanalyzerapp;

import java.util.HashMap;
import java.util.Map;

public class Budget {
    private double totalBudget;
    private Map<Category, Double> categoryBudgets;

    public Budget(double totalBudget) {
        this.totalBudget = totalBudget;
        this.categoryBudgets = new HashMap<>();
        // Initialize category budgets to 0
        for (Category category : Category.values()) {
            this.categoryBudgets.put(category, 0.0);
        }
    }

    public double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public Map<Category, Double> getCategoryBudgets() {
        return categoryBudgets;
    }

    public void setCategoryBudget(Category category, double amount) {
        this.categoryBudgets.put(category, amount);
    }

    public double getCategoryBudget(Category category) {
        return categoryBudgets.getOrDefault(category, 0.0);
    }
}

