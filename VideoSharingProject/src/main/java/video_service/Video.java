package video_service;

import java.util.UUID;

public final class Video {
	private final String name;
	private int views;
	private final String uid;
	
	public Video(String name) {
		this.name = name;
		this.uid = UUID.randomUUID().toString();
		this.views = 0;
	}
	
	public int getViews() {
		return views;
	}
	
	public void addViews(int amount) {
		views += amount;
	}
	
	@Override
	public String toString() {
		return "Video(name: " + name + "; uid: " + uid + "; views: " + views + ")";
	}
}
