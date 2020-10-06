package frames;

import java.awt.Color;
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

import system.Character;
import system.Itachi;
import system.Kakashi;
import system.Naruto;
import system.Sasuke;
import audio.Audio;

@SuppressWarnings("serial")
public class CombatFrame extends JPanel implements ActionListener {
	// x
	static public int choice[] = { 1, 1 };
	String sp1,sp2;

	static public boolean seted = true;

	int attacker = 0;
	int defender = 0;
	double calculo, calculoAux;

	int fontSize;

	private ImageIcon reference;

	private Image skillArea;

	public static boolean inLog = false;

	// TO call nextFrame after press enter
	private boolean wins = false;
	private boolean press = false;

	// Images
	private Image background;
	private Image Pl1Avatar;
	private Image Pl1ContainerLife;
	private Image Pl1LifeBar;
	private Image pl1ImgButton1;
	private Image pl1ImgButton2;
	private Image pl1ImgButton3;
	private Image pl1ImgButton4;
	private Image pl1ImgButton5;
	private Image Pl2Avatar;
	private Image Pl2ContainerLife;
	private Image Pl2LifeBar;
	private Image pl2ImgButton1;
	private Image pl2ImgButton2;
	private Image pl2ImgButton3;
	private Image pl2ImgButton4;
	private Image pl2ImgButton5;

	// Audio & SFX
	Audio audioCombat = new Audio("res/audio/combatAudio.mp3", 1);
	Audio p1Choice = new Audio("res/sfx/p1Choice.mp3", 0);
	Audio p2Choice = new Audio("res/sfx/p2Choice.mp3", 0);
	Audio pass = new Audio("res/sfx/pass.mp3", 0);
	Audio sfxSelect = new Audio("res/sfx/selectP.mp3", 0);
	private boolean isPlaying;

	// 2 Players
	Character[] players = new Character[2];
	private Timer timer;

	// Skill position (circle)
	int position1 = 0;
	int position2 = 0;

	static JFrame frame = new JFrame();

	@SuppressWarnings("static-access")
	public CombatFrame() {
		
		// Start instantiating, but will set again each game
		int choice[] = { 1, 1 };
		players[0] = new Naruto();
		players[1] = new Naruto();

		// Name of Players
		players[0].playerName = ("Player 1");
		players[1].playerName = ("Player 2");

		// Call Images
		referenceImages();
		frame.setUndecorated(true);
		frame.setVisible(false);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setExtendedState(frame.MAXIMIZED_BOTH);
		frame.add(this);

		setFocusable(true);
		setDoubleBuffered(true);
		addKeyListener(new KeyAdapterX());
		frame.addKeyListener(new KeyAdapterX());

		timer = new Timer(100, this);
		timer.start();

	}

