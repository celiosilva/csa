package br.com.delogic.showcase.entity;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

import br.com.delogic.showcase.repository.LongEntityId;

public class Account extends LongEntityId {

	@NotNull
	private User user;

	@NotNull
	private Integer bankAccount;

	@NumberFormat(pattern = "#,##0.00")
	@NotNull
	private BigDecimal balance;

	@NotNull
	private AccountType accountType;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(Integer bankAccount) {
		this.bankAccount = bankAccount;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

}
