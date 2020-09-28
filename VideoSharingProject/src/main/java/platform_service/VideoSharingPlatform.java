package platform_service;

import java.util.LinkedList;

public final class VideoSharingPlatform {
	private double balance;
	private double transactionFee;
	private LinkedList<User> userList;
	private LinkedList<Advertiser> advertiserList;
	
	
	public VideoSharingPlatform(double transactionFee) {
		validateFee(transactionFee);
		
		this.transactionFee = transactionFee;
		this.balance = 0;
		this.userList = new LinkedList<User>();
		this.advertiserList = new LinkedList<Advertiser>();
	}
	
	private static void validateFee(double transactionFee){
		boolean validCondition = transactionFee >= 0 && transactionFee <= 1;
		
        if (!validCondition)
            throw new IllegalArgumentException("Invalid value (it has to be between 0 and 1).");
	}
	
	public double getTransactionFee() {
		return this.transactionFee;
	}
	
	public void setTransactionFee(double transactionFee) {
		validateFee(transactionFee);
		this.transactionFee = transactionFee;
	}
	
	public LinkedList<User> getUserList(){
		return this.userList;
	}
	
	public void setUserList(LinkedList<User> userList) {
		this.userList = userList;
	}
	
	
	public LinkedList<Advertiser> getAdvertiserList(){
		return this.advertiserList;
	}
	
	public void setAdvertiserList(LinkedList<Advertiser> advertiserList) {
		this.advertiserList = advertiserList;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void increaseBalance(double amount) {
		this.balance += amount;
	}
	
	public void decreaseBalance(double amount) {
		this.balance -= amount;
	}
	
	@Override
	public String toString() {
		return "VideoSharingPlatform(balance: " + balance + "; fee: " + transactionFee + ")";
	}
}
