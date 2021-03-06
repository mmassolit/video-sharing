package video_service;

import java.util.LinkedList;

import platform_service.User;

public final class VideoManagingService {
	private final LinkedList<Video> fullVideoList = new LinkedList<Video>();
	
	public void addVideo(User user, String videoName) {
		LinkedList<Video> videoList = user.getVideoList();
		Video video = new Video(videoName, user.show());
		
		if (!videoList.contains(video)) {
			fullVideoList.addLast(video);
			videoList.addLast(video);
			user.setVideoList(videoList);
		}
	}
	
	public void deleteVideo(User user, Video video) {
		LinkedList<Video> videoList = user.getVideoList();
		
		if (videoList.contains(video)) {
			fullVideoList.remove(video);
			videoList.remove(video);
			user.setVideoList(videoList);
		}
	}
	
	public LinkedList<Video> getFullVideoList() {
		return fullVideoList;
	}
	
	public void playVideo(Video video) {
		video.addViews(1);
	}
}
