package com.spendanalyzerapp;

import android.util.Log;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsParser {
    private static final String TAG = "SmsParser";

    // Common patterns for banking SMS
    private static final Pattern DEBIT_PATTERN = Pattern.compile(
        "(?i).*(debited|spent|paid|purchase|withdraw).*(?:aed|inr|usd|rs\\.?)\\s*(\\d+(?:\\.\\d{2})?)",
        Pattern.CASE_INSENSITIVE
    );

    private static final Pattern CREDIT_PATTERN = Pattern.compile(
        "(?i).*(credited|received|deposit).*(?:aed|inr|usd|rs\\.?)\\s*(\\d+(?:\\.\\d{2})?)",
        Pattern.CASE_INSENSITIVE
    );

    private static final Pattern AMOUNT_PATTERN = Pattern.compile(
        "(?:aed|inr|usd|rs\\.?)\\s*(\\d+(?:\\.\\d{2})?)",
        Pattern.CASE_INSENSITIVE
    );

    public Transaction parseSms(String sender, String messageBody, long timestamp) {
        Log.d(TAG, "Parsing SMS from: " + sender + ", Message: " + messageBody);

        Transaction transaction = new Transaction();
        transaction.setSender(sender);
        transaction.setRawMessage(messageBody);
        transaction.setTimestamp(new Date(timestamp));

        // Extract amount
        double amount = extractAmount(messageBody);
        transaction.setAmount(amount);

        // Determine transaction type
        TransactionType type = determineTransactionType(messageBody);
        transaction.setType(type);

        // Extract merchant/description
        String merchant = extractMerchant(messageBody);
        transaction.setMerchant(merchant);

        // Categorize transaction
        Category category = categorizeTransaction(messageBody, merchant);
        transaction.setCategory(category);

        return transaction;
    }

    private double extractAmount(String message) {
        Matcher matcher = AMOUNT_PATTERN.matcher(message);
        if (matcher.find()) {
            try {
                return Double.parseDouble(matcher.group(1));
            } catch (NumberFormatException e) {
                Log.e(TAG, "Failed to parse amount: " + matcher.group(1));
            }
        }
        return 0.0;
    }

    private TransactionType determineTransactionType(String message) {
        if (DEBIT_PATTERN.matcher(message).find()) {
            return TransactionType.DEBIT;
        } else if (CREDIT_PATTERN.matcher(message).find()) {
            return TransactionType.CREDIT;
        }
        return TransactionType.DEBIT; // Default assumption
    }

    private String extractMerchant(String message) {
        // Simple extraction - look for merchant after common keywords
        String[] keywords = {"at ", "to ", "from ", "towards ", "merchant ", "vendor "};
        String lowerMessage = message.toLowerCase();

        for (String keyword : keywords) {
            int index = lowerMessage.indexOf(keyword);
            if (index != -1) {
                String afterKeyword = message.substring(index + keyword.length());
                // Extract until next space or punctuation
                String merchant = afterKeyword.split("[\\s.,;]+")[0];
                if (merchant.length() > 2) {
                    return merchant.trim();
                }
            }
        }

        return "Unknown";
    }

    private Category categorizeTransaction(String message, String merchant) {
        String lowerMessage = message.toLowerCase();
        String lowerMerchant = merchant.toLowerCase();

        // Food & Dining
        if (containsAny(lowerMessage, "restaurant", "cafe", "food", "dining", "pizza", "burger") ||
            containsAny(lowerMerchant, "mcdonalds", "kfc", "subway", "dominos", "pizza")) {
            return Category.FOOD;
        }

        // Transportation
        if (containsAny(lowerMessage, "uber", "taxi", "careem", "petrol", "fuel", "parking", "metro") ||
            containsAny(lowerMerchant, "uber", "careem", "metro", "parking")) {
            return Category.TRANSPORTATION;
        }

        // Shopping
        if (containsAny(lowerMessage, "mall", "store", "shopping", "amazon", "purchase") ||
            containsAny(lowerMerchant, "amazon", "flipkart", "mall", "store")) {
            return Category.SHOPPING;
        }

        // Entertainment
        if (containsAny(lowerMessage, "movie", "cinema", "entertainment", "game", "netflix") ||
            containsAny(lowerMerchant, "netflix", "cinema", "movie", "game")) {
            return Category.ENTERTAINMENT;
        }

        // Utilities
        if (containsAny(lowerMessage, "electricity", "water", "gas", "internet", "mobile", "phone") ||
            containsAny(lowerMerchant, "etisalat", "du", "addc", "sewa")) {
            return Category.UTILITIES;
        }

        return Category.OTHER;
    }

    private boolean containsAny(String text, String... keywords) {
        for (String keyword : keywords) {
            if (text.contains(keyword)) {
                return true;
            }
        }
        return false;
    }
}