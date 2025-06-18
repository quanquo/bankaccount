package com.dangvis.account.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity(name = "bank_user")
public class User {  
    
    @Id
    @Column(name = "username", length=10)
    private String username;
    
    @Column(name = "fullname")
    private String name;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BankAccount bankAccount;
    
    public User () {}
    
    public User (String username, String name) {
    	this.setName(name);
    	this.setUsername(username);
    }
    
    public User (String username, String name, BankAccount newAccount) {
    	this.setName(name);
    	this.setUsername(username);
    	this.setBankAccount(newAccount);
    }
    
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name;}
	
	public BankAccount getBankAccount() { return this.bankAccount; }
	public void setBankAccount(BankAccount newAccount) { this.bankAccount = newAccount; }
	
/*	public String toString() {
		return "Username " + this.getUsername() +
				": " + this.getName() + " - " + this.bankAccount.toString();
	}
*/	
}
