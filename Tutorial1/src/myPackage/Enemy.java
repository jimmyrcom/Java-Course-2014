package myPackage;

public class Enemy extends Cow {
	
	public void fart()
	{
		System.out.println("poot");
	};
	
	protected int hp;
	protected int mp;
	public enum State {DEAD, POISONED, UNDEAD, NORMAL};
	public Boolean alive = true;
	public State state = State.NORMAL;
	public int damage;
	public final String name;
	
	public static Boolean scared = false;
	
	public int getHP(){
		return hp;
	}

	public int enemyAttacksPlayer(int playersHP){
		return playersHP-damage;
	}
	
	public int playerAttacksEnemy(int damage)
	{
		hp-=damage;
		if (hp<1)
			state = State.DEAD;
		return hp;
	}

	public Enemy(String name, int hp, int mp, int damage){
		this.name=name;
		this.hp=hp;
		this.mp = mp;
		this.damage=damage;
	}
	public Enemy(String name, int hp, int mp, int damage, State state){
		this(name,hp, mp,damage);
		this.state =state;
	}
	
}
