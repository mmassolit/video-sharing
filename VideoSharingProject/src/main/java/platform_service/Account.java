package platform_service;

import java.util.UUID;

public class Account {
	final protected String name;
	final protected String uid;
	protected double balance;
	
	public Account(String name) {
		this.name = name;
		this.uid = UUID.randomUUID().toString();
		this.balance = 0;
	}
	
	public void increaseBalance(double amount) {
		balance += amount;
	}
	
	public void decreaseBalance(double amount) {
		balance -= amount;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public String getName() {
		return name;
	}
	
	public String show() {
		return uid;
	}
	
	@Override public String toString() {
		return "Account(name:" + name + "; uid: " + uid + "; balance: " + balance + ")";
	}
}
