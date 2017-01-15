package myPackage;


public class Boss extends Enemy {
	
	public Object enemyClass = new Mage();
	
	public Boss(String name, int hp, int mp, int damage, State state){
		super(name,hp, mp,damage, state);
	}
		
	public int bossAttacksPlayer(int playerHP){
		return playerHP-(damage*3);
	}
}

