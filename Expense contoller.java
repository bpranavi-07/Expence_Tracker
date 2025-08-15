package com.expensetracker.controller;

import com.expensetracker.model.Expense;
import com.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService service;

    @GetMapping
    public String listExpenses(Model model) {
        model.addAttribute("expenses", service.getAllExpenses());
        model.addAttribute("total", service.getTotalExpenses());
        model.addAttribute("limit", service.getLimit());
        model.addAttribute("limitWarning", service.checkLimit());
        return "expenses";
    }

    @PostMapping("/add")
    public String addExpense(@RequestParam String description, @RequestParam double amount, @RequestParam String date) {
        LocalDate expenseDate = LocalDate.parse(date);
        service.addExpense(new Expense(description, amount, expenseDate));
        return "redirect:/expenses";
    }

    @PostMapping("/setLimit")
    public String setLimit(@RequestParam double limit) {
        service.updateLimit(limit);
        return "redirect:/expenses";
    }

    @GetMapping("/delete/{id}")
    public String deleteExpense(@PathVariable Long id) {
        service.deleteExpense(id);
        return "redirect:/expenses";
    }
}
