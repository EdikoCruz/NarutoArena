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

import email.Client;

import audio.Audio;

public class VictoryFrame extends JPanel implements ActionListener {

	private ImageIcon reference;
	private Image background;
	private Image playerimg;

	private Timer timer;

	static JFrame frame = new JFrame();

	static public int type = 4, player = 1;

	public static boolean inLog = false;
	public static boolean mail = false;

	private boolean isPlaying = false;
	static boolean ref = true;

	Audio sfxLoading = new Audio("res/sfx/win.mp3", 0);
	static Client x = new Client();

	public VictoryFrame() {

		// Mouse Cursor Image
		Image cursorImage = Toolkit.getDefaultToolkit().getImage("res/img/options/mouseCursor.gif");

		// Assign image to a blank cursor
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "");

		// Assign custom cursor to Actual Cursor
		setCursor(blankCursor);

		setFocusable(true);
		setDoubleBuffered(true);

		frame.setUndecorated(true);
		frame.setVisible(false);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setExtendedState(frame.MAXIMIZED_BOTH);
		frame.add(this);

		addKeyListener(new KeyAdapterX());
		frame.addKeyListener(new KeyAdapterX());

		timer = new Timer(10, this);
		timer.start();
	}

	private void referenceImages() {
		switch (type) {
		case 1:
			reference = new ImageIcon("res/img/background/02.jpg");
			break;
		case 2:
			reference = new ImageIcon("res/img/background/03.jpg");
			break;
		case 3:
			reference = new ImageIcon("res/img/background/01.jpg");
			break;
		case 4:
			reference = new ImageIcon("res/img/background/00.jpg");
			break;
		default:
			reference = new ImageIcon("res/img/background/drawgame.jpg");
			break;
		}
		background = reference.getImage();

		if (player == 1) {
			reference = new ImageIcon("res/img/text/player1.png");
		} else if (player == 2) {
			reference = new ImageIcon("res/img/text/player2.png");
		} else {
			reference = new ImageIcon("res/img/text/transp.png");
		}
		playerimg = reference.getImage();
	}

	public void paint(Graphics g) {

		Graphics2D graphics = (Graphics2D) g;

		graphics.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		switch (type) {
		case 1:
			graphics.drawImage(playerimg, (int) (getWidth() * 0.02), (int) (getHeight() * 0.65), (int) (getWidth() * 0.3), (int) (getHeight() * 0.15), null);
			break;
		case 2:
			graphics.drawImage(playerimg, (int) (getWidth() * 0.7), (int) (getHeight() * 0.65), (int) (getWidth() * 0.3), (int) (getHeight() * 0.15), null);
			break;
		case 3:
			graphics.drawImage(playerimg, (int) (getWidth() * 0.05), (int) (getHeight() * 0.37), (int) (getWidth() * 0.3), (int) (getHeight() * 0.15), null);
			break;
		case 4:
			graphics.drawImage(playerimg, (int) (getWidth() * 0.7), (int) (getHeight() * 0.64), (int) (getWidth() * 0.3), (int) (getHeight() * 0.15), null);
			;
			break;
		}

		g.dispose();
	}

	// Set Visible
	public static void setVisible() {
		frame.setVisible(true);
		// After visible, will send email
		if (mail) {
			x.sendEmail();
		} else {
			LogFrame.addLog("E-mail is disabled.\n");
		}

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
			if (ref) {
				referenceImages();
				ref = false;
			}
		} else {
			sfxLoading.stopAudio();
			isPlaying = false;
		}

		repaint();
	}

	public void nextFrame() {
		MainMenuFrame.setVisible();
		frame.setVisible(false);
	}

	private class KeyAdapterX extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (!inLog) {

				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					inLog = true;
					LogFrame.addLog("Entered in log\n");
					LogFrame.option = true;
					LogFrame.setVisible();
				}
				if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_ENTER) {
					nextFrame();
				}
			}
		}

		public void keyReleased(KeyEvent e) {

		}
	}

}
