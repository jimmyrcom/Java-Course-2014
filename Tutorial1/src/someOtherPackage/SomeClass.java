package someOtherPackage;

import myPackage.Enemy;
public class SomeClass {
	public static void main(String[] args) {
		Enemy myEnemy = new Enemy("Zombie",100, 100, 20);

		//NOT EQUIVALENT
		myEnemy.playerAttacksEnemy(10);
		
		System.out.println(myEnemy.hp);
	}
}
