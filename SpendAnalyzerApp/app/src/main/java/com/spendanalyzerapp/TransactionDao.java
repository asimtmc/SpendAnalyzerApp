package com.spendanalyzerapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TransactionDao {
    
    @Query("SELECT * FROM transactions ORDER BY timestamp DESC")
    List<Transaction> getAllTransactions();

    @Query("SELECT * FROM transactions WHERE category = :category ORDER BY timestamp DESC")
    List<Transaction> getTransactionsByCategory(Category category);

    @Query("SELECT * FROM transactions WHERE timestamp >= :startTime AND timestamp <= :endTime ORDER BY timestamp DESC")
    List<Transaction> getTransactionsBetweenDates(long startTime, long endTime);

    @Insert
    void insertTransaction(Transaction transaction);

    @Update
    void updateTransaction(Transaction transaction);

    @Delete
    void deleteTransaction(Transaction transaction);

    @Query("DELETE FROM transactions")
    void deleteAllTransactions();

    @Query("SELECT SUM(amount) FROM transactions WHERE category = :category")
    double getTotalAmountByCategory(Category category);

    @Query("SELECT SUM(amount) FROM transactions")
    double getTotalAmount();
}