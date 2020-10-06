package system;

import frames.LogFrame;

public class Naruto extends Character {
	// x
	int delayPersistenceAbility1 = 2;
	int turnPersistenceAbility1 = 0;

	public Naruto() {

		descSkill2 = "Strike the opponent by reducing 15 points of his life.";
		desc2Skill2 = "For the next turn Naruto gets a 15% reduction of the opponent's attack.";

		descSkill3 = "Attacks with his most powerful blow to the opponent inflicting 35 points of his life.";
		desc2Skill3 = "Naruto impacted 10 points in his life.";

		nameAbility1 = "Kyuubi Chakra";
		descSkill1 = "Launches a powerful mass of chakra in the opponent by reducing 10% of his life for two turns.";
		descDelaySkill1 = "Delay: 1 turn";
		descChakraSkill1 = "Chakra Consumption: 2 points";

		nameAbility2 = "Kyuubi Rage";
		descDelaySkill2 = "Delay: none";
		descChakraSkill2 = "Chakra Consumption: 1 point";

		nameAbility3 = "Kyuubi Rasengan";
		descDelaySkill3 = "Delay: 1 turn";
		descChakraSkill3 = "Chakra Consumption: 2 points";

		nameAbility4 = "Chakra Howl";
		descSkill4 = "Naruto is 100% invulnerable to any attack for 1 turn.";
		descDelaySkill4 = "Delay: 4 turnos";
		descChakraSkill4 = "Chakra Consumption: 2 points";

		chakraMod2 = 1;
	}

	@Override
	// 3. Kyuubi Rasengan
	public Character ability3(Character oponent) {

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
		}// end of ability3

		this.life -= 10;
		LogFrame.addLog(playerName + " lost " + 10 + " life's point(s)" + "\n");

		LogFrame.addLog(playerName + " lost " + chakraMod3 + " chakra's point(s)" + "\n");
		chakra -= chakraMod3;

		turnAbility3 = 1;
		return oponent;
	}

	@Override
	// 1. Corrupt Chidori
	public Character ability1(Character oponent) {
		if (oponent.mirrorAttack == true) {
			LogFrame.addLog(oponent.playerName + "  using Jutsu Imitation\n");
			if (turnDefense > 0) {
				LogFrame.addLog(playerName + " has some defense:\n");
				LogFrame.addLog(playerName + " lost " + (int) (this.life * 0.1 - calculateDefense(this.life * 0.1, this)) + " life's point(s)" + "\n");
				this.life -= (this.life * 0.1 - calculateDefense(this.life * 0.1, this));
			} else {
				LogFrame.addLog(playerName + " lost " + (int) (this.life * 0.1) + " life's point(s)" + "\n");
				this.life -= (this.life * 0.1);
			}
		}
		if (oponent.turnDefense > 0) {
			LogFrame.addLog(oponent.playerName + " has some defense:\n");
			LogFrame.addLog(oponent.playerName + " lost " + (int) (oponent.life * 0.1) + " life's point(s)" + "\n");
			oponent.life -= ((oponent.life * 0.1) - calculateDefense(oponent.life * 0.1, oponent));
		} else {
			LogFrame.addLog(oponent.playerName + " lost " + (int) (oponent.life * 0.1) + " life's point(s)" + "\n");
			oponent.life -= (oponent.life * 0.1);
		}
		LogFrame.addLog(playerName + " lost " + chakraMod1 + " chakra's point(s)" + "\n");
		chakra -= chakraMod1;
		turnAbility1 = 1;
		turnPersistenceAbility1 = 1;
		return oponent;
	}// end of ability1

	public Character checkPersistence(Character oponent) {
		if (this.turnPersistenceAbility1 > 0) {
			LogFrame.addLog("Persistente Attack!\n");
			if (oponent.turnDefense > 0) {
				LogFrame.addLog(oponent.playerName + " has some defense:\n");
				LogFrame.addLog(playerName + " lost " + (int) (this.life * 0.1 - calculateDefense(this.life * 0.1, this)) + " life's point(s)" + "\n");
				oponent.life -= ((oponent.life * 0.1) - calculateDefense(oponent.life * 0.1, oponent));
			} else {
				LogFrame.addLog(playerName + " lost " + (int) (this.life * 0.1) + " life's point(s)" + "\n");
				oponent.life -= (oponent.life * 0.1);
			}
		}

		turnPersistenceAbility1--;
		return oponent;
	}

	@Override
	// 2. Kyuubi Rage
	public Character ability2(Character oponent) {
		if (oponent.mirrorAttack == true) {
			LogFrame.addLog(oponent.playerName + "  using Jutsu Imitation\n");
			if (turnDefense > 0) {
				LogFrame.addLog(playerName + " has some defense:\n");
				LogFrame.addLog(playerName + " lost " + (int) (15 - calculateDefense(15, this)) + " life's point(s)" + "\n");
				this.life -= (15 - calculateDefense(15, this));
			} else {
				LogFrame.addLog(playerName + " lost " + 15 + " life's point(s)" + "\n");
				this.life -= 15;
			}
		}
		if (oponent.turnDefense > 0) {
			LogFrame.addLog(oponent.playerName + " has some defense:\n");
			LogFrame.addLog(oponent.playerName + " lost " + (int) (15 - calculateDefense(15, oponent)) + " life's point(s)" + "\n");
			oponent.life -= (15 - calculateDefense(15, oponent));
		} else {
			LogFrame.addLog(oponent.playerName + " lost " + 15 + " life's point(s)" + "\n");
			oponent.life -= 15;
		}
		defense = 0.15;
		LogFrame.addLog(playerName + " increase your defense in 15%\n");
		turnDefense = 1;
		LogFrame.addLog(playerName + " lost " + chakraMod2 + " chakra's point(s)" + "\n");
		chakra -= chakraMod2;
		turnAbility2 = 0;
		return oponent;
	}// end of ability2
}
