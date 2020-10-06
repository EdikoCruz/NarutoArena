package frames;

import java.awt.Color;
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

import com.sun.mail.handlers.image_gif;

import audio.Audio;

@SuppressWarnings("serial")
public class SelectCharacterFrame extends JPanel implements ActionListener {
	// x
	private Timer timer;

	static JFrame frame = new JFrame();

	
	//Images
	private Image rand, img1, img2, img3, img4, img5, img6, img7, img8;
	private Image selected1;
	private Image selected2;
	private Image choice1;
	private Image choice2;
	private Image name1;
	private Image name2;
	private Image ok1, ok2;
	private Image background;
	private ImageIcon reference;
	private Image back;
	
	
	static private int position1 = 2;
	static private int position2 = 3;

	static public boolean p1ok = false;
	static public boolean p2ok = false;

	public static boolean inLog = false;

	private boolean isPlaying = false;

	private int p1choice;
	private int p2choice;

	// Audio
	Audio audioMainSelect = new Audio("res/audio/AudioSelectP.mp3", 1);
	Audio sfxSelectP = new Audio("res/sfx/selectP.mp3", 0);
	Audio sfxEnterP = new Audio("res/sfx/enterP.mp3", 0);
	Audio sfxUnselectP = new Audio("res/sfx/unselectP.mp3", 0);

	public SelectCharacterFrame() {

		// Mouse Cursor Image
		Image cursorImage = Toolkit.getDefaultToolkit().getImage("res/img/options/mouseCursor.gif");

		// Assign image to a blank cursor
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "");

		// Assign custom cursor to Actual Cursor
		setCursor(blankCursor);

		frame.setUndecorated(true);
		frame.setVisible(false);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setExtendedState(frame.MAXIMIZED_BOTH);
		frame.add(this);

		referenceImages();

		setFocusable(true);
		setDoubleBuffered(true);
		addKeyListener(new KeyAdapterX());
		frame.addKeyListener(new KeyAdapterX());

