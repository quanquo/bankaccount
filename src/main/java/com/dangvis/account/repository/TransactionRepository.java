package com.dangvis.account.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dangvis.account.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
