package com.dangvis.account.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name = "bankaccount")
public class BankAccount {
	
	@Id
    private String accountNumber;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal balance;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
	private User user;
	
	public BankAccount() {}
	
	public BankAccount(String accountNumber, User user, BigDecimal balance) {
		this.setAccountNumber(accountNumber);
		this.setUser(user);
		this.setBalance(balance);
	}

	public String getAccountNumber() { return accountNumber; }
	public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

	public BigDecimal getBalance() { return balance; }
	public void setBalance(BigDecimal balance) { this.balance = balance; }

	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; }
}