		timer = new Timer(10, this);
		timer.start();

	}

	private void referenceImages() {
		reference = new ImageIcon("res/img/background/character_select.jpg");
		background = reference.getImage();

		reference = new ImageIcon("res/img/text/backnone.png");
		back = reference.getImage();
		
		reference = new ImageIcon("res/img/characters/CharSelected/selected1.png");
		selected1 = reference.getImage();
		reference = new ImageIcon("res/img/characters/CharSelected/selected2.png");
		selected2 = reference.getImage();

		reference = new ImageIcon("res/img/characters/CharSelect/randomb.png");
		rand = reference.getImage();

		reference = new ImageIcon("res/img/characters/CharSelect/1b.png");
		img1 = reference.getImage();

		reference = new ImageIcon("res/img/characters/CharSelect/2b.png");
		img2 = reference.getImage();

		reference = new ImageIcon("res/img/characters/CharSelect/3b.png");
		img3 = reference.getImage();

		reference = new ImageIcon("res/img/characters/CharSelect/4b.png");
		img4 = reference.getImage();

		reference = new ImageIcon("res/img/characters/CharSelect/5b.png");
		img5 = reference.getImage();

		reference = new ImageIcon("res/img/characters/CharSelect/6b.png");
		img6 = reference.getImage();

		reference = new ImageIcon("res/img/characters/CharSelect/7b.png");
		img7 = reference.getImage();

		reference = new ImageIcon("res/img/characters/CharSelect/8b.png");
		img8 = reference.getImage();


	}

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;

		graficos.drawImage(background, 0, 0, getWidth(), getHeight(), null);

		// names
		graficos.drawImage(name1, (int) (getWidth() * 0.06), (int) (getHeight() * 0.75), (int) (getWidth() * 0.13), (int) (getHeight() * 0.05), null);
		graficos.drawImage(name2, (int) (getWidth() * 0.815), (int) (getHeight() * 0.75), (int) (getWidth() * 0.13), (int) (getHeight() * 0.05), null);

		graficos.drawImage(choice1, (int) (getWidth() * 0.02), (int) (getHeight() * 0.25), (int) (getWidth() * 0.2), (int) (getHeight() * 0.48), null);

		graficos.drawImage(choice2, (int) (getWidth() * 0.78), (int) (getHeight() * 0.25), (int) (getWidth() * 0.2), (int) (getHeight() * 0.48), null);

		graficos.drawImage(rand, (int) (getWidth() * 0.47), (int) ((getHeight() / 2) - (rand.getHeight(null) / 2)), (int) (getWidth() * 0.06), (int) (getHeight() * 0.12), null);
		
		graficos.drawImage(back, (int) (getWidth() * 0.425), (int) (getHeight() *0.75), (int) (getWidth() * 0.15), (int) (getHeight() * 0.1), null);

		graficos.drawImage(img1, (int) ((getWidth() * 0.25)), (int) (getHeight() * 0.35), (int) (getWidth() * 0.06), (int) (getHeight() * 0.12), null);

		graficos.drawImage(img2, (int) ((getWidth() * 0.35)), (int) (getHeight() * 0.35), (int) (getWidth() * 0.06), (int) (getHeight() * 0.12), null);

		graficos.drawImage(img5, (int) ((getWidth() * 0.25)), (int) (getHeight() * 0.50), (int) (getWidth() * 0.06), (int) (getHeight() * 0.12), null);

		graficos.drawImage(img6, (int) ((getWidth() * 0.35)), (int) (getHeight() * 0.50), (int) (getWidth() * 0.06), (int) (getHeight() * 0.12), null);

		graficos.drawImage(img3, (int) ((getWidth() * 0.588)), (int) (getHeight() * 0.35), (int) (getWidth() * 0.06), (int) (getHeight() * 0.12), null);

		graficos.drawImage(img4, (int) ((getWidth() * 0.688)), (int) (getHeight() * 0.35), (int) (getWidth() * 0.06), (int) (getHeight() * 0.12), null);

		graficos.drawImage(img7, (int) ((getWidth() * 0.5888)), (int) (getHeight() * 0.50), (int) (getWidth() * 0.06), (int) (getHeight() * 0.12), null);

		graficos.drawImage(img8, (int) ((getWidth() * 0.6888)), (int) (getHeight() * 0.50), (int) (getWidth() * 0.06), (int) (getHeight() * 0.12), null);
		
		

		if (p1ok) {
			graficos.drawImage(selected1, (int) (getWidth() * 0.1), (int) (getHeight() * 0.65), (int) (getWidth() * 0.1), (int) (getHeight() * 0.15), null);
		}
		if (p2ok) {
			graficos.drawImage(selected2, (int) (getWidth() * 0.8), (int) (getHeight() * 0.65), (int) (getWidth() * 0.1), (int) (getHeight() * 0.15), null);
		}

		g.dispose();
	}

	public static void setVisible() {
		position1 = 2;
		position2 = 3;
		p1ok = false;
		p2ok = false;
		frame.setVisible(true);
	}

	public static void tornarInvisivel() {
		frame.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (frame.isVisible()) {
			if (OptionsMenuFrame.isStatusSound()) {
				if (!isPlaying) {
					audioMainSelect.playAudio();
					isPlaying = true;

				}
			} else {
				audioMainSelect.stopAudio();
				isPlaying = false;
			}
		} else {
			audioMainSelect.stopAudio();
			isPlaying = false;
		}

		if (isVisible() && p1ok && p2ok) {
			int combatParameters[] = { position1, position2 };
			CombatFrame.choice = combatParameters;
			CombatFrame.seted = false;
			// Combat.setVisible();

			position1 = 2;
			position2 = 3;
			p1ok = false;
			p2ok = false;

			new LoadingFrame();
			frame.setVisible(false);
		}
		reinstaciarImagens();
		repaint();
	}

	private class KeyAdapterX extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if (isVisible()) {
				if (!inLog) {
					if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						inLog = true;
						LogFrame.addLog("Entered in log\n");
						LogFrame.select = true;
						LogFrame.setVisible();
					}
					if (!p2ok) {
						if (e.getKeyCode() == KeyEvent.VK_LEFT) {

							switch (position2) {
							case 1:
								position2 = 1;
								break;
							case 2:
								position2 = 1;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 3:
								position2 = 9;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 4:
								position2 = 3;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 5:
								position2 = 5;
								break;
							case 6:
								position2 = 5;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 7:
								position2 = 9;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 8:
								position2 = 7;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 9:
								position2 = 2;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 10:
								position2 = 6;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
								
							}
						}
						if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

							switch (position2) {
							case 1:
								position2 = 2;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 2:
								position2 = 9;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 3:
								position2 = 4;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 4:
								position2 = 4;
								break;
							case 5:
								position2 = 6;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 6:
								position2 = 9;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 7:
								position2 = 8;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 8:
								position2 = 8;
								break;
							case 9:
								position2 = 3;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 10:
								position2 = 7;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							}
						}
						if (e.getKeyCode() == KeyEvent.VK_DOWN) {

							switch (position2) {
							case 1:
								position2 = 5;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 2:
								position2 = 6;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 3:
								position2 = 7;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 4:
								position2 = 8;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 5:
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								position2 = 10;
								break;
							case 6:
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								position2 = 10;
								break;
							case 7:
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								position2 = 10;
								break;
							case 8:
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								position2 = 10;
								break;
							case 9:
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								position2 = 10;
								break;
							case 10:
								position2 = 10;
								break;
							}
						}
						if (e.getKeyCode() == KeyEvent.VK_UP) {

							switch (position2) {
							case 1:
								position2 = 1;
								break;
							case 2:
								position2 = 2;
								break;
							case 3:
								position2 = 3;
								break;
							case 4:
								position2 = 4;
								break;
							case 5:
								position2 = 1;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 6:
								position2 = 2;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 7:
								position2 = 3;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 8:
								position2 = 4;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 9:
								position2 = 9;
								break;
							case 10:
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								position2 = 9;
								break;
							}
						}
					}
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {

						if (p2ok) {
							if (OptionsMenuFrame.isStatusSfx()) {
								sfxUnselectP.playAudio();
							}
							p2ok = false;
							LogFrame.addLog("Player 2  deselect ");
							switch (position2) {
							case 1:
								LogFrame.addLog("Kakashi\n");
								break;
							case 2:
								LogFrame.addLog("Naruto\n");
								break;
							case 3:
								LogFrame.addLog("Sasuke\n");
								break;
							case 4:
								LogFrame.addLog("Itachi\n");
								break;
							case 9:
								LogFrame.addLog("Random\n");
								break;
							}
						} else {
							if (position2 >= 1 && position2 <= 4 || position2 == 9 || position2 == 10) {
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxEnterP.playAudio();
								}
								p2ok = true;
								p2choice = position2;
								ok2 = choice2;
								LogFrame.addLog("Player 2 select ");
								switch (position2) {
								case 1:
									LogFrame.addLog("Kakashi\n");
									break;
								case 2:
									LogFrame.addLog("Naruto\n");
									break;
								case 3:
									LogFrame.addLog("Sasuke\n");
									break;
								case 4:
									LogFrame.addLog("Itachi\n");
									break;
								case 9:
									LogFrame.addLog("Random\n");
									break;
								case 10:
									position1 = 2;
									position2 = 3;
									p1ok = false;
									p2ok = false;
									LogFrame.addLog("Back to Main Menu\n");

									MainMenuFrame.setVisible();
									frame.setVisible(false);
									break;
								}
							}
						}
					}
					if (e.getKeyCode() == KeyEvent.VK_SPACE) {

						if (p1ok) {
							if (OptionsMenuFrame.isStatusSfx()) {
								sfxUnselectP.playAudio();
							}
							p1ok = false;
							LogFrame.addLog("Player 1  deselect ");
							switch (position1) {
							case 1:
								LogFrame.addLog("Kakashi\n");
								break;
							case 2:
								LogFrame.addLog("Naruto\n");
								break;
							case 3:
								LogFrame.addLog("Sasuke\n");
								break;
							case 4:
								LogFrame.addLog("Itachi\n");
								break;
							case 9:
								LogFrame.addLog("Random\n");
								break;
							}
						} else {
							if (position1 >= 1 && position1 <= 4 || position1 == 9|| position1 == 10) {
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxEnterP.playAudio();
								}
								p1ok = true;
								p1choice = position1;
								ok1 = choice1;
								LogFrame.addLog("Player 1 select ");
								switch (position1) {
								case 1:
									LogFrame.addLog("Kakashi\n");
									break;
								case 2:
									LogFrame.addLog("Naruto\n");
									break;
								case 3:
									LogFrame.addLog("Sasuke\n");
									break;
								case 4:
									LogFrame.addLog("Itachi\n");
									break;
								case 9:
									LogFrame.addLog("Random\n");
									break;
								case 10:
									position1 = 2;
									position2 = 3;
									p1ok = false;
									p2ok = false;
									LogFrame.addLog("Back to Main Menu\n");

									MainMenuFrame.setVisible();
									frame.setVisible(false);
									break;
								}
							}
						}
					}
					if (!p1ok) {
						if (e.getKeyCode() == KeyEvent.VK_A) {

							switch (position1) {
							case 1:
								position1 = 1;
								break;
							case 2:
								position1 = 1;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 3:
								position1 = 9;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 4:
								position1 = 3;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 5:
								position1 = 5;
								break;
							case 6:
								position1 = 5;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 7:
								position1 = 9;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 8:
								position1 = 7;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 9:
								position1 = 2;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 10:
								position1 = 6;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							}
						}
						if (e.getKeyCode() == KeyEvent.VK_D) {

							switch (position1) {
							case 1:
								position1 = 2;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 2:
								position1 = 9;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 3:
								position1 = 4;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 4:
								position1 = 4;
								break;
							case 5:
								position1 = 6;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 6:
								position1 = 9;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 7:
								position1 = 8;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 8:
								position1 = 8;
								break;
							case 9:
								position1 = 3;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 10:
								position1 = 7;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							}
						}



						if (e.getKeyCode() == KeyEvent.VK_S) {

							switch (position1) {
							case 1:
								position1 = 5;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 2:
								position1 = 6;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 3:
								position1 = 7;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 4:
								position1 = 8;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 5:
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								position1 = 10;
								break;
							case 6:
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								position1 = 10;
								break;
							case 7:
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								position1 = 10;
								break;
							case 8:
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								position1 = 10;
								break;
							case 9:
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								position1 = 10;
								break;
							case 10:
								position1 = 10;
								break;
							}
						}
						if (e.getKeyCode() == KeyEvent.VK_W) {

							switch (position1) {
							case 1:
								position1 = 1;
								break;
							case 2:
								position1 = 2;
								break;
							case 3:
								position1 = 3;
								break;
							case 4:
								position1 = 4;
								break;
							case 5:
								position1 = 1;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 6:
								position1 = 2;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 7:
								position1 = 3;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 8:
								position1 = 4;
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								break;
							case 9:
								position1 = 9;
								break;
							case 10:
								if (OptionsMenuFrame.isStatusSfx()) {
									sfxSelectP.playAudio();
								}
								position1 = 9;
								break;
							}
						}
					}
				}
			}
		}

		public void keyReleased(KeyEvent e) {

		}
	}

	private void reinstaciarImagens() {
		referenceImages();
		// names
		if(position1 != 10){
		switch (position1) {
		case 1:
			reference = new ImageIcon("res/img/text/Kakashi.png");
			break;
		case 2:
			reference = new ImageIcon("res/img/text/naruto.png");
			break;
		case 3:
			reference = new ImageIcon("res/img/text/sasuke.png");
			break;
		case 4:
			reference = new ImageIcon("res/img/text/itachi.png");
			break;
		case 5:
			reference = new ImageIcon("res/img/text/tsunade.png");
			break;
		case 6:
			reference = new ImageIcon("res/img/text/sakura.png");
			break;
		case 7:
			reference = new ImageIcon("res/img/text/Orochimaru.png");
			break;
		case 8:
			reference = new ImageIcon("res/img/text/Deidara.png");
			break;
		case 9:
			reference = new ImageIcon("res/img/text/random.png");
			break;
		}
		name1 = reference.getImage();
		}
	

		if(position2!=10){
		switch (position2) {
		case 1:
			reference = new ImageIcon("res/img/text/Kakashi.png");
			break;
		case 2:
			reference = new ImageIcon("res/img/text/naruto.png");
			break;
		case 3:
			reference = new ImageIcon("res/img/text/sasuke.png");
			break;
		case 4:
			reference = new ImageIcon("res/img/text/itachi.png");
			break;
		case 5:
			reference = new ImageIcon("res/img/text/tsunade.png");
			break;
		case 6:
			reference = new ImageIcon("res/img/text/sakura.png");
			break;
		case 7:
			reference = new ImageIcon("res/img/text/Orochimaru.png");
			break;
		case 8:
			reference = new ImageIcon("res/img/text/Deidara.png");
			break;
		case 9:
			reference = new ImageIcon("res/img/text/random.png");
			break;
		}
		name2 = reference.getImage();
		}

		if (position1 == position2) {
			switch (position1) {
			case 1:
				reference = new ImageIcon("res/img/characters/CharSelect/1r.png");
				img1 = reference.getImage();

				reference = new ImageIcon("res/img/characters/CharSelected/003.png");
				if (!p1ok) {
					choice1 = reference.getImage();
				} else {
					choice1 = ok1;
				}
				if (!p2ok) {
					choice2 = reference.getImage();
				} else {
					choice2 = ok2;
				}

				break;
			case 2:
				reference = new ImageIcon("res/img/characters/CharSelect/2r.png");
				img2 = reference.getImage();

				reference = new ImageIcon("res/img/characters/CharSelected/002.png");
				if (!p1ok) {
					choice1 = reference.getImage();
				} else {
					choice1 = ok1;
				}
				if (!p2ok) {
					choice2 = reference.getImage();
				} else {
					choice2 = ok2;
				}
				break;
			case 3:
				reference = new ImageIcon("res/img/characters/CharSelect/3r.png");
				img3 = reference.getImage();

				reference = new ImageIcon("res/img/characters/CharSelected/004.png");
				if (!p1ok) {
					choice1 = reference.getImage();
				} else {
					choice1 = ok1;
				}
				if (!p2ok) {
					choice2 = reference.getImage();
				} else {
					choice2 = ok2;
				}
				break;
			case 4:
				reference = new ImageIcon("res/img/characters/CharSelect/4r.png");
				img4 = reference.getImage();

				reference = new ImageIcon("res/img/characters/CharSelected/001.png");
				if (!p1ok) {
					choice1 = reference.getImage();
				} else {
					choice1 = ok1;
				}
				if (!p2ok) {
					choice2 = reference.getImage();
				} else {
					choice2 = ok2;
				}
				break;
			case 5:
				reference = new ImageIcon("res/img/characters/CharSelect/5r.png");
				img5 = reference.getImage();
				reference = new ImageIcon("res/img/characters/CharSelected/005-shadow.png");
				if (!p1ok) {
					choice1 = reference.getImage();
				} else {
					choice1 = ok1;
				}
				if (!p2ok) {
					choice2 = reference.getImage();
				} else {
					choice2 = ok2;
				}
				break;
			case 6:
				reference = new ImageIcon("res/img/characters/CharSelect/6r.png");
				img6 = reference.getImage();
				
				reference = new ImageIcon("res/img/characters/CharSelected/006-shadow.png");
				if (!p1ok) {
					choice1 = reference.getImage();
				} else {
					choice1 = ok1;
				}
				if (!p2ok) {
					choice2 = reference.getImage();
				} else {
					choice2 = ok2;
				}
				break;
			case 7:
				reference = new ImageIcon("res/img/characters/CharSelect/7r.png");
				img7 = reference.getImage();
				reference = new ImageIcon("res/img/characters/CharSelected/007-shadow.png");
				if (!p1ok) {
					choice1 = reference.getImage();
				} else {
					choice1 = ok1;
				}
				if (!p2ok) {
					choice2 = reference.getImage();
				} else {
					choice2 = ok2;
				}
				break;
			case 8:
				reference = new ImageIcon("res/img/characters/CharSelect/8r.png");
				img8 = reference.getImage();
				
				reference = new ImageIcon("res/img/characters/CharSelected/008-shadow.png");
				if (!p1ok) {
					choice1 = reference.getImage();
				} else {
					choice1 = ok1;
				}
				if (!p2ok) {
					choice2 = reference.getImage();
				} else {
					choice2 = ok2;
				}
				break;
			case 9:
				reference = new ImageIcon("res/img/characters/CharSelect/randomr.png");
				rand = reference.getImage();

				reference = new ImageIcon("res/img/characters/CharSelected/selecao-random.png");
				if (!p1ok) {
					choice1 = reference.getImage();
				} else {
					choice1 = ok1;
				}
				if (!p2ok) {
					choice2 = reference.getImage();
				} else {
					choice2 = ok2;
				}
				break;
			case 10:
				reference = new ImageIcon("res/img/text/backboth.png");
				back = reference.getImage();
				break;
			}

		} else {
			switch (position1) {
			case 1:
				reference = new ImageIcon("res/img/characters/CharSelect/1a.png");
				img1 = reference.getImage();
				reference = new ImageIcon("res/img/characters/CharSelected/003.png");
				if (!p1ok) {
					choice1 = reference.getImage();
				} else {
					choice1 = ok1;
				}
				break;
			case 2:
				reference = new ImageIcon("res/img/characters/CharSelect/2a.png");
				img2 = reference.getImage();

				reference = new ImageIcon("res/img/characters/CharSelected/002.png");
				if (!p1ok) {
					choice1 = reference.getImage();
				} else {
					choice1 = ok1;
				}
				break;
			case 3:
				reference = new ImageIcon("res/img/characters/CharSelect/3a.png");
				img3 = reference.getImage();

				reference = new ImageIcon("res/img/characters/CharSelected/004.png");
				if (!p1ok) {
					choice1 = reference.getImage();
				} else {
					choice1 = ok1;
				}
				break;
			case 4:
				reference = new ImageIcon("res/img/characters/CharSelect/4a.png");
				img4 = reference.getImage();

				reference = new ImageIcon("res/img/characters/CharSelected/001.png");
				if (!p1ok) {
					choice1 = reference.getImage();
				} else {
					choice1 = ok1;
				}
				break;
			case 5:
				reference = new ImageIcon("res/img/characters/CharSelect/5a.png");
				img5 = reference.getImage();
				if (p1ok) {
					choice1 = ok1;
				} else {
					reference = new ImageIcon("res/img/characters/CharSelected/005-shadow.png");
					choice1 = reference.getImage();
				}
				break;
			case 6:
				reference = new ImageIcon("res/img/characters/CharSelect/6a.png");
				img6 = reference.getImage();
				if (p1ok) {
					choice1 = ok1;
				} else {
					reference = new ImageIcon("res/img/characters/CharSelected/006-shadow.png");
					choice1 = reference.getImage();
				}
				break;
			case 7:
				reference = new ImageIcon("res/img/characters/CharSelect/7a.png");
				img7 = reference.getImage();
				if (p1ok) {
					choice1 = ok1;
				} else {
					reference = new ImageIcon("res/img/characters/CharSelected/007-shadow.png");
					choice1 = reference.getImage();
				}
				break;
			case 8:
				reference = new ImageIcon("res/img/characters/CharSelect/8a.png");
				img8 = reference.getImage();
				if (p1ok) {
					choice1 = ok1;
				} else {
					reference = new ImageIcon("res/img/characters/CharSelected/008-shadow.png");
					choice1 = reference.getImage();
				}
				break;
			case 9:
				reference = new ImageIcon("res/img/characters/CharSelect/randoma.png");
				rand = reference.getImage();

				reference = new ImageIcon("res/img/characters/CharSelected/selecao-random.png");
				if (!p1ok) {
					choice1 = reference.getImage();
				} else {
					choice1 = ok1;
				}
				break;
			case 10:
				reference = new ImageIcon("res/img/text/backp1.png");
				back = reference.getImage();
				break;
			}

			switch (position2) {
			case 1:
				reference = new ImageIcon("res/img/characters/CharSelect/1v.png");
				img1 = reference.getImage();

				reference = new ImageIcon("res/img/characters/CharSelected/003.png");
				if (!p2ok) {
					choice2 = reference.getImage();
				} else {
					choice2 = ok2;
				}
				break;
			case 2:
				reference = new ImageIcon("res/img/characters/CharSelect/2v.png");
				img2 = reference.getImage();

				reference = new ImageIcon("res/img/characters/CharSelected/002.png");
				if (!p2ok) {
					choice2 = reference.getImage();
				} else {
					choice2 = ok2;
				}
				break;
			case 3:
				reference = new ImageIcon("res/img/characters/CharSelect/3v.png");
				img3 = reference.getImage();

				reference = new ImageIcon("res/img/characters/CharSelected/004.png");
				if (!p2ok) {
					choice2 = reference.getImage();
				} else {
					choice2 = ok2;
				}
				break;
			case 4:
				reference = new ImageIcon("res/img/characters/CharSelect/4v.png");
				img4 = reference.getImage();

				reference = new ImageIcon("res/img/characters/CharSelected/001.png");
				if (!p2ok) {
					choice2 = reference.getImage();
				} else {
					choice2 = ok2;
				}
				break;
			case 5:
				reference = new ImageIcon("res/img/characters/CharSelect/5v.png");
				img5 = reference.getImage();
				if (p2ok) {
					choice2 = ok2;
				} else {
					reference = new ImageIcon("res/img/characters/CharSelected/005-shadow.png");
					choice2 = reference.getImage();
				}
				break;
			case 6:
				reference = new ImageIcon("res/img/characters/CharSelect/6v.png");
				img6 = reference.getImage();
				if (p2ok) {
					choice2 = ok2;
				} else {
					reference = new ImageIcon("res/img/characters/CharSelected/006-shadow.png");
					choice2 = reference.getImage();
				}
				break;
			case 7:
				reference = new ImageIcon("res/img/characters/CharSelect/7v.png");
				img7 = reference.getImage();
				if (p2ok) {
					choice2 = ok2;
				} else {
					reference = new ImageIcon("res/img/characters/CharSelected/007-shadow.png");
					choice2 = reference.getImage();
				}
				break;
			case 8:
				reference = new ImageIcon("res/img/characters/CharSelect/8v.png");
				img8 = reference.getImage();
				if (p2ok) {
					choice2 = ok2;
				} else {
					reference = new ImageIcon("res/img/characters/CharSelected/008-shadow.png");
					choice2 = reference.getImage();
				}
				break;
			case 9:
				reference = new ImageIcon("res/img/characters/CharSelect/randomv.png");
				rand = reference.getImage();

				reference = new ImageIcon("res/img/characters/CharSelected/selecao-random.png");
				if (!p2ok) {
					choice2 = reference.getImage();
				} else {
					choice2 = ok2;
				}
				break;
			case 10:
				reference = new ImageIcon("res/img/text/backp2.png");
				back = reference.getImage();
				break;
			}
		}
	}
}
