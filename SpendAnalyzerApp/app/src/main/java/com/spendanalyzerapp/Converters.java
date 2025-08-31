
package com.spendanalyzerapp;

import androidx.room.TypeConverter;

public class Converters {
    
    @TypeConverter
    public static Category fromCategoryName(String categoryName) {
        return categoryName == null ? null : Category.valueOf(categoryName);
    }

    @TypeConverter
    public static String categoryToString(Category category) {
        return category == null ? null : category.name();
    }
}
package com.spendanalyzerapp;

import androidx.room.TypeConverter;
import java.util.Date;

public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static String fromCategory(Category category) {
        return category == null ? null : category.name();
    }

    @TypeConverter
    public static Category toCategory(String categoryString) {
        return categoryString == null ? null : Category.valueOf(categoryString);
    }
}