	private void referenceImages() {
		reference = new ImageIcon("res/img/battle/Others/background.jpg");
		background = reference.getImage();

		// Avatar

		// Player 1
		// in your turn
		if (attacker == 0) {
			switch (choice[0]) {
			case 1:
				reference = new ImageIcon("res/img/battle/char/1kakSelected.png");
				break;
			case 2:
				reference = new ImageIcon("res/img/battle/char/1narSelected.png");
				break;
			case 3:
				reference = new ImageIcon("res/img/battle/char/1sasSelected.png");
				break;
			case 4:
				reference = new ImageIcon("res/img/battle/char/1itaSelected.png");
				break;
			}
			// waiting
		} else {
			switch (choice[0]) {
			case 1:
				reference = new ImageIcon("res/img/battle/char/1kakWaiting.png");
				break;
			case 2:
				reference = new ImageIcon("res/img/battle/char/1narWaiting.png");
				break;
			case 3:
				reference = new ImageIcon("res/img/battle/char/1sasWaiting.png");
				break;
			case 4:
				reference = new ImageIcon("res/img/battle/char/1itaWaiting.png");
				break;
			}
		}
		Pl1Avatar = reference.getImage();

		// Container of Life Bar
		reference = new ImageIcon("res/img/battle/Others/p1bg_bar.png");
		Pl1ContainerLife = reference.getImage();

		// Life Bar
		reference = new ImageIcon("res/img/battle/Others/p1lifeBar.png");
		Pl1LifeBar = reference.getImage();

		if (position1 == 1) {
			reference = new ImageIcon("res/img/battle/numbers/1c.png");
		} else {
			if (players[0].getAbilityTurn1() <= 0 && players[0].getChakra() >= players[0].getChakraMod1()) {
				reference = new ImageIcon("res/img/battle/numbers/1b.png");
			} else {
				reference = new ImageIcon("res/img/battle/numbers/1a.png");
			}
		}
		pl1ImgButton1 = reference.getImage();

		if (position1 == 2) {
			reference = new ImageIcon("res/img/battle/numbers/2c.png");
		} else {
			if (players[0].getAbilityTurn2() <= 0 && players[0].getChakra() >= players[0].getChakraMod2()) {
				reference = new ImageIcon("res/img/battle/numbers/2b.png");
			} else {
				reference = new ImageIcon("res/img/battle/numbers/2a.png");
			}
		}
		pl1ImgButton2 = reference.getImage();

		if (position1 == 3) {
			reference = new ImageIcon("res/img/battle/numbers/3c.png");
		} else {
			if (players[0].getAbilityTurn3() <= 0 && players[0].getChakra() >= players[0].getChakraMod3()) {
				reference = new ImageIcon("res/img/battle/numbers/3b.png");
			} else {
				reference = new ImageIcon("res/img/battle/numbers/3a.png");
			}
		}
		pl1ImgButton3 = reference.getImage();

		if (position1 == 4) {
			reference = new ImageIcon("res/img/battle/numbers/4c.png");
		} else {
			if (players[0].getAbilityTurn4() <= 0 && players[0].getChakra() >= players[0].getChakraMod4()) {
				reference = new ImageIcon("res/img/battle/numbers/4b.png");
			} else {
				reference = new ImageIcon("res/img/battle/numbers/4a.png");
			}
		}
		pl1ImgButton4 = reference.getImage();
		if (position1 == 5) {
			reference = new ImageIcon("res/img/battle/numbers/5c.png");
		} else {
			reference = new ImageIcon("res/img/battle/numbers/5b.png");
		}
		pl1ImgButton5 = reference.getImage();

		reference = new ImageIcon("res/img/battle/Others/skillArea.jpg");
		skillArea = reference.getImage();

		// Avatar

		// Player 2
		// in your turn
		if (attacker == 1) {
			switch (choice[1]) {
			case 1:
				reference = new ImageIcon("res/img/battle/char/2kakSelected.png");
				break;
			case 2:
				reference = new ImageIcon("res/img/battle/char/2narSelected.png");
				break;
			case 3:
				reference = new ImageIcon("res/img/battle/char/2sasSelected.png");
				break;
			case 4:
				reference = new ImageIcon("res/img/battle/char/2itaSelected.png");
				break;
			}
			// waiting
		} else {
			switch (choice[1]) {
			case 1:
				reference = new ImageIcon("res/img/battle/char/2kakWaiting.png");
				break;
			case 2:
				reference = new ImageIcon("res/img/battle/char/2narWaiting.png");
				break;
			case 3:
				reference = new ImageIcon("res/img/battle/char/2sasWaiting.png");
				break;
			case 4:
				reference = new ImageIcon("res/img/battle/char/2itaWaiting.png");
				break;
			}
		}
		Pl2Avatar = reference.getImage();

		// Container of Life Bar
		reference = new ImageIcon("res/img/battle/Others/p2bg_bar.png");
		Pl2ContainerLife = reference.getImage();

		// Life Bar
		reference = new ImageIcon("res/img/battle/Others/p2lifeBar.png");
		Pl2LifeBar = reference.getImage();
		if (position2 == 1) {
			reference = new ImageIcon("res/img/battle/numbers/1d.png");
		} else {
			if (players[1].getAbilityTurn1() <= 0 && players[1].getChakra() >= players[1].getChakraMod1()) {
				reference = new ImageIcon("res/img/battle/numbers/1b.png");
			} else {
				reference = new ImageIcon("res/img/battle/numbers/1a.png");
			}
		}
		pl2ImgButton1 = reference.getImage();
		if (position2 == 2) {
			reference = new ImageIcon("res/img/battle/numbers/2d.png");
		} else {
			if (players[1].getAbilityTurn2() <= 0 && players[1].getChakra() >= players[1].getChakraMod2()) {
				reference = new ImageIcon("res/img/battle/numbers/2b.png");
			} else {
				reference = new ImageIcon("res/img/battle/numbers/2a.png");
			}
		}
		pl2ImgButton2 = reference.getImage();
		if (position2 == 3) {
			reference = new ImageIcon("res/img/battle/numbers/3d.png");
		} else {
			if (players[1].getAbilityTurn3() <= 0 && players[1].getChakra() >= players[1].getChakraMod3()) {
				reference = new ImageIcon("res/img/battle/numbers/3b.png");
			} else {
				reference = new ImageIcon("res/img/battle/numbers/3a.png");
			}
		}
		pl2ImgButton3 = reference.getImage();

		if (position2 == 4) {
			reference = new ImageIcon("res/img/battle/numbers/4d.png");
		} else {
			if (players[1].getAbilityTurn4() <= 0 && players[1].getChakra() >= players[1].getChakraMod4()) {
				reference = new ImageIcon("res/img/battle/numbers/4b.png");
			} else {
				reference = new ImageIcon("res/img/battle/numbers/4a.png");
			}
		}
		pl2ImgButton4 = reference.getImage();

		if (position2 == 5) {
			reference = new ImageIcon("res/img/battle/numbers/5d.png");
		} else {
			reference = new ImageIcon("res/img/battle/numbers/5b.png");
		}
		pl2ImgButton5 = reference.getImage();

	}

