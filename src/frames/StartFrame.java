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

@SuppressWarnings("serial")
public class StartFrame extends JPanel implements ActionListener {
	// x

	private Image startImage;
	private Timer timer;

	static JFrame frame = new JFrame();

	private int contseg = 0;

	@SuppressWarnings("static-access")
	public StartFrame() {

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
		reference = new ImageIcon("res/img/logo/startLogo.gif");
		startImage = reference.getImage();
	}

	public void paint(Graphics g) {

		Graphics2D graficos = (Graphics2D) g;

		graficos.fillRect(0, 0, getWidth(), getHeight());

		graficos.drawImage(startImage, (int) ((getWidth() / 2) - (startImage.getWidth(null) / 2)), (int) ((getHeight() / 2) - (startImage.getHeight(null) / 2)), this);

		g.dispose();
	}

	public static void setVisible() {
		frame.setVisible(true);
	}

	public static void setInvisible() {
		frame.setVisible(false);
	}

	// Time of loading
	@Override
	public void actionPerformed(ActionEvent arg0) {
		contseg++;
		if (contseg > 400 && frame.isVisible()) {
			nextFrame();
		}
		repaint();
	}

	// Call nextFrame and set actual frame to false
	public void nextFrame() {
		MainMenuFrame.setVisible();
		frame.setVisible(false);
	}
}
