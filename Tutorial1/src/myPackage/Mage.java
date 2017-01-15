package myPackage;

public class Mage implements job {
	public int jobSpecialAttack(int playerHP){
		return playerHP-=1000;
	}
	public int jobBonus(){
		return 0;
	}
}
