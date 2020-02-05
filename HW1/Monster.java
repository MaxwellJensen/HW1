import java.util.*;
public class Monster {
    private int health;
    private int damage;
    private boolean monsterAlive = true;
    private String monsterType;
    private String action;
    private Scanner input = new Scanner(System.in);
    private boolean dead = false;

    Random rng = new Random();
    public boolean attack(int damage, Player target) {
    	int dmgTurn = rng.nextInt(damage); //Should damage calculation be inclusive or exclusive?
    	System.out.println("The "+this.monsterType+" hits you for "+dmgTurn+" damage!");
    	dead = target.onHit(dmgTurn);
    	return dead;
    }

    public boolean onHit(int damage) {
    	System.out.println("You smack the "+ this.monsterType + " for "+damage+" damage!");
    	this.health -= damage;
    	if(this.health <= 0) {
    		System.out.println("The "+this.monsterType+" dies!");
    		monsterAlive = false;
    		//WRITE CODE FOR LOOT DROP HERE?
    	}
        return monsterAlive;
    }

    public int initStats(int encounter){
        if (encounter == 0){
            this.health = 6;
            this.damage = 10;
            this.monsterType = "Goblin";
        } else if (encounter == 1) {
            this.health = 12;
            this.damage = 15;
            this.monsterType = "Zombie";
        } else if (encounter == 2){
            this.health = 18;
            this.damage = 20;
            this.monsterType = "Orc";
        } else if (encounter == 3){
            this.damage = 5;
            this.health = 55;
            this.monsterType = "Deneke";
        }
        return this.damage;
    }

}
