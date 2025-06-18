package com.dangvis.account.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "transaction")
public class Transaction {
	
	public final static String DEPOSIT = "D";
	public final static String WITHDRAW = "W";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID uuid;
	
	@Column(name = "time_stamp")
	private LocalDateTime transactionTimeStamp;
	
	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "fromAccount")
	private String fromAccount;
	
	@Column(name = "toAccount")
	private String toAccount;
	
	@Column(name = "type", length=1)
	private String transactionType;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="bank_account_id", nullable=false)
	@JsonBackReference
	private BankAccount bankAccount;
	
	public Transaction() {
		transactionTimeStamp = LocalDateTime.now();
	}
	
	public Transaction(BankAccount newBankAccount) {
		transactionTimeStamp = LocalDateTime.now();
		this.setBankAccount(newBankAccount);
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public LocalDateTime getTransactionTimeStamp() {
		return transactionTimeStamp;
	}

	public void setTransactionTimeStamp(LocalDateTime transactionTimeStamp) {
		this.transactionTimeStamp = transactionTimeStamp;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public String getToAccount() {
		return toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	public BankAccount getBankAccount() {
		return this.bankAccount;
	}
	
	public void setBankAccount(BankAccount newBankAccount) {
		this.bankAccount = newBankAccount;
	}
}