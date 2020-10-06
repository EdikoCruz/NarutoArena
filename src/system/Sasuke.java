package system;

import frames.LogFrame;

public class Sasuke extends Character {
	// x

	int delayPersistenceAbility2 = 2;
	int turnPersistenceAbility2 = 0;

	public Sasuke() {

		descSkill1 = "The attack reduces 35 points of the opponent's life. ";
		desc2Skill1 = "Sasuke gets a defense of 50% reduction in attack next turn.";
		descSkill3 = "Enables the cursed seal and removes 15 points of life from opponent. ";
		desc2Skill3 = "Sasuke wins invulnerability of 100% in the next attack.";

		descDelaySkill1 = "Delay: 1 turn";
		descChakraSkill1 = "Chakra Consumption: 3 points";

		descSkill2 = "The fireball reduces 20 points from the life of the opponent for 2 turns.";
		descDelaySkill2 = "Delay: 1 turn";
		descChakraSkill2 = "Chakra Consumption: 2 points";

		descDelaySkill3 = "Delay: 3 turns";
		descChakraSkill3 = "Chakra Consumption: 1 points";

		descSkill4 = "Sasuke gets 100% invulnerable to any attack for 1 turn.";
		descDelaySkill4 = "Delay: 4 turns";
		descChakraSkill4 = "Chakra Consumption: 2 points";

		nameAbility1 = "Corrupt Chidori";
		nameAbility2 = "Katon Goukakyuu no Jutsu";
		nameAbility3 = "Cursed Seal Activation";
		nameAbility4 = "Sharingan Evasion";
		chakraMod1 = 3;
		chakraMod3 = 1;
	}

	@Override
	public Character checkPersistence(Character oponent) {
		if (this.turnPersistenceAbility2 > 0) {
			LogFrame.addLog("Persistente Attack!\n");
			if (oponent.turnDefense > 0) {
				LogFrame.addLog(oponent.playerName + " has some defense:\n");
				LogFrame.addLog(playerName + " lost " + (int) (20 - calculateDefense(20, this)) + " life's point(s)" + "\n");
				oponent.life -= (20 - calculateDefense(20, oponent));
			} else {
				LogFrame.addLog(playerName + " lost " + 20 + " life's point(s)" + "\n");
				oponent.life -= 20;
			}
		}

		turnPersistenceAbility2--;
		return oponent;

	}

	@Override
	// 1. Corrupt Chidori
	public Character ability1(Character oponent) {
		if (oponent.mirrorAttack == true) {
			LogFrame.addLog(oponent.playerName + "  using Jutsu Imitation\n");
			if (turnDefense > 0) {
				LogFrame.addLog(playerName + " has some defense:\n");
				LogFrame.addLog(playerName + " lost " + (int) (35 - calculateDefense(35, this)) + " life's point(s)" + "\n");
				this.life -= (35 - calculateDefense(35, this));
			} else {
				LogFrame.addLog(playerName + " lost " + 35 + " life's point(s)" + "\n");
				this.life -= 35;
			}
		}
		if (oponent.turnDefense > 0) {
			LogFrame.addLog(oponent.playerName + " has some defense:\n");
			LogFrame.addLog(oponent.playerName + " lost " + (int) (35 - calculateDefense(35, oponent)) + " life's point(s)" + "\n");
			oponent.life -= (35 - calculateDefense(35, oponent));
		} else {
			LogFrame.addLog(oponent.playerName + " lost " + 35 + " life's point(s)" + "\n");
			oponent.life -= 35;
		}
		defense = 0.5;
		LogFrame.addLog(playerName + " increase your defense in 50%\n");
		turnDefense = 1;
		LogFrame.addLog(playerName + " lost " + chakraMod1 + " chakra's point(s)" + "\n");
		chakra -= chakraMod1;
		turnAbility1 = 1;
		return oponent;
	}

	@Override
	// 2. Katon Goukakyuu no Jutsu
	public Character ability2(Character oponent) {
		if (oponent.mirrorAttack == true) {
			LogFrame.addLog(oponent.playerName + "  using Jutsu Imitation\n");
			if (turnDefense > 0) {
				LogFrame.addLog(playerName + " has some defense:\n");
				LogFrame.addLog(playerName + " lost " + (int) (20 - calculateDefense(20, this)) + " life's point(s)" + "\n");
				this.life -= (20 - calculateDefense(20, this));
			} else {
				LogFrame.addLog(playerName + " lost " + 20 + " life's point(s)" + "\n");
				this.life -= 20;
			}
		}
		if (oponent.turnDefense > 0) {
			LogFrame.addLog(oponent.playerName + " has some defense:\n");
			LogFrame.addLog(oponent.playerName + " lost " + (int) (20 - calculateDefense(20, oponent)) + " life's point(s)" + "\n");
			oponent.life -= (20 - calculateDefense(20, oponent));
		} else {
			LogFrame.addLog(oponent.playerName + " lost " + 20 + " life's point(s)" + "\n");
			oponent.life -= 20;
		}
		LogFrame.addLog(playerName + " lost " + chakraMod2 + " chakra's point(s)" + "\n");
		chakra -= chakraMod2;
		turnAbility2 = 2;
		turnPersistenceAbility2 = 1;
		return oponent;
	}

	@Override
	// 3. Cursed Seal Activation
	public Character ability3(Character oponent) {
		if (life + 15 > 150) {
			LogFrame.addLog(playerName + " don't wins your life's points because the life bar is full\n");
			life = 150;
		} else {
			LogFrame.addLog(playerName + " wins 15 life's points\n");
			life += 15;
		}
		defense = 1;
		LogFrame.addLog(playerName + " increase your defense in 100%\n");
		turnDefense = 1;
		LogFrame.addLog(playerName + " lost " + chakraMod3 + " chakra's point(s)" + "\n");
		chakra -= chakraMod3;
		turnAbility3 = 3;
		return oponent;
	}

}
