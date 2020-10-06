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

public class CreditsFrame extends JPanel implements ActionListener {

	private ImageIcon reference;
	private Image background;
	private Image creditsImg;

	public static boolean inLog = false;

	private Timer timer;

	static JFrame frame = new JFrame();

	public CreditsFrame() {

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
		reference = new ImageIcon("res/img/background/credits.png");
		background = reference.getImage();

		reference = new ImageIcon("res/img/text/credits-full.png");
		creditsImg = reference.getImage();
	}

	public void paint(Graphics g) {

		Graphics2D graphics = (Graphics2D) g;

		graphics.drawImage(background, 0, 0, getWidth(), getHeight(), null);

		graphics.drawImage(creditsImg, (int) (getWidth() * 0.02), (int) (getHeight() * 0.4), (int) (getWidth() * 0.3), (int) (getHeight() * 0.5), null);

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

		repaint();
	}

	public void nextFrame() {
		OptionsMenuFrame.creditsFrame = false;
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
				if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
					nextFrame();
				}
			}
		}

		public void keyReleased(KeyEvent e) {

		}
	}

}
