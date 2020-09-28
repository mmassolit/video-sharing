package playback_service;

import video_service.Video;

public class PlaybackService {
	public void playVideo(Video video) {
		video.addViews(1);
	}
}
