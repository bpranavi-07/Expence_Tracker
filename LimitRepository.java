package com.expensetracker.repository;

import com.expensetracker.model.LimitConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitRepository extends JpaRepository<LimitConfig, Long> {
}
