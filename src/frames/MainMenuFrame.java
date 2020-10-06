package frames;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import audio.Audio;

@SuppressWarnings("serial")
public class MainMenuFrame extends JPanel implements ActionListener {
	// x
	static JFrame frame = new JFrame();

	private Image background, pointerSelect;

	private Timer timer;

	// Booleans to verify menu options
	static private boolean start = true;
	static private boolean options = false;
	static private boolean exit = false;
	static boolean inLog = false;

	// To check whether the music is playing
	private boolean isPlaying = false;

	// private boolean ok = true;

	// Introduction music, 1 to set loop
	Audio audioMain = new Audio("res/audio/main.mp3", 1);

	// SFX Select
	Audio sfxSelect = new Audio("res/sfx/selectP.mp3", 0);

	// SFX Enter
	Audio sfxEnter = new Audio("res/sfx/enter.mp3", 0);

	@SuppressWarnings("static-access")
	public MainMenuFrame() {

		// Mouse Cursor Image
		Image cursorImage = Toolkit.getDefaultToolkit().getImage("res/img/options/mouseCursor.gif");

		// Assign image to a blank cursor
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "");

		// Assign custom cursor to Actual Cursor
		setCursor(blankCursor);

		setFocusable(true);
		setDoubleBuffered(true);
		this.addKeyListener(new KeyAdapterX());
		frame.addKeyListener(new KeyAdapterX());

		setImages();

		// Without Title Bar
		frame.setUndecorated(true);

		// Visible
		frame.setVisible(false);

		// Frame close when Exit
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

		// Set Fullscreen mode
		frame.setExtendedState(frame.MAXIMIZED_BOTH);

		// Add panel(class) in frame
		frame.add(this);

		// Each 10ms repaint
		timer = new Timer(10, this);
		// Initialize
		timer.start();
	}

	// Set all images of this panel
	private void setImages() {

		ImageIcon reference;

		// Background Menu
		reference = new ImageIcon("res/img/background/startMenu.jpg");

		// Get the image from reference
		background = reference.getImage();

		// Pointer
		reference = new ImageIcon("res/img/options/optionCursor.gif");
		pointerSelect = reference.getImage();

	}

	// Draw canvas
	public void paint(Graphics foreG) {

		// Create backG behind foreG
		Graphics2D backG = (Graphics2D) foreG;

		// Draw the background in the backG
		backG.drawImage(background, 0, 0, getWidth(), getHeight(), this);

		// Draw point in a choose option
		if (start) {
			backG.drawImage(pointerSelect, (int) (getWidth() * 0.41), (int) (getHeight() * 0.8), (int) (getWidth() * 0.06), (int) (getHeight() * 0.08), this);
		} else if (options) {
			backG.drawImage(pointerSelect, (int) (getWidth() * 0.42), (int) (getHeight() * 0.84), (int) (getWidth() * 0.06), (int) (getHeight() * 0.08), this);
		} else if (exit) {
			backG.drawImage(pointerSelect, (int) (getWidth() * 0.44), (int) (getHeight() * 0.884), (int) (getWidth() * 0.06), (int) (getHeight() * 0.08), this);
		}

		// Destroy foreG and backG takes to front
		foreG.dispose();
	}

	// Set Visible
	public static void setVisible() {
		frame.setVisible(true);

	}

	// Set Invisible
	public static void setInvisible() {
		frame.setVisible(false);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		// If frame is visible and audio is not playing
		if (frame.isVisible()) {
			if (OptionsMenuFrame.isStatusSound()) {
				if (!isPlaying) {

					// play audio
					audioMain.play();
					isPlaying = true;

				}
			} else {
				// Stop audio
				audioMain.stopAudio();
				isPlaying = false;
			}
		} else {
			audioMain.stopAudio();
			isPlaying = false;
		}

		repaint();
	}

	private class KeyAdapterX extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {

			if (frame.isVisible()) {
				if (!inLog) {
					if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						inLog = true;
						LogFrame.addLog("Entered in log\n");
						LogFrame.main = true;
						LogFrame.setVisible();
					}

					// Moving down
					if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
						e.setKeyCode(KeyEvent.VK_DOWN);

						if (start) {
							start = false;
							options = true;
							if (OptionsMenuFrame.isStatusSfx()) {
								sfxSelect.playAudio();
							}
							exit = false;
						} else if (options) {
							start = false;
							options = false;
							exit = true;
							if (OptionsMenuFrame.isStatusSfx()) {
								sfxSelect.playAudio();
							}
						} else if (exit) {
							start = false;
							options = false;
							exit = true;

						}

					}
					// Moving up
					if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
						if (start) {
							start = true;
							options = false;
							exit = false;
						} else if (options) {
							start = true;
							if (OptionsMenuFrame.isStatusSfx()) {
								sfxSelect.playAudio();
							}
							options = false;
							exit = false;
						} else if (exit) {
							start = false;
							if (OptionsMenuFrame.isStatusSfx()) {
								sfxSelect.playAudio();
							}
							options = true;
							exit = false;

						}

					}

					// Enter to select an option
					if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {

						if (start) {
							if (OptionsMenuFrame.isStatusSfx()) {
								sfxEnter.playAudio();
							}
							LogFrame.addLog("Entered in Character Selection\n");
							SelectCharacterFrame.setVisible();
							frame.setVisible(false);
						} else if (options) {
							if (OptionsMenuFrame.isStatusSfx()) {
								sfxEnter.playAudio();
							}
							LogFrame.addLog("Entered in Opitions\n");
							OptionsMenuFrame.setVisible();
							frame.setVisible(false);
						} else if (exit) {
							// Close
							System.exit(0);
						}

					}

				}
			}
		}

		public void keyReleased(KeyEvent e) {

		}
	}

}
