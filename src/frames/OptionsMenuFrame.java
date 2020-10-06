package frames;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
public class OptionsMenuFrame extends JPanel implements ActionListener {
	// x
	static JFrame frame = new JFrame();

	private Image background, pointer, on, off,creditsImg;

	private Timer timer;

	// To set sounds on/off
	static private boolean statusSound = true;
	static private boolean statusSfx = true;

	static boolean inLog = false;
	static boolean creditsFrame = false;

	static private boolean sound = true;
	static private boolean sfx = false;
	static private boolean back = false;
	static private boolean credits = false;

	private boolean isPlaying = false;
	

	// Music Options, 1 to set loop
	Audio audioOptions = new Audio("res/audio/options.mp3", 1);

	// Sfx Select
	Audio sfxSelectP = new Audio("res/sfx/selectP.mp3", 0);

	// Sfx Enter
	Audio sfxEnter = new Audio("res/sfx/enter.mp3", 0);

	@SuppressWarnings("static-access")
	public OptionsMenuFrame() {

		setFocusable(true);
		setDoubleBuffered(true);
		this.addKeyListener(new KeyAdapterX());
		frame.addKeyListener(new KeyAdapterX());

		referenceImages();

		frame.setUndecorated(true);
		frame.setVisible(false);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setExtendedState(frame.MAXIMIZED_BOTH);
		frame.add(this);

		timer = new Timer(10, this);
		timer.start();
	}

	private void referenceImages() {

		// Creating ImageIcon
		ImageIcon reference;
		
		// Credits
		reference = new ImageIcon("res/img/text/credits.png");
		creditsImg = reference.getImage();

		// Options Menu Background
		reference = new ImageIcon("res/img/background/optionsMenu.jpg");
		background = reference.getImage();

		// Pointer
		reference = new ImageIcon("res/img/options/optionCursor.gif");
		pointer = reference.getImage();

		// (On) Option
		reference = new ImageIcon("res/img/options/on.png");
		on = reference.getImage();
		// (Off) Option
		reference = new ImageIcon("res/img/options/off.png");
		off = reference.getImage();

	}

