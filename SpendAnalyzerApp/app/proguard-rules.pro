
# Add project specific ProGuard rules here.

# Keep Room database classes
-keep class androidx.room.** { *; }
-keep @androidx.room.Entity class * { *; }
-keep @androidx.room.Dao class * { *; }
-keep @androidx.room.Database class * { *; }

# Keep SQLCipher classes
-keep class net.zetetic.database.** { *; }

# Keep enum classes
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Keep transaction class for Room
-keep class com.spendanalyzerapp.Transaction { *; }
-keep class com.spendanalyzerapp.Category { *; }

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
