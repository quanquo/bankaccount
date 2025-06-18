package com.dangvis.account.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dangvis.account.model.BankAccount;
import com.dangvis.account.service.BankAccountService;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "*")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;
    
    @GetMapping
    public List<BankAccount> getAllAccounts() {
        return bankAccountService.getAccounts();
    } 
    
    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> getAccount(@PathVariable String accountNumber) {
        return bankAccountService.getAccountByAccountNumber(accountNumber)
                .map(acc -> ResponseEntity.ok().body(acc))
                .orElse(ResponseEntity.notFound().build());
    }
    
	@PostMapping
	public ResponseEntity<BankAccount> createAccount(@RequestBody Map<String, Object> request) {
		try {
			String username = request.get("username").toString();
			String accountNumber = request.get("accountNumber").toString();		
			String description = request.get("description").toString();
			BigDecimal cashBalance = new BigDecimal(request.get("cashBalance").toString()); 
			
			BankAccount createdAccount = bankAccountService.createAccount(username, accountNumber, description, cashBalance);
			
			return ResponseEntity.ok(createdAccount);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	} 
	
	@PostMapping("/deposit")
	public ResponseEntity<BankAccount> deposit(@RequestBody Map<String, Object> request) {
		try {
			String accountNumber = request.get("accountNumber").toString();		
			BigDecimal amount = new BigDecimal(request.get("amount").toString()); 
			
			Optional<BankAccount> account = bankAccountService.getAccountByAccountNumber(accountNumber);
			bankAccountService.deposit(account.get(), amount.doubleValue());
			
			return ResponseEntity.ok(account.get());
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}  
}
