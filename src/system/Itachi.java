package system;

import frames.LogFrame;

public class Itachi extends Character{
	// x
	public Itachi() {
		
		descSkill1 = "Opponent suffers an ocular attack and falls into a trance.";
		desc2Skill1 = "Itachi is 100% invulnerable for 2 turns.";
		descSkill3 = "A black flame consumes all the chakra points of the opponent";
		desc2Skill3 = " and reduces 15 points of own life.";

		
		descDelaySkill1 = "Delay: 3 turns";
		descChakraSkill1 = "Chakra Consumption: 3 points";

		descSkill2 = "Reduces 20 life opponent. Itachi receives a cure of 5% of your current life.";
		descDelaySkill2 = "Delay: none";
		descChakraSkill2 = "Chakra Consumption: 1 point";
		
		descDelaySkill3 = "Delay: 1 turn";
		descChakraSkill3 = "Chakra Consumption: 2 points";
		
		descSkill4 = "Itachi is 100% invulnerable to any attack for 1 turn.";
		descDelaySkill4 = "Delay: 4 turnos";
		descChakraSkill4 = "Chakra Consumption: 2 points";
		
		nameAbility1 = "Mangekyou Sharingan";
		nameAbility2 = "Tsukuyomi";
		nameAbility3 = "Amaterasu";
		nameAbility4 = "Sharingan Insight";
		chakraMod1 = 3;
		chakraMod2 = 1;
	}

	@Override
	public Character checkPersistence(Character oponent) {
		
		return oponent;
	}

	@Override
	public Character ability1(Character oponent) {
		LogFrame.addLog(playerName +" increase your defense in 100%\n");
		defense = 1;
		turnDefense = 2;
		LogFrame.addLog(playerName +" lost "+chakraMod1+" chakra's point(s)"+ "\n");
		chakra -= chakraMod1;
		turnAbility1 = 3;
		return oponent;
	}

	@Override
	public Character ability2(Character oponent) {
		if(oponent.mirrorAttack == true){
			LogFrame.addLog(oponent.playerName+"  using Jutsu Imitation\n");
			if(turnDefense>0){
				LogFrame.addLog(playerName+" has some defense:\n");
				LogFrame.addLog(playerName +" lost "+(int)((20 - calculateDefense(20, oponent)))+" life's point(s)"+ "\n");
				this.life -= (20 - calculateDefense(20, this));
			}else{
				LogFrame.addLog(playerName +" lost "+20+" life's point(s)"+ "\n");
				this.life -= 20;
			}
		}
		if(oponent.turnDefense > 0){
			LogFrame.addLog(oponent.playerName+" has some defense:\n");
			LogFrame.addLog(oponent.playerName +" lost "+(int)((20 - calculateDefense(20, oponent)))+" life's point(s)"+ "\n");
			oponent.life -= (20 - calculateDefense(20, oponent));
		}else{
			LogFrame.addLog(oponent.playerName +" lost "+20+" life's point(s)"+ "\n");
			oponent.life -= 20;
		}
		int lifeAux = (int) (life + (life*0.05));
		if(lifeAux > 150){
			LogFrame.addLog(playerName + " don't wins your life's points because the life bar is full\n");
			life = 150;
		}else{
			LogFrame.addLog(playerName + " wins 5% of your life's points\n");
			life = lifeAux;
		}
		
		LogFrame.addLog(playerName +" lost "+chakraMod2+" chakra's point(s)"+ "\n");
		chakra-=chakraMod2;
		turnAbility2 = 1;
		return oponent;
	}

	@Override
	public Character ability3(Character oponent) {
		if(oponent.mirrorAttack == true){
			LogFrame.addLog(oponent.playerName+"  using Jutsu Imitation\n");
			if(turnDefense>0){
				LogFrame.addLog(playerName+" has some defense:\n");
				LogFrame.addLog(playerName +" lost "+(int)(oponent.chakra - calculateDefense((oponent.chakra), oponent))+" life's point(s)"+ "\n");
				this.chakra -= (1 - calculateDefense(1, this));
			}else{
				LogFrame.addLog(playerName +" lost all chakra's point(s)"+ "\n");
				this.chakra = 0;
			}
		}
		if(oponent.turnDefense > 0){
			LogFrame.addLog(oponent.playerName+" has some defense:\n");
			LogFrame.addLog(oponent.playerName +" lost "+(int)(oponent.chakra - calculateDefense((oponent.chakra), oponent))+" life's point(s)"+ "\n");
			oponent.chakra -= (oponent.chakra - calculateDefense((oponent.chakra), oponent));
		}else{
			LogFrame.addLog(oponent.playerName +" lost all chakra's point(s)"+ "\n");
			oponent.chakra = 0;
		}
		
		if(turnDefense>0){
			LogFrame.addLog(playerName+" has some defense:\n");
			LogFrame.addLog(playerName +" lost "+(int)((15- calculateDefense(15, oponent)))+" life's point(s)"+ "\n");
			this.life -= (15 - calculateDefense(15, this));
		}else{
			LogFrame.addLog(playerName +" lost "+15+" life's point(s)"+ "\n");
			this.life -= 15;
		}
		
		
		LogFrame.addLog(playerName +" lost "+chakraMod3+" chakra's point(s)"+ "\n");
		chakra-=chakraMod3;
		turnAbility3 = 2;
		return oponent;
	}

}
