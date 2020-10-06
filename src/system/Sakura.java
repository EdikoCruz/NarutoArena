package system;

public class Sakura extends Character {
	// x
	int delayPersistenceAbility1 = 4;
	int turnPersistenceAbility1 = 0;
	boolean ok=false;

	public Sakura() {
		nameAbility1 = "Inner Sakura";
		nameAbility2 = "KO Punch";
		nameAbility3 = "Cure";
		nameAbility4 = "Sakura Replacement Technique";
	}

	@Override
	public Character ability3(Character oponent) {
		if(life+25>150){
			life=150;
		}else{
			this.life += 25;
		}

		chakra -= chakraMod3;

		turnAbility3 = 0;
		return oponent;
	}

	@Override
	public Character ability1(Character oponent) {
		chakra -= chakraMod1;
		turnAbility1 = 5;
		turnPersistenceAbility1 = 4;
		if(defense<0.25){
		defense = 0.25;
		}
		turnDefense = 4;
		return oponent;
	}

	public Character checkPersistence(Character oponent) {
		if (this.turnPersistenceAbility1 > 0) {
			if(defense < 0.25){
				defense = 0.25;
			}
			ok =true;
		}else{
			ok = false;
		}

		turnPersistenceAbility1--;
		return oponent;
	}

	@Override
	public Character ability2(Character oponent) {
		int damage;
		if(ok){
			damage=40;
		}else{
			damage=20;
		}
		
		if (oponent.mirrorAttack == true) {
			if (turnDefense >= 0) {
				this.life -= (damage - calculateDefense(damage, this));
			} else {
				this.life -= damage;
			}
		}
		if (oponent.turnDefense >= 0) {
			oponent.life -= (damage - calculateDefense(damage, oponent));
		} else {
			oponent.life -= damage;
		}
		
		chakra -= chakraMod2;
		turnAbility2 = 0;
		return oponent;
	}
}
