package main_service;

import java.util.LinkedList;

import ads_service.Ad;
import ads_service.AdsManagingService;
import payments_service.PaymentsService;
import platform_service.Advertiser;
import platform_service.PlatformService;
import platform_service.User;
import platform_service.VideoSharingPlatform;
import video_service.Video;
import video_service.VideoManagingService;

public final class AppService {
	private final AdsManagingService adsService = new AdsManagingService();
	private final PaymentsService paymentsService = new PaymentsService();
	private final PlatformService platformService = new PlatformService();
	private final VideoManagingService videoService = new VideoManagingService();
	private VideoSharingPlatform platform;
	
	public void createPlatform(double transactionFee) {
		platform = new VideoSharingPlatform(transactionFee);
		
		System.out.println("Создан видеохостинг с комиссией " + transactionFee * 100 + "%.");
	}
	
	public VideoSharingPlatform getPlatform() {
		return platform;
	}
	
	public void createUser(String name) {
		User user = new User(name);
		platformService.addUser(user, platform);
		
		System.out.println("Добавлен пользователь " + name + ".");
	}
	
	public void createAdvertiser(String name) {
		Advertiser advertiser = new Advertiser(name);
		platformService.addAdvertiser(advertiser, platform);
		
		System.out.println("Добавлен рекламодатель " + name + ".");
	}
	
	public void createVideo(User user, String name) {
		videoService.addVideo(user, name);
		
		System.out.println("Пользователь " + user.getName() + " добавил видео " + name + ".");
	}
	
	public void createAd(Advertiser advertiser, String name, double cpm, double budget) {
		adsService.createAd(advertiser, name, cpm, budget);
		
		System.out.println("Рекламодатель " + advertiser.getName() + " добавил рекламу " + name +
				"(cpm = " + cpm + "; бюджет = " + budget + ").");
	}
	
	public void watchVideo(Video video) {
		videoService.playVideo(video);
		
		String videoOwnerId = video.getOwnerId();
		int videoOwnerIndex = 0;
	    
	    for (int i = 0; i < platform.getUserList().size(); i++) {
	    	if(platform.getAdvertiserList().get(i).show() == videoOwnerId);
	    		videoOwnerIndex = i;
	    }
	    
	    User videoOwner = this.platform.getUserList().get(videoOwnerIndex);
	    
	    System.out.println("Видео " + video.getName() + 
	    		" запущено (автор: " + videoOwner.getName() + ")");
	    
		Ad pickedAd = adsService.pickAd();
		System.out.println("Показана реклама " + pickedAd.getName() + "." +
				"\nСтоимость показа: " + pickedAd.getCpm()/1000 + "$");
		
		String adOwnerId = pickedAd.getOwnerId();			
		int adOwnerIndex = 0;
		
		for (int i = 0; i < platform.getAdvertiserList().size(); i++) {
			if (platform.getAdvertiserList().get(i).show() == adOwnerId)
				adOwnerIndex = i;
		}
		
		Advertiser adOwner = this.platform.getAdvertiserList().get(adOwnerIndex);
		
		System.out.println("Бюджет рекламной кампании до показа: " + pickedAd.getBudget() + "$." +
				"\nБаланс автора видео до показа: " + videoOwner.getBalance() + "$.");
		
		adsService.decreaseBudget(adOwner, pickedAd);
		paymentsService.transferPayment(platform, videoOwner, adOwner, pickedAd.getCpm()/1000);
		System.out.println("Бюджет рекламной кампании после показа: " + pickedAd.getBudget() + "$." +
				"\nБаланс автора видео после показа: " + videoOwner.getBalance() + "$. ");
	}
	
	public void rechargeAllAdvertisers(double amount) {
		for (int i = 0; i < this.platform.getAdvertiserList().size(); i++) {
			System.out.println("К балансу рекламодателя " + this.platform.getAdvertiserList().get(i).getName() +
					" добавлено " + amount + "$.");
			 
			paymentsService.rechargeAdvertiserBalance(this.platform.getAdvertiserList().get(i), amount);
		}
	}
}
