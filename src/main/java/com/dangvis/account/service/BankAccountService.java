package com.dangvis.account.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangvis.account.repository.AccountRepository;
import com.dangvis.account.repository.UserRepository;
import com.dangvis.account.repository.TransactionRepository;
import com.dangvis.account.model.BankAccount;
import com.dangvis.account.model.User;
import com.dangvis.account.model.Transaction;

@Service
public class BankAccountService {
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
    public List<BankAccount> getAccounts() {
        return accountRepository.findAll();
    }
    
    public Optional<BankAccount> getAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }
    
    public BankAccount createAccount(String username, String accountNumber, String description, BigDecimal cashBalance) {
        User user = userRepository.findById(username)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + username));
        
        if (accountRepository.existsByAccountNumber(accountNumber)) {
            throw new RuntimeException("Account number already exists: " + accountNumber);
        }
        
        if (cashBalance.doubleValue() < 0 ) {
        	throw new RuntimeException("Balance during the creation of the account must be positiv: " + cashBalance.doubleValue());
        }
        	
        BankAccount account = new BankAccount(accountNumber, description, user, cashBalance);
        return accountRepository.save(account);
    }
    
    public void deposit(BankAccount acc, double amount) {
    	if (! accountRepository.existsByAccountNumber(acc.getAccountNumber())) {
            throw new RuntimeException("Account number does not exist: " + acc.getAccountNumber());
        }
    	
        if (amount < 0 ) {
        	throw new RuntimeException("Amount by deposit must be positiv: " + amount);
        }
        
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(BigDecimal.valueOf(amount));
        newTransaction.setTransactionType(Transaction.DEPOSIT);
        
        acc.getTransactions().add(newTransaction);
        acc.setBalance( acc.getBalance().add(BigDecimal.valueOf(amount)));
        accountRepository.save(acc);
    }
    
    // withdraw
    // transfer
     
}
