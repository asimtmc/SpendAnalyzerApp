
package com.spendanalyzerapp;

public enum Category {
    FOOD("Food"),
    SHOPPING("Shopping"),
    ENTERTAINMENT("Entertainment"),
    UTILITIES("Utilities"),
    TRANSPORT("Transport"),
    OTHER("Other");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Category fromString(String categoryName) {
        for (Category category : Category.values()) {
            if (category.displayName.equalsIgnoreCase(categoryName)) {
                return category;
            }
        }
        return OTHER;
    }
}
