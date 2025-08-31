
package com.spendanalyzerapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import java.util.Date;

@Entity(tableName = "transactions")
@TypeConverters(Converters.class)
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private double amount;
    private String type; // "DEBIT" or "CREDIT"
    private String merchant;
    private Category category;
    private Date timestamp;
    private String smsBody;
    private String sender;

    public Transaction() {}

    public Transaction(double amount, String type, String merchant, Category category, Date timestamp, String smsBody, String sender) {
        this.amount = amount;
        this.type = type;
        this.merchant = merchant;
        this.category = category;
        this.timestamp = timestamp;
        this.smsBody = smsBody;
        this.sender = sender;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getMerchant() { return merchant; }
    public void setMerchant(String merchant) { this.merchant = merchant; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }

    public String getSmsBody() { return smsBody; }
    public void setSmsBody(String smsBody) { this.smsBody = smsBody; }

    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }
}
