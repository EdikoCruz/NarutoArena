package system;

import frames.LogFrame;

public class Kakashi extends Character {
	// x
	public Kakashi() {

		descSkill1 = "Kakashi copies the attack and directly reflects the opponent.";
		desc2Skill1 = "The opponent has life reduces the value of attack granted.";
		descSkill2 = "Kakashi calls reinforcements and attacks with a group of blows";
		desc2Skill2 = "that reduces 35 points of your opponent's life";
		descSkill3 = "Attacks the opponent with a powerful blow eyepiece that can kill the opponent.";
		desc2Skill3 = "This attack has a 10% chance of success.";

		descDelaySkill1 = "Delay: 1 turn";
		descChakraSkill1 = "Chakra Consumption: 4 points";

		descDelaySkill2 = "Delay: 2 turns";
		descChakraSkill2 = "Chakra Consumption: 1 point";

		descDelaySkill3 = "Delay: 2 turns";
		descChakraSkill3 = "Chakra Consumption: 2 points";

		descSkill4 = "Kakashi is 100% invulnerable to any attack for 1 turn.";
		descDelaySkill4 = "Delay: 4 turnos";
		descChakraSkill4 = "Chakra Consumption: 2 points";

		nameAbility1 = "Jutsu Imitation";
		nameAbility2 = "Team Tactics";
		nameAbility3 = "Kakashi Mangekyou";
		nameAbility4 = "Bunshin Stand-in";
		chakraMod1 = 4;
		chakraMod2 = 1;
	}

	@Override
	public Character checkPersistence(Character oponent) {
		return oponent;
	}

	@Override
	// 1. Jutsu Imitation
	public Character ability1(Character oponent) {
		LogFrame.addLog(playerName + " use Jutsu Imitation\n");
		mirrorAttack = true;
		LogFrame.addLog(playerName + " increase your defense in 100%\n");
		defense = 1;
		turnDefense = 1;
		LogFrame.addLog(playerName + " lost " + chakraMod1 + " chakra's point(s)" + "\n");
		chakra -= chakraMod1;
		turnAbility1 = 1;
		return oponent;
	}

	@Override
	// 2. Team Tactics
	public Character ability2(Character oponent) {
		if (oponent.mirrorAttack == true) {
			LogFrame.addLog(oponent.playerName + "  using Jutsu Imitation\n");
			if (turnDefense > 0) {
				LogFrame.addLog(playerName + " has some defense:\n");
				LogFrame.addLog(playerName + " lost " + (int) (35 - calculateDefense(35, oponent)) + " life's point(s)" + "\n");
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
		LogFrame.addLog(playerName + " lost " + chakraMod2 + " chakra's point(s)" + "\n");
		chakra -= chakraMod2;
		turnAbility2 = 2;
		return oponent;
	}

	@Override
	// 3. Kakashi Mangekyou
	public Character ability3(Character oponent) {
		if (oponent.mirrorAttack == true) {
			LogFrame.addLog(oponent.playerName + "  using Jutsu Imitation\n");
			if (((int) (1 + (Math.random() * 10))) == 1) {
				this.life = 0;
			}
		}

		if (oponent.turnDefense > 0) {
			if (oponent.defense == 1) {
				LogFrame.addLog(oponent.playerName + " is invulnerable\n");
				return oponent;
			}
		}

		if (((int) (1 + (Math.random() * 10))) == 1) {
			LogFrame.addLog(oponent.playerName + " lost all life\n");
			oponent.life = 0;
		}

		LogFrame.addLog(playerName + " lost " + chakraMod3 + " chakra's point(s)" + "\n");
		chakra -= chakraMod3;
		turnAbility3 = 2;
		return oponent;
	}// end of ability3
}
