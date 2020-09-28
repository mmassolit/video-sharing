package main_service;

import java.util.LinkedList;

import ads_service.Ad;
import ads_service.AdsManagingService;
import payments_service.PaymentsService;
import platform_service.Advertiser;
import platform_service.PlatformService;
import platform_service.User;
import platform_service.VideoSharingPlatform;
import playback_service.PlaybackService;
import video_service.Video;
import video_service.VideoManagingService;

public final class AppService {
	private final AdsManagingService adsService = new AdsManagingService();
	private final PaymentsService paymentsService = new PaymentsService();
	private final PlatformService platformService = new PlatformService();
	private final PlaybackService playbackService = new PlaybackService();
	private final VideoManagingService videoService = new VideoManagingService();
	private VideoSharingPlatform platform;
	
	public void createPlatform(double transactionFee) {
		platform = new VideoSharingPlatform(transactionFee);
		
		System.out.println("Создан видеохостинг с комиссией " + transactionFee * 100 + "%.");
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
		Video video = new Video(name);
		videoService.addVideo(user, video);
		
		System.out.println("Пользователь " + user.getName() + "добавил видео " + name + ".");
	}
	
	public void createAd(Advertiser advertiser, String name, double cpm, double budget) {
		Ad ad = new Ad(name, cpm, budget);
		adsService.createAd(advertiser, ad);
		
		System.out.println("Рекламодатель " + advertiser.getName() + "добавил рекламу " + name +
				"(cpm = " + cpm + "; бюджет = " + budget + ").");
	}
	
	public void printUsers() {
		LinkedList<User> userList = this.platform.getUserList();
		System.out.println(userList);
	}
	
//	public void watchVideo(User videoOwner, Video video) {
//		playbackService.playVideo(video);
//		Ad pickedAd = adsService.pickAd();
//		adsService.decreaseBudget(pickedAd);
//		paymentsService.
//	}
}
