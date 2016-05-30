
import java.util.HashSet;
import java.util.Set;
import gui.*;
import model.*;

public class Karaoke {
	
	public static void main(String[] args) {

		
		SongBook songBook = new SongBook();		
		KaraokeMachine machine = new KaraokeMachine(songBook);
		machine.run();
	}
	

}
