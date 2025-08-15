package com.expensetracker.service;

import com.expensetracker.model.Expense;
import com.expensetracker.model.LimitConfig;
import com.expensetracker.repository.ExpenseRepository;
import com.expensetracker.repository.LimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private LimitRepository limitRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public void addExpense(Expense expense) {
        expenseRepository.save(expense);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public double getTotalExpenses() {
        return expenseRepository.findAll().stream().mapToDouble(Expense::getAmount).sum();
    }

    public double getLimit() {
        List<LimitConfig> limits = limitRepository.findAll();
        return limits.isEmpty() ? 1000.0 : limits.get(0).getLimitAmount(); // Default limit 1000
    }

    public String checkLimit() {
        double total = getTotalExpenses();
        double limit = getLimit();
        if (total > limit) {
            return "⚠️ Warning: Your total expenses (" + total + ") have exceeded the set limit (" + limit + ")!";
        }
        return "";
    }

    public void updateLimit(double newLimit) {
        List<LimitConfig> limits = limitRepository.findAll();
        LimitConfig limitConfig;
        if (limits.isEmpty()) {
            limitConfig = new LimitConfig(newLimit);
        } else {
            limitConfig = limits.get(0);
            limitConfig.setLimitAmount(newLimit);
        }
        limitRepository.save(limitConfig);
    }
}