	public void paint(Graphics g) {

		Graphics2D graphics = (Graphics2D) g;

		graphics.drawImage(background, 0, 0, getWidth(), getHeight(), this);
		graphics.drawImage(creditsImg, (int) (getWidth()*0.05), (int) (getHeight()*0.94), (int) (getWidth()*0.06), (int) (getHeight()*0.03), this);

		if (statusSound) {
			graphics.drawImage(on, (int) (getWidth() * 0.35), (int) (getHeight() * 0.24), (int) (getWidth() * 0.025), (int) (getHeight() * 0.025), this);

		} else {
			graphics.drawImage(off, (int) (getWidth() * 0.35), (int) (getHeight() * 0.239), (int) (getWidth() * 0.025), (int) (getHeight() * 0.025), this);
		}

		if (statusSfx) {
			graphics.drawImage(on, (int) (getWidth() * 0.35), (int) (getHeight() * 0.28), (int) (getWidth() * 0.025), (int) (getHeight() * 0.025), this);
		} else {
			graphics.drawImage(off, (int) (getWidth() * 0.35), (int) (getHeight() * 0.279), (int) (getWidth() * 0.025), (int) (getHeight() * 0.025), this);
		}

		if (sound) {
			graphics.drawImage(pointer, (int) (getWidth() * 0.25), (int) (getHeight() * 0.21), (int) (getWidth() * 0.06), (int) (getHeight() * 0.08), this);
		} else if (sfx) {
			graphics.drawImage(pointer, (int) (getWidth() * 0.25), (int) (getHeight() * 0.245), (int) (getWidth() * 0.06), (int) (getHeight() * 0.08), this);
		} else if (back) {
			graphics.drawImage(pointer, (int) (getWidth() * 0.25), (int) (getHeight() * 0.285), (int) (getWidth() * 0.06), (int) (getHeight() * 0.08), this);
		} else if(credits){
			graphics.drawImage(pointer, (int) (getWidth()*0.015), (int) (getHeight()*0.915), (int) (getWidth() * 0.06), (int) (getHeight() * 0.08), this);
		}

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

	// To Check Music Option (on/off)
	public static boolean isStatusSound() {
		return statusSound;
	}

	// To Check Sfx Option (on/off)
	public static boolean isStatusSfx() {
		return statusSfx;
	}

	// This option set if sound will play (Music)
	public void changeStatusSound() {
		if (statusSound) {
			statusSound = false;
		} else {
			statusSound = true;
		}
	}

	// This option set if sound will play (SFX)
	public void mudarStatusSfx() {
		if (statusSfx) {
			statusSfx = false;
		} else {
			statusSfx = true;
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (frame.isVisible()) {
			if (OptionsMenuFrame.isStatusSound()) {
				if (!isPlaying) {
					audioOptions.playAudio();
					isPlaying = true;

				}
			} else {
				audioOptions.stopAudio();
				isPlaying = false;
			}
		} else {
			audioOptions.stopAudio();
			isPlaying = false;
		}

		repaint();
	}

	private class KeyAdapterX extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {

			if (frame.isVisible()) {
				if (!inLog) {
					if (e.getKeyCode() == KeyEvent.VK_M) {
						if(VictoryFrame.mail){
							VictoryFrame.mail = false;
							LogFrame.addLog("E-mail disabled\n");
						}else{
							VictoryFrame.mail = true;
							LogFrame.addLog("E-mail enabled\n");
						}
					}
					if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						inLog = true;
						LogFrame.addLog("Entered in log\n");
						LogFrame.option = true;
						LogFrame.setVisible();
					}
					if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
						if (sound) {
							if (isStatusSfx()) {
								sfxSelectP.playAudio();
							}
							sound = false;
							sfx = true;
							back = false;
							credits = false;
						} else if (sfx) {
							if (isStatusSfx()) {
								sfxSelectP.playAudio();
							}
							sound = false;
							sfx = false;
							back = true;
							credits = false;
						} else if (back) {
							if (isStatusSfx()) {
								sfxSelectP.playAudio();
							}
							sound = false;
							sfx = false;
							back = false;
							credits = true;

						}else if (credits) {
							if (isStatusSfx()) {
								sfxSelectP.playAudio();
							}
							sound = false;
							sfx = false;
							back = false;
							credits = true;
						}


					}
					if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
						if (sound) {
							sound = true;
							sfx = false;
							back = false;
						} else if (sfx) {
							if (isStatusSfx()) {
								sfxSelectP.playAudio();
							}
							sound = true;
							sfx = false;
							back = false;
						} else if (back) {
							if (isStatusSfx()) {
								sfxSelectP.playAudio();
							}
							sound = false;
							sfx = true;
							back = false;

						}else if (credits) {
							if (isStatusSfx()) {
								sfxSelectP.playAudio();
							}
							sound = false;
							sfx = false;
							back = true;
							credits = false;
						}

					}

					if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
						if (sound) {
							if (statusSound) {
								LogFrame.addLog("Sound turned off\n");
							} else {
								LogFrame.addLog("Sound turned on\n");
							}
							if (isStatusSfx()) {
								sfxEnter.playAudio();
							}
							changeStatusSound();
						} else if (sfx) {
							if (statusSfx) {
								LogFrame.addLog("Sfx turned off\n");
							} else {
								LogFrame.addLog("Sfx turned on\n");
							}
							mudarStatusSfx();
							if (isStatusSfx()) {
								sfxEnter.playAudio();
							}
						} else if (back) {
							if (isStatusSfx()) {
								sfxEnter.playAudio();
							}
							LogFrame.addLog("Back to Main Menu\n");
							MainMenuFrame.setVisible();
							frame.setVisible(false);
						}else if (credits) {
							if (isStatusSfx()) {
								sfxEnter.playAudio();
							}
							LogFrame.addLog("Entered in Credits\n");
							CreditsFrame.setVisible();
							creditsFrame = true;
						}

					}
					if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_D) {
						if(sound || sfx){							
							if (sound) {
								if (isStatusSfx()) {
									sfxEnter.playAudio();
								}
								changeStatusSound();
							} else if (sfx) {
								sfxEnter.playAudio();
								mudarStatusSfx();
							}
						}
					}
				}
			}
		}

		public void keyReleased(KeyEvent e) {

		}
	}

}
