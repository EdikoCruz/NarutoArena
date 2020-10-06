package audio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import frames.LogFrame;

import javazoom.jl.player.Player;

public class Audio {

	// x

	// Name of track
	private String filename;

	// Player
	private Player player;

	// Option to set play once or loop
	private int op;

	// constructor that takes the name of an MP3 file and op
	public Audio(String filename, int op) {
		this.filename = filename;
		this.op = op;
	}

	public void close() {
		if (player != null) {
			player.close();
		}
	}

	// play the MP3 file to the sound card

	public void play() {
		try {
			// obtains input bytes from a file
			FileInputStream fis = new FileInputStream(filename);

			// Add FileInputStream in a buffer array
			BufferedInputStream bis = new BufferedInputStream(fis);

			// Create a player with bufferedinputstream
			player = new Player(bis);

			// Register in Log
		} catch (Exception e) {
			LogFrame.addLog(("Problem playing file " + filename));
			System.err.println(e);

		}

		// run in new thread to play in background
		new Thread() {

			// Run
			public void run() {
				try {
					// Play Audio
					player.play();

					// If op = 1
					if (op == 1) {
						// Play music again
						if (player.isComplete()) {
							play();
						}
					}

				} catch (Exception e) {
					System.err.println(e);
				}
			}// end of run()

		}// end of Thread()
		.start();// Start Thread

	}// end of play()

	// Stop Audio
	public void stopAudio() {
		close();
	}

	// Play Audio
	public void playAudio() {
		play();
	}
}// end of Audio Class
