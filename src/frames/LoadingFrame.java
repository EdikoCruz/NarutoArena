package frames;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import audio.Audio;

public class LoadingFrame extends JPanel implements ActionListener {
	// x
	private Image LoadingImage;
	private Image loading;
	private Timer timer;

	static JFrame frame = new JFrame();

	private int contseg = 0;

	private boolean isPlaying = false;

	Audio sfxLoading = new Audio("res/sfx/loading.mp3", 1);

	public LoadingFrame() {

		// Mouse Cursor Image
		Image cursorImage = Toolkit.getDefaultToolkit().getImage("res/img/options/mouseCursor.gif");

		// Assign image to a blank cursor
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "");

		// Assign custom cursor to Actual Cursor
		setCursor(blankCursor);

		setFocusable(true);
		setDoubleBuffered(true);

		referenceImages();

		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setExtendedState(frame.MAXIMIZED_BOTH);
		frame.add(this);

		timer = new Timer(10, this);
		timer.start();
	}

	private void referenceImages() {
		ImageIcon reference;
		reference = new ImageIcon("res/img/background/loading.jpg");
		LoadingImage = reference.getImage();

		reference = new ImageIcon("res/img/logo/loadingText.gif");
		loading = reference.getImage();
	}

	public void paint(Graphics g) {

		Graphics2D graphics = (Graphics2D) g;

		graphics.drawImage(LoadingImage, 0, 0, getWidth(), getHeight(), this);

		graphics.drawImage(loading, (int) (getWidth() * 0.65), (int) (getHeight() * 0.863), (int) (getWidth() * 0.3), (int) (getHeight() * 0.1), this);

		g.dispose();
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
		if (frame.isVisible()) {
			if (OptionsMenuFrame.isStatusSound()) {
				if (!isPlaying) {
					sfxLoading.playAudio();
					isPlaying = true;
				}
			} else {
				sfxLoading.stopAudio();
				isPlaying = false;
			}
		} else {
			sfxLoading.stopAudio();
			isPlaying = false;
		}

		if (frame.isVisible()) {
			contseg++;
			if (contseg > 300) {
				nextFrame();
			}
		} else {
			contseg = 0;
		}

		repaint();
	}

	public void nextFrame() {
		sfxLoading.stopAudio()   ;
		CombatFrame.setVisible();
		frame.dispose();
	}

}
