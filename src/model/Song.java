package model;

/**
 * 
 * Immutable!
 *
 */

public class Song {

	private  String artist;
	private String title;
	private String vidUrl;
	
	public Song(String artist, String title, String vidUrl) {
		
		this.artist = artist;
		this.title = title;
		this.vidUrl = vidUrl;
	}

	
	
	public String getArtist() {
		return artist;
	}

	public String getTitle() {
		return title;
	}

	public String getVidUrl() {
		return vidUrl;
	}



	@Override
	public String toString() {
		return "Song [artist: " + artist + ", title: " + title ;
	}
	
	
	
	
}
