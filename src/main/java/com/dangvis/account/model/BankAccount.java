package com.dangvis.account.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity(name = "bankaccount")
public class BankAccount {
	
	@Id
	@Column(name = "accountNumber", length=10)
    private String accountNumber;
    
    @Column(name = "description", length=255)
    private String description;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal balance;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
	private User user;
    
	@OneToMany(
			mappedBy="bankAccount",
			fetch = FetchType.LAZY,
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	@JsonManagedReference
    private List<Transaction> transactions;
		
	public BankAccount() {
		this(null, null, null, null);
	}
	
	public BankAccount(String accountNumber, User user, BigDecimal balance) {
		this.transactions = new ArrayList<Transaction>();
	}
	
	public BankAccount(String accountNumber, String description, User user, BigDecimal balance) {
		this.setAccountNumber(accountNumber);
		this.setUser(user);
		this.setBalance(balance);
		this.setDescription(description); 
		this.transactions = new ArrayList<Transaction>();
	}

	public String getAccountNumber() { return accountNumber; }
	public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	
	public BigDecimal getBalance() { return balance; }
	public void setBalance(BigDecimal balance) { this.balance = balance; }

	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; }
	
	public List<Transaction> getTransactions() {
		return this.transactions;
	}
}
