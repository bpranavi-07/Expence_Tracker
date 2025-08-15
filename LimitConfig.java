package com.expensetracker.model;

import jakarta.persistence.*;

@Entity
public class LimitConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double limitAmount;

    public LimitConfig() {}

    public LimitConfig(double limitAmount) {
        this.limitAmount = limitAmount;
    }

    public Long getId() {
        return id;
    }

    public double getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(double limitAmount) {
        this.limitAmount = limitAmount;
    }
}
