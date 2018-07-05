package com.bank.pojos;


public class Account {
	
	private int id;
	private double balance;
	private User user;
	
	
	public Account(){}
	
	public Account(int id, double balance, User user) {
		super();
		this.id = id;
		this.balance = balance;
		this.user = user;
	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public String toString() {
		return "Account " + id + " has balance=" + balance;
	}
	
	
}
