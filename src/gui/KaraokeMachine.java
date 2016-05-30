package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import model.Song;
import model.SongBook;

public class KaraokeMachine {

	private SongBook songBook;
	private BufferedReader reader;
	private Map<String, String> menu;
	private Queue<Song> songQueue;
	
	public KaraokeMachine(SongBook songBook) {
		songQueue = new ArrayDeque<Song>();
		this.songBook = songBook;
		reader = new BufferedReader(new InputStreamReader(System.in));
		menu = new HashMap<String, String>();
		menu.put("add", "Add a new song to the song book");
		menu.put("play", "Play a song from the Queue");
		menu.put("choose", "Choose a song");
		menu.put("quit", "Exit the program");
	}

	private String promptAction() throws IOException {
		System.out.printf("There are %d songs available. "
				+ " In the queue:" + " " + songQueue.size()  + "\nYour options are: %n", songBook.getSongCount());
		for (Map.Entry<String, String> option : menu.entrySet()) {
			System.out.printf("%s - %s %n", option.getKey(), option.getValue());
		}
		System.out.print("What do you want to do: ");
		String choice = reader.readLine();
		return choice.trim().toLowerCase();

	}

	public void run() {
		String choice = "";
		do {
			try {
				choice = promptAction();
				switch (choice) {

				case "add":
					Song song = promptNewSong();
					songBook.addSong(song);
					System.out.print(song + " - added! \n");
					break;
				case "choose":
					System.out.println();
					String artist = promptArtist();
					Song artistSong = promptForSong(artist);
					// ADD TO THE SONG QUEUE
					songQueue.add(artistSong);
					System.out.println("You chose : " + artistSong);
					break;
				case "play":
					playNext();
					break;
				case "quit":
					System.out.println("Thank you for playing");
					break;
				default:
					System.out.println("Whats this choice?????. Try again");

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("!!Problem with Input!!");
				e.printStackTrace();
			}
		} while (!choice.equals("quit"));
	}

	private Song promptNewSong() throws IOException {
		System.out.print("Enter artist name: ");
		String artist = reader.readLine();
		System.out.print("Title: ");
		String title = reader.readLine();
		System.out.print("Video URL: ");
		String url = reader.readLine();
		return new Song(artist, title, url);
	}

	private String promptArtist() throws IOException {
		System.out.println("Available artist: ");
		List<String> artist = new ArrayList<>(songBook.getArtist());
		int index = promptForIndex(artist);
		return artist.get(index);
	}
	private Song promptForSong(String artist)throws IOException{
		List<Song> songs = songBook.getSongForArtist(artist);
		List<String> songTitle = new ArrayList<>();
		for(Song song : songs){
			songTitle.add(song.getTitle());
		}
		System.out.println("Available songs for: " + artist);
		int index = promptForIndex(songTitle);
		return songs.get(index);
	}

	private int promptForIndex(List<String> options) throws IOException {
		int counter = 1;
		for (String option : options) {
			System.out.printf("%d.)  %s %n", counter, option);
			counter++;
		}
		System.out.print("Your choice: ");
		String optionAsString = reader.readLine();
		int choice = Integer.parseInt(optionAsString.trim());		
		return choice - 1;
	}
	
	public void playNext(){
		Song song = songQueue.poll();
		if(song == null){
			System.out.println("No songs in the queue, use the menu to add some");
		}else{
			System.out.printf("%n%n%n Open to hear %s %n%n%n", song.getVidUrl(),
			song.getTitle(), song.getArtist());
		}
	}
}
