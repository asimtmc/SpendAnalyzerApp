package com.spendanalyzerapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "transactions")
public class TransactionEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String sender;
    public String messageBody;
    public long timestamp;
    public double amount;
    public String type;
    public String merchant;
    public Category category;

    public TransactionEntity(String sender, String messageBody, long timestamp, double amount, String type, String merchant, Category category) {
        this.sender = sender;
        this.messageBody = messageBody;
        this.timestamp = timestamp;
        this.amount = amount;
        this.type = type;
        this.merchant = merchant;
        this.category = category;
    }
}

