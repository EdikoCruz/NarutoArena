package system;

import frames.LogFrame;

public abstract class Character {
	// x
	public String playerName;

	public int life = 150;
	public int chakra = 0;

	public String descSkill1;
	public String descSkill2;
	public String descSkill3;
	public String descSkill4;
	
	public String desc2Skill1="";
	public String desc2Skill2="";
	public String desc2Skill3="";
	public String desc2Skill4="";


	public String descChakraSkill1;
	public String descChakraSkill2;
	public String descChakraSkill3;
	public String descChakraSkill4;

	public String descDelaySkill1;
	public String descDelaySkill2;
	public String descDelaySkill3;
	public String descDelaySkill4;

	public int chakraMod1 = 2;
	public int chakraMod2 = 2;
	public int chakraMod3 = 2;
	public int chakraMod4 = 2;

	double defense = 0;
	public int delayDenfese = 1;
	public int turnDefense = -2;

	
	public int turnAbility1 = -2;
	public int turnAbility2 = -2;
	public int turnAbility3 = -2;
	public int turnAbility4 = -2;

	// Ability Names
	public String nameAbility1 = "ability1";
	public String nameAbility2 = "ability2";
	public String nameAbility3 = "ability3";
	public String nameAbility4 = "ability4";

	boolean mirrorAttack = false;

	public Character() {
	}

	public Character attack(int index, Character oponent) {
		oponent = checkPersistence(oponent);
		switch (index) {
		case 1:
			LogFrame.addLog(playerName + " used " + nameAbility1 + "\n");
			return ability1(oponent);
		case 2:
			LogFrame.addLog(playerName + " used "+nameAbility2 + "\n");
			return ability2(oponent);
		case 3:
			LogFrame.addLog(playerName + " used "+nameAbility3 + "\n");
			return ability3(oponent);
		case 4:
			LogFrame.addLog(playerName + " used "+nameAbility4 + "\n");
			return ability4(oponent);
		default:
			LogFrame.addLog(playerName + " pass\n");
			return oponent;
		}
	}

	public abstract Character checkPersistence(Character oponent);

	public abstract Character ability1(Character oponent);

	public abstract Character ability2(Character oponent);

	public abstract Character ability3(Character oponent);

	public Character ability4(Character oponent) {
		LogFrame.addLog(playerName + " is invulnerable\n");
		defense = 1;
		turnDefense = 1;
		LogFrame.addLog(playerName +" lost "+chakraMod4+" chakra's point(s)"+ "\n");
		chakra -= chakraMod4;
		turnAbility4 = 4;
		return oponent;
	}

	public void startTurn() {
		mirrorAttack = false;
		if(turnAbility1==1){
			LogFrame.addLog(nameAbility1 +  " is enabled\n");
		}
		if(turnAbility2==1){
			LogFrame.addLog(nameAbility2 +  " is enabled\n");
		}
		if(turnAbility3==1){
			LogFrame.addLog(nameAbility3 +  " is enabled\n");
		}
		if(turnAbility4==1){
			LogFrame.addLog(nameAbility4 +  " is enabled\n");
		}
		turnAbility1--;
		turnAbility2--;
		turnAbility3--;
		turnAbility4--;
		turnDefense--;
	}
	
	public void chakraIncrese(){
		LogFrame.addLog(playerName + " wins 2 chakra's points\n");
		chakra += 2;
	}

	public double calculateDefense(double attack, Character oponent) {
		if (oponent.defense == 1) {
			LogFrame.addLog(oponent.playerName + " is invulnerable\n");
			return attack;
		}

		if (oponent.defense > 1) {
			if (defense > attack) {
				return attack;
			} else {
				return (int) defense;
			}
		} else {
			return (int) (attack * oponent.defense);
		}
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getChakra() {
		return chakra;
	}

	public void setChakra(int chakra) {
		this.chakra = chakra;
	}

	public int getChakraMod1() {
		return chakraMod1;
	}

	public void setChakraMod1(int chakraMod1) {
		this.chakraMod1 = chakraMod1;
	}

	public int getChakraMod2() {
		return chakraMod2;
	}

	public void setChakraMod2(int chakraMod2) {
		this.chakraMod2 = chakraMod2;
	}

	public int getChakraMod3() {
		return chakraMod3;
	}

	public void setChakraMod3(int chakraMod3) {
		this.chakraMod3 = chakraMod3;
	}

	public int getChakraMod4() {
		return chakraMod4;
	}

	public void setChakraMod4(int chakraMod4) {
		this.chakraMod4 = chakraMod4;
	}

	public double getDefense() {
		return defense;
	}

	public void setDefense(double defense) {
		this.defense = defense;
	}

	public int getDelayDefense() {
		return delayDenfese;
	}

	public void setDelayDefense(int delayDefense) {
		this.delayDenfese = delayDefense;
	}

	public int getTurnDefense() {
		return turnDefense;
	}

	public void setTurnDefense(int turnDefense) {
		this.turnDefense = turnDefense;
	}

	public int getAbilityTurn1() {
		return turnAbility1;
	}

	public void setTurnAbility1(int turnAbility1) {
		this.turnAbility1 = turnAbility1;
	}


	public int getAbilityTurn2() {
		return turnAbility2;
	}

	public void setTurnAbility2(int turnAbility2) {
		this.turnAbility2 = turnAbility2;
	}


	public int getAbilityTurn3() {
		return turnAbility3;
	}

	public void setTurnAbility3(int turnAbility3) {
		this.turnAbility3 = turnAbility3;
	}


	public int getAbilityTurn4() {
		return turnAbility4;
	}

	public void setTurnAbility4(int turnAbility4) {
		this.turnAbility4 = turnAbility4;
	}

	public String getAbilityName1() {
		return nameAbility1;
	}

	public void setNameAbility1(String nameAbility1) {
		this.nameAbility1 = nameAbility1;
	}

	public String getAbilityName2() {
		return nameAbility2;
	}

	public void setNameAbility2(String nameAbility2) {
		this.nameAbility2 = nameAbility2;
	}

	public String getAbilityName3() {
		return nameAbility3;
	}

	public void setNameAbility3(String nameAbility3) {
		this.nameAbility3 = nameAbility3;
	}

	public String getAbilityName4() {
		return nameAbility4;
	}

	public void setNameAbility4(String nameAbility4) {
		this.nameAbility4 = nameAbility4;
	}

	public boolean isMirrorAttack() {
		return mirrorAttack;
	}

	public void setMirrorAttack(boolean mirrorAttack) {
		this.mirrorAttack = mirrorAttack;
	}

}
