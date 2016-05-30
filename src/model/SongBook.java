package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SongBook {

	private List<Song> songs;

	public SongBook() {
		songs = new ArrayList<>();
	}

	// add a song
	public void addSong(Song song) {
		songs.add(song);
	}

	// how many songs are added?:
	public int getSongCount() {
		return songs.size();
	}
// To be fixed : 
	//Cache is needed.
	private Map<String, List<Song>> byArtist() {
		Map<String, List<Song>> byArtist = new HashMap<>();
		for (Song song : songs) {
			List<Song> artistSongs = byArtist.get(song.getArtist());
			if (artistSongs == null) {
				artistSongs = new ArrayList<>();
				byArtist.put(song.getArtist(), artistSongs);
			}
			artistSongs.add(song);
		}
		return byArtist;
	}

	
	public Set<String> getArtist(){
		return byArtist().keySet();
	}
	
	public List<Song> getSongForArtist(String artistName){
		return byArtist().get(artistName);
	}
	
	
	
}
