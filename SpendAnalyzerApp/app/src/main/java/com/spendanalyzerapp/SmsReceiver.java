
package com.spendanalyzerapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SmsReceiver extends BroadcastReceiver {

    private static final String TAG = "SmsReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                String sender = smsMessage.getDisplayOriginatingAddress();
                String messageBody = smsMessage.getMessageBody();
                long timestamp = smsMessage.getTimestampMillis();

                Log.d(TAG, "SMS received from: " + sender);
                Log.d(TAG, "Message body: " + messageBody);
                Log.d(TAG, "Timestamp: " + timestamp);

                // Check if this looks like a banking SMS
                if (isBankingSms(sender, messageBody)) {
                    SmsParser smsParser = new SmsParser();
                    Transaction transaction = smsParser.parseSms(sender, messageBody, timestamp);

                    Log.d(TAG, "Parsed Transaction: Amount=" + transaction.getAmount() + 
                          ", Type=" + transaction.getType() + 
                          ", Merchant=" + transaction.getMerchant() + 
                          ", Category=" + transaction.getCategory());

                    // Save transaction to database
                    saveTransaction(context, transaction);
                }
            }
        }
    }

    private boolean isBankingSms(String sender, String messageBody) {
        // Check if sender is from known banking sources
        String[] bankKeywords = {"BANK", "CREDIT", "DEBIT", "ATM", "CARD", "PAY", "PURCHASE"};
        String upperSender = sender.toUpperCase();
        String upperMessage = messageBody.toUpperCase();

        for (String keyword : bankKeywords) {
            if (upperSender.contains(keyword) || upperMessage.contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    private void saveTransaction(Context context, Transaction transaction) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    AppDatabase database = AppDatabase.getDatabase(context);
                    database.transactionDao().insert(transaction);
                    Log.d(TAG, "Transaction saved to database");
                } catch (Exception e) {
                    Log.e(TAG, "Error saving transaction: " + e.getMessage());
                }
            }
        });
    }
}