	private void setCharacters() {
		for (int i = 0; i < players.length; i++) {
			boolean sair;
			do {
				sair = false;
				switch (choice[i]) {
				case 1:
					LogFrame.addLog(players[i].playerName + ": Kakashi is ready!\n");
					players[i] = new Kakashi();
					break;
				case 2:
					LogFrame.addLog(players[i].playerName + ": Naruto is ready!\n");
					players[i] = new Naruto();
					break;
				case 3:
					LogFrame.addLog(players[i].playerName + ": Sasuke is ready!\n");
					players[i] = new Sasuke();
					break;
				case 4:
					LogFrame.addLog(players[i].playerName + ": Itachi is ready!\n");
					players[i] = new Itachi();
					break;
				default:
					LogFrame.addLog(players[i].playerName + ": preparing the Character\n");
					choice[i] = (int) (1 + (Math.random() * 4));
					sair = true;
					break;
				}
			} while (sair == true);
			players[i].playerName = "Player " + (i + 1);
		}

	}

	public void play(int skill) {

		if (seted) {
			LogFrame.addLog("-----------------------\n");
			LogFrame.addLog(players[attacker].playerName + " turns\n");
			LogFrame.addLog("-----------------------\n");
			players[attacker].startTurn();
			players[defender] = players[attacker].attack(skill, players[defender]);
			if (players[defender].life < 0) {
				players[defender].life = 0;
			}

			// to the player can use the chakar in the next round
			// because this method waits the player choose and
			// the player choose skill if he haves chakra
			players[defender].chakraIncrese();

			if (attacker == 0) {
				attacker = 1;
				defender = 0;
				position2 = 1;
				position1 = 6;
			} else {
				position2 = 6;
				position1 = 1;
				attacker = 0;
				defender = 1;
			}
			LogFrame.addLog("\n****************************************************************\n");
			LogFrame.addLog("Player 1's life: " + players[0].life + " ----- Player 2's life: " + players[1].life + "\n");
			LogFrame.addLog("Player 1's chakra: " + players[0].chakra + " ----- Player 2's chakra: " + players[1].chakra + "\n");
			LogFrame.addLog("\n****************************************************************\n");
		}

	}

	// sort who will begin
	public void sort() {
		if (((int) (1 + (Math.random() * 2))) == 1) {
			attacker = 1;
			position2 = 1;
			players[1].chakra = 2;
		} else {
			defender = 1;
			position1 = 1;
			players[0].chakra = 2;
		}
	}

