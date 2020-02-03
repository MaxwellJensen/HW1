import java.util.*;
public class Monster {
    private int health;
    private int damage;
    private String monsterType;
    
    Random rng = new Random();
    public void attack(Player target) {
    	int dmgTurn = rng.nextInt(this.damage); //Should damage calculation be inclusive or exclusive?
    	System.out.println("The "+this.monsterType+" hits you for "+dmgTurn+" damage!");
    	target.onHit(dmgTurn);
    }

    public void onHit(int damage) {
    	System.out.println("You smack the "+ this.monsterType + " for "+damage+" damage!");
    	this.health -= damage;
    	if(this.health <= 0) {
    		System.out.println("The "+this.monsterType+" dies!");
    		//WRITE CODE FOR LOOT DROP HERE?
    	}
    }
}
