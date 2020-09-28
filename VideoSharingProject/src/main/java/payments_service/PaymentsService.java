package payments_service;

import platform_service.Account;
import platform_service.Advertiser;
import platform_service.User;
import platform_service.VideoSharingPlatform;

public final class PaymentsService {
	private static void validatePayment(Advertiser advertiser, double amount){
		double advertisersBalance = advertiser.getBalance();
		boolean validCondition = amount > 0 && amount < advertisersBalance;
		
        if (!validCondition)
            throw new IllegalArgumentException("Invalid value.");
	}
	
	private static void validateWithdraw(Account account, double amount) {
		if (account.getBalance() < amount)
			throw new IllegalArgumentException("Invalid value "
					+ "(withdrawal amount has to be smaller than balance.");
	}
	
	public void transferPayment(VideoSharingPlatform platform, User user, Advertiser advertiser, double amount) {
		validatePayment(advertiser, amount);
		
		final double transactionFee = platform.getTransactionFee();
		
		double userAmount = amount * (1-transactionFee);
		double platformAmount = amount * transactionFee;
		
		advertiser.decreaseBalance(amount);
		user.increaseBalance(userAmount);
		platform.increaseBalance(platformAmount);
	}
	
	public void rechargeBalance(Account account, double amount) {
		account.increaseBalance(amount);
	}
	
	public void withdrawBalance(Account account, double amount) {
		validateWithdraw(account, amount);
		account.decreaseBalance(amount);
	}
}