	public void paint(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;

		// background
		graphics.drawImage(background, 0, 0, getWidth(), getHeight(), null);

		// jogador 1

		// Container Life Bar
		graphics.drawImage(Pl1ContainerLife, (int) (getWidth() * 0.1), (int) (getHeight() * 0.1), (int) (getWidth() * 0.80), (int) (getHeight() * 0.05), null);

		// life Bar
		calculo = (players[0].life * 100) / 150;
		graphics.drawImage(Pl1LifeBar, (int) (getWidth() * 0.17), (int) (getHeight() * 0.1075), (int) ((getWidth() * 0.70) * (calculo / 100)), (int) (getHeight() * 0.035), null);

		// Avatar
		graphics.drawImage(Pl1Avatar, (int) (getWidth() * 0.05), (int) (getHeight() * 0.05), (int) (getWidth() * 0.15), (int) (getHeight() * 0.20), null);

		// Buttons
		graphics.drawImage(pl1ImgButton1, (int) (getWidth() * 0.055), (int) (getHeight() * 0.245), (int) (getWidth() * 0.04), (int) (getHeight() * 0.05), null);
		graphics.drawImage(pl1ImgButton2, (int) (getWidth() * 0.1), (int) (getHeight() * 0.255), (int) (getWidth() * 0.04), (int) (getHeight() * 0.05), null);
		graphics.drawImage(pl1ImgButton3, (int) (getWidth() * 0.15), (int) (getHeight() * 0.245), (int) (getWidth() * 0.04), (int) (getHeight() * 0.05), null);
		graphics.drawImage(pl1ImgButton4, (int) (getWidth() * 0.19), (int) (getHeight() * 0.205), (int) (getWidth() * 0.04), (int) (getHeight() * 0.05), null);
		graphics.drawImage(pl1ImgButton5, (int) (getWidth() * 0.198), (int) (getHeight() * 0.153), (int) (getWidth() * 0.04), (int) (getHeight() * 0.05), null);

		// skill area
		graphics.drawImage(skillArea, (int) (getWidth() * 0.24), (int) (getHeight() * 0.155), (int) (getWidth() * 0.63), (int) (getHeight() * 0.2), null);

		// jogador 2
		// Container Life Bar
		graphics.drawImage(Pl2ContainerLife, (int) (getWidth() * 0.105), (int) (getHeight() * 0.85), (int) (getWidth() * 0.80), (int) (getHeight() * 0.05), null);

		// life Bar
		calculo = (players[1].life * 100) / 150;
		calculoAux = (getWidth() * 0.69) - ((getWidth() * 0.69) * (calculo / 100));
		graphics.drawImage(Pl2LifeBar, (int) ((getWidth() * 0.13) + calculoAux), (int) (getHeight() * 0.858), (int) ((getWidth() * 0.69) * (calculo / 100)),
				(int) (getHeight() * 0.035), null);

		// Avatar
		graphics.drawImage(Pl2Avatar, (int) (getWidth() * 0.8), (int) (getHeight() * 0.74), (int) (getWidth() * 0.15), (int) (getHeight() * 0.20), null);

		// Buttons
		graphics.drawImage(pl2ImgButton1, (int) (getWidth() * 0.9), (int) (getHeight() * 0.695), (int) (getWidth() * 0.04), (int) (getHeight() * 0.05), null);
		graphics.drawImage(pl2ImgButton2, (int) (getWidth() * 0.855), (int) (getHeight() * 0.68), (int) (getWidth() * 0.04), (int) (getHeight() * 0.05), null);
		graphics.drawImage(pl2ImgButton3, (int) (getWidth() * 0.81), (int) (getHeight() * 0.695), (int) (getWidth() * 0.04), (int) (getHeight() * 0.05), null);
		graphics.drawImage(pl2ImgButton4, (int) (getWidth() * 0.77), (int) (getHeight() * 0.74), (int) (getWidth() * 0.04), (int) (getHeight() * 0.05), null);
		graphics.drawImage(pl2ImgButton5, (int) (getWidth() * 0.76), (int) (getHeight() * 0.795), (int) (getWidth() * 0.04), (int) (getHeight() * 0.05), null);

		// skill area
		graphics.drawImage(skillArea, (int) (getWidth() * 0.13), (int) (getHeight() * 0.645), (int) (getWidth() * 0.63), (int) (getHeight() * 0.2), null);

		// show life

		// (int) ((((getWidth() / 100) * (getHeight() / 100))) / 4)
		fontSize = (int) (getWidth() / 47);
		graphics.setFont(new Font("Arial", Font.PLAIN, fontSize));
		graphics.setColor(Color.WHITE);
		graphics.drawString(players[0].life + "", (int) (getWidth() * 0.195), (int) (getHeight() * 0.135));
		graphics.drawString(players[1].life + "", (int) (getWidth() * 0.775), (int) (getHeight() * 0.885));
		graphics.setColor(new Color(254, 203, 0));
		graphics.drawString(players[0].chakra + "", (int) (getWidth() * 0.19), (int) (getHeight() * 0.095));
		graphics.drawString(players[1].chakra + "", (int) (getWidth() * 0.79), (int) (getHeight() * 0.925));

		// Skills
		// (int) ((((getWidth() / 100) * (getHeight() / 100))) / 3.5)
		fontSize = (int) (getWidth() / 49);
		graphics.setFont(new Font("Arial", Font.PLAIN, fontSize));
		graphics.setColor(new Color(254, 203, 0));
		if (position1 != 6) {
			switch (position1) {
			case 1:
				if (!(players[0].getAbilityTurn1() <= 0 && players[0].getChakra() >= players[0].getChakraMod1())) {
					graphics.setColor(Color.WHITE);
				}
				graphics.drawString(players[0].nameAbility1 + ",", (int) (getWidth() * 0.26), (int) (getHeight() * 0.22));
				break;
			case 2:
				if (!(players[0].getAbilityTurn2() <= 0 && players[0].getChakra() >= players[0].getChakraMod2())) {
					graphics.setColor(Color.WHITE);
				}
				graphics.drawString(players[0].nameAbility2 + ",", (int) (getWidth() * 0.26), (int) (getHeight() * 0.22));
				break;
			case 3:
				if (!(players[0].getAbilityTurn3() <= 0 && players[0].getChakra() >= players[0].getChakraMod3())) {
					graphics.setColor(Color.WHITE);
				}
				graphics.drawString(players[0].nameAbility3 + ",", (int) (getWidth() * 0.26), (int) (getHeight() * 0.22));
				break;
			case 4:
				if (!(players[0].getAbilityTurn4() <= 0 && players[0].getChakra() >= players[0].getChakraMod4())) {
					graphics.setColor(Color.WHITE);
				}
				graphics.drawString(players[0].nameAbility4 + ",", (int) (getWidth() * 0.26), (int) (getHeight() * 0.22));
				break;
			case 5:
				graphics.drawString("Pass,", (int) (getWidth() * 0.26), (int) (getHeight() * 0.22));
				break;
			case 6:

				break;
			}
		}

		graphics.setColor(new Color(254, 203, 0));
		if (position2 != 6) {
			switch (position2) {
			case 1:
				if (!(players[1].getAbilityTurn1() <= 0 && players[1].getChakra() >= players[1].getChakraMod1())) {
					graphics.setColor(Color.WHITE);
				}
				graphics.drawString(players[1].nameAbility1 + ",", (int) (getWidth() * 0.15), (int) (getHeight() * 0.71));
				break;
			case 2:
				if (!(players[1].getAbilityTurn2() <= 0 && players[1].getChakra() >= players[1].getChakraMod2())) {
					graphics.setColor(Color.WHITE);
				}
				graphics.drawString(players[1].nameAbility2 + ",", (int) (getWidth() * 0.15), (int) (getHeight() * 0.71));
				break;
			case 3:
				if (!(players[1].getAbilityTurn3() <= 0 && players[1].getChakra() >= players[1].getChakraMod3())) {
					graphics.setColor(Color.WHITE);
				}
				graphics.drawString(players[1].nameAbility3 + ",", (int) (getWidth() * 0.15), (int) (getHeight() * 0.71));
				break;
			case 4:
				if (!(players[1].getAbilityTurn4() <= 0 && players[1].getChakra() >= players[1].getChakraMod4())) {
					graphics.setColor(Color.WHITE);
				}
				graphics.drawString(players[1].nameAbility4 + ",", (int) (getWidth() * 0.15), (int) (getHeight() * 0.71));
				break;
			case 5:
				graphics.drawString("Pass,", (int) (getWidth() * 0.15), (int) (getHeight() * 0.71));
				break;
			case 6:
				break;
			}
		}

		// (int) ((((getWidth() / 100) * (getHeight() / 100))) / 7)
		fontSize = (int) (getWidth() / 70);
		graphics.setFont(new Font("Arial", Font.PLAIN, fontSize));
		graphics.setColor(Color.WHITE);
		if (position1 != 6) {
			switch (position1) {
			case 1:
				graphics.drawString(players[0].descSkill1, (int) (getWidth() * 0.26), (int) (getHeight() * 0.25));
				graphics.drawString(players[0].desc2Skill1, (int) (getWidth() * 0.26), (int) (getHeight() * 0.275));
				break;
			case 2:
				graphics.drawString(players[0].descSkill2, (int) (getWidth() * 0.26), (int) (getHeight() * 0.25));
				graphics.drawString(players[0].desc2Skill2, (int) (getWidth() * 0.26), (int) (getHeight() * 0.275));
				break;
			case 3:
				graphics.drawString(players[0].descSkill3, (int) (getWidth() * 0.26), (int) (getHeight() * 0.25));
				graphics.drawString(players[0].desc2Skill3, (int) (getWidth() * 0.26), (int) (getHeight() * 0.275));
				break;
			case 4:
				graphics.drawString(players[0].descSkill4, (int) (getWidth() * 0.26), (int) (getHeight() * 0.25));
				graphics.drawString(players[0].desc2Skill4, (int) (getWidth() * 0.26), (int) (getHeight() * 0.275));
				break;
			case 5:
				graphics.drawString("Pass", (int) (getWidth() * 0.26), (int) (getHeight() * 0.25));
				break;
			case 6:

				break;
			}
		}
		if (position2 != 6) {
			switch (position2) {
			case 1:
				graphics.drawString(players[1].descSkill1, (int) (getWidth() * 0.15), (int) (getHeight() * 0.735));
				graphics.drawString(players[1].desc2Skill1, (int) (getWidth() * 0.15), (int) (getHeight() * 0.76));
				break;
			case 2:
				graphics.drawString(players[1].descSkill2, (int) (getWidth() * 0.15), (int) (getHeight() * 0.735));
				graphics.drawString(players[1].desc2Skill2, (int) (getWidth() * 0.15), (int) (getHeight() * 0.76));
				break;
			case 3:
				graphics.drawString(players[1].descSkill3, (int) (getWidth() * 0.15), (int) (getHeight() * 0.735));
				graphics.drawString(players[1].desc2Skill3, (int) (getWidth() * 0.15), (int) (getHeight() * 0.76));
				break;
			case 4:
				graphics.drawString(players[1].descSkill4, (int) (getWidth() * 0.15), (int) (getHeight() * 0.735));
				graphics.drawString(players[1].desc2Skill4, (int) (getWidth() * 0.15), (int) (getHeight() * 0.76));
				break;
			case 5:
				graphics.drawString("Pass", (int) (getWidth() * 0.15), (int) (getHeight() * 0.735));
				break;
			case 6:

				break;
			}
		}

		graphics.setColor(new Color(254, 203, 0));
		if (position1 != 6) {
			switch (position1) {
			case 1:
				graphics.drawString(players[0].descDelaySkill1, (int) (getWidth() * 0.26), (int) (getHeight() * 0.3));
				break;
			case 2:
				graphics.drawString(players[0].descDelaySkill2, (int) (getWidth() * 0.26), (int) (getHeight() * 0.3));
				break;
			case 3:
				graphics.drawString(players[0].descDelaySkill3, (int) (getWidth() * 0.26), (int) (getHeight() * 0.3));
				break;
			case 4:
				graphics.drawString(players[0].descDelaySkill4, (int) (getWidth() * 0.26), (int) (getHeight() * 0.3));
				break;
			case 5:
				graphics.drawString("Delay: none", (int) (getWidth() * 0.26), (int) (getHeight() * 0.3));
				break;
			case 6:

				break;
			}
		}
		if (position2 != 6) {
			switch (position2) {
			case 1:
				graphics.drawString(players[1].descDelaySkill1, (int) (getWidth() * 0.15), (int) (getHeight() * 0.785));
				break;
			case 2:
				graphics.drawString(players[1].descDelaySkill2, (int) (getWidth() * 0.15), (int) (getHeight() * 0.785));
				break;
			case 3:
				graphics.drawString(players[1].descDelaySkill3, (int) (getWidth() * 0.15), (int) (getHeight() * 0.785));
				break;
			case 4:
				graphics.drawString(players[1].descDelaySkill4, (int) (getWidth() * 0.15), (int) (getHeight() * 0.785));
				break;
			case 5:
				graphics.drawString("Delay: none", (int) (getWidth() * 0.15), (int) (getHeight() * 0.785));
				break;
			case 6:

				break;
			}
		}

		graphics.setColor(Color.BLACK);
		if (position1 != 6) {
			switch (position1) {
			case 1:
				graphics.drawString(players[0].descChakraSkill1, (int) (getWidth() * 0.26), (int) (getHeight() * 0.325));
				break;
			case 2:
				graphics.drawString(players[0].descChakraSkill2, (int) (getWidth() * 0.26), (int) (getHeight() * 0.325));
				break;
			case 3:
				graphics.drawString(players[0].descChakraSkill3, (int) (getWidth() * 0.26), (int) (getHeight() * 0.325));
				break;
			case 4:
				graphics.drawString(players[0].descChakraSkill4, (int) (getWidth() * 0.26), (int) (getHeight() * 0.325));
				break;
			case 5:
				graphics.drawString("Chakra Consumption: none", (int) (getWidth() * 0.26), (int) (getHeight() * 0.325));
				break;
			case 6:

				break;
			}
		}
		if (position2 != 6) {
			switch (position2) {
			case 1:
				graphics.drawString(players[1].descChakraSkill1, (int) (getWidth() * 0.15), (int) (getHeight() * 0.81));
				break;
			case 2:
				graphics.drawString(players[1].descChakraSkill2, (int) (getWidth() * 0.15), (int) (getHeight() * 0.81));
				break;
			case 3:
				graphics.drawString(players[1].descChakraSkill3, (int) (getWidth() * 0.15), (int) (getHeight() * 0.81));
				break;
			case 4:
				graphics.drawString(players[1].descChakraSkill4, (int) (getWidth() * 0.15), (int) (getHeight() * 0.81));
				break;
			case 5:
				graphics.drawString("Chakra Consumption: none", (int) (getWidth() * 0.15), (int) (getHeight() * 0.81));
				break;
			case 6:

				break;
			}
		}

		// fontSize = (int) ((((getWidth() / 100) * (getHeight() / 100))) / 2);
		fontSize = (int) (getWidth() / 32);
		graphics.setFont(new Font("Arial", Font.PLAIN, fontSize));
		graphics.setColor(Color.WHITE);

		// Drawing "End Game"
		//if (players[0].life <= 0 || players[1].life <= 0) {
			//graphics.drawString("End Game", (int) (getWidth() * 0.40), (int) (getHeight() * 0.515));
		//}
		if (players[0].life <= 0 || players[1].life <= 0 ) {
			graphics.drawString("END GAME", (int) (getWidth() * 0.40), (int) (getHeight() * 0.515));
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (frame.isVisible()) {
			if (OptionsMenuFrame.isStatusSound()) {
				if (!isPlaying) {
					audioCombat.playAudio();
					isPlaying = true;

				}
			} else {
				audioCombat.stopAudio();
				isPlaying = false;
			}
		} else {
			audioCombat.stopAudio();
			isPlaying = false;
		}

		if (isVisible()) {
			referenceImages();
			if (!seted) {
				setCharacters();
				sort();
				players[defender].chakra += 2;
				seted = true;
			}

			// Determine end of round
			if (players[0].life <= 0 || players[1].life <= 0) {
				press = true;
				position1 = 6;
				position2 = 6;
			}

			// Wins
			if (wins) {
				wins = false;
				if (players[0].life <= 0 && players[1].life <= 0) {
					LogFrame.addLog("Draw!\n");
					VictoryFrame.player = 3;
					VictoryFrame.type = 5;
				} else if (players[1].life <= 0) {
					LogFrame.addLog("Player 1 wins!\n");
					VictoryFrame.player = 1;
					VictoryFrame.type = choice[0];
				} else if (players[0].life <= 0) {
					LogFrame.addLog("Player 2 wins!\n");
					VictoryFrame.player = 2;
					VictoryFrame.type = choice[1];
				}
				LogFrame.addLog("Game Over!\n");
				VictoryFrame.ref = true;
				VictoryFrame.setVisible();
				frame.setVisible(false);
			}
		}
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
						LogFrame.combat = true;
						LogFrame.setVisible();
					}
					// P2 - Enter (start)
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						if (players[0].life > 0) {
							if (players[1].life > 0) {
								if (attacker == 1) {
									// Each Turn, will verify attributes
									// (Ability, Chakra, Chakra Mod) before use
									switch (position2) {
									// AbilityTurn = DelayControl
									case 1:
										if (players[1].getAbilityTurn1() <= 0 && players[1].getChakra() >= players[1].getChakraMod1()) {
											play(1);
											audioP2Choice();
										}
										break;
									case 2:
										if (players[1].getAbilityTurn2() <= 0 && players[1].getChakra() >= players[1].getChakraMod2()) {
											play(2);
											audioP2Choice();
										}
										break;
									case 3:
										if (players[1].getAbilityTurn3() <= 0 && players[1].getChakra() >= players[1].getChakraMod3()) {
											play(3);
											audioP2Choice();
										}
										break;
									case 4:
										if (players[1].getAbilityTurn4() <= 0 && players[1].getChakra() >= players[1].getChakraMod4()) {
											play(4);
											audioP2Choice();
										}
										break;
									case 5:
										audioPass();
										play(5);
										break;
									}
								}
							}
						} else {
							wins = true;
						}
					}
					// P2 - Move to Right
					if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
						if (attacker == 1) {
							if (players[0].life > 0 && players[0].life > 0) {
								switch (position2) {
								case 1:
									position2 = 1;
									break;
								case 2:
									position2 = 1;
									playSfxSelect();
									break;
								case 3:
									position2 = 2;
									playSfxSelect();
									break;
								case 4:
									position2 = 3;
									playSfxSelect();
									break;
								case 5:
									position2 = 4;
									playSfxSelect();
									break;
								}
							}
						}
					}
					// P2 - Move to Left
					if (e.getKeyCode() == KeyEvent.VK_LEFT) {
						if (attacker == 1) {
							if (players[0].life > 0 && players[0].life > 0) {
								switch (position2) {
								case 1:
									position2 = 2;
									playSfxSelect();
									break;
								case 2:
									position2 = 3;
									playSfxSelect();
									break;
								case 3:
									position2 = 4;
									playSfxSelect();
									break;
								case 4:
									position2 = 5;
									playSfxSelect();
									break;
								case 5:
									position2 = 5;
									break;
								}
							}
						}
					}
					// P1 - Select (start)
					if (e.getKeyCode() == KeyEvent.VK_SPACE) {
						if (players[1].life > 0) {
							if (players[0].life > 0) {
								if (attacker == 0) {

									switch (position1) {
									case 1:
										if (players[0].getAbilityTurn1() <= 0 && players[0].getChakra() >= players[0].getChakraMod1()) {
											play(1);
											audioP1Choice();
										}
										break;
									case 2:
										if (players[0].getAbilityTurn2() <= 0 && players[0].getChakra() >= players[0].getChakraMod2()) {
											play(2);
											audioP1Choice();
										}
										break;
									case 3:
										if (players[0].getAbilityTurn3() <= 0 && players[0].getChakra() >= players[0].getChakraMod3()) {
											play(3);
											audioP1Choice();
										}
										break;
									case 4:
										if (players[0].getAbilityTurn4() <= 0 && players[0].getChakra() >= players[0].getChakraMod4()) {
											play(4);
											audioP1Choice();
										}
										break;
									case 5:
										audioPass();
										play(5);
										break;
									}// end of switch
								} // end of if(attaccker ==0)
							}// end of player0 life >0
						}// end of player1 life >0
						else {
							wins = true;
						}
					}// end of KeyEvent VK_SPACE

					// P1 - Move to Left
					if (e.getKeyCode() == KeyEvent.VK_A) {
						if (attacker == 0) {
							if (players[0].life > 0 && players[0].life > 0) {
								switch (position1) {
								case 1:
									position1 = 1;
									break;
								case 2:
									position1 = 1;
									playSfxSelect();
									break;
								case 3:
									position1 = 2;
									playSfxSelect();
									break;
								case 4:
									position1 = 3;
									playSfxSelect();
									break;
								case 5:
									position1 = 4;
									playSfxSelect();

									break;
								}// end of switch
							}// end of life players > 0
						} // end of if(attaccker ==0)
					}// end of KeyEvent VK_A

					// P1 - Move to Right
					if (e.getKeyCode() == KeyEvent.VK_D) {
						if (attacker == 0) {
							if (players[0].life > 0 && players[0].life > 0) {
								switch (position1) {
								case 1:
									position1 = 2;
									playSfxSelect();
									break;
								case 2:
									position1 = 3;
									playSfxSelect();
									break;
								case 3:
									position1 = 4;
									playSfxSelect();
									break;
								case 4:
									position1 = 5;
									playSfxSelect();
									break;
								case 5:
									position1 = 5;
									break;
								}// end of switch
							}// end of life players > 0
						} // end of if(attaccker ==0)
					}// end of KeyEvent VK_D
				}// end of (!inLog)
			}// end of isVisible()
		}// end of keyPressed

		// KeyReleased (not used)
		public void keyReleased(KeyEvent e) {

		}

		// Call Effects if Enable

		// Select Audio
		public void playSfxSelect() {
			if (OptionsMenuFrame.isStatusSfx()) {
				sfxSelect.playAudio();
			}

		}

		// P1 Choice
		public void audioP1Choice() {
			if (OptionsMenuFrame.isStatusSfx()) {
				p1Choice.playAudio();
			}
		}

		// P2 Choice
		public void audioP2Choice() {
			if (OptionsMenuFrame.isStatusSfx()) {
				p2Choice.playAudio();
			}
		}

		// Pass
		public void audioPass() {
			if (OptionsMenuFrame.isStatusSfx()) {
				pass.playAudio();
			}
		}

	}
}// end of CombatFrame Class
