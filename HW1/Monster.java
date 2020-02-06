import java.util.*;
public class Monster {
    //Constants//
    private final String TYPE_GOBLIN = "Goblin";
    private final int HP_GOBLIN = 6;
    private final int DMG_GOBLIN = 10;
    private final int LOOT_MIN_GOBLIN = 1;
    private final int LOOT_MAX_GOBLIN = 10;

    private final String TYPE_ZOMBIE = "Zombie";
    private final int HP_ZOMBIE = 12;
    private final int DMG_ZOMBIE = 15;
    private final int LOOT_MIN_ZOMBIE = 7;
    private final int LOOT_MAX_ZOMBIE = 12;

    private final String TYPE_ORC = "Orc";
    private final int HP_ORC = 18;
    private final int DMG_ORC = 20;   
    private final int LOOT_MIN_ORC = 9;
    private final int LOOT_MAX_ORC = 14;

    private final String TYPE_DENEKE = "Deneke"; 
    private final int HP_DENEKE = 55;
    private final int DMG_DENEKE = 5;
    private final int LOOT_MIN_DENEKE = 11;
    private final int LOOT_MAX_DENEKE = 16;
    //Constants//

    //Variables//
    private int health;
    private int damage;
    private String monsterType;
    private Random rng = new Random();
    private boolean monsterAlive;
    //Variables//

    public Monster(){
        monsterAlive = true;
        initStats(rng.nextInt(4));
    }

    public int generateLoot() {
        int returnLoot = 0;

        //Generates loot based on monster type
        //bound is MAX + MIN because in order to have a minimum bound we have to have a max bound += min bound, then subtract the min after the number is generated
        if(monsterType == TYPE_GOBLIN) {
            returnLoot = rng.nextInt(LOOT_MAX_GOBLIN - LOOT_MIN_GOBLIN) + LOOT_MIN_GOBLIN;
        } else if(monsterType == TYPE_ZOMBIE) {
            returnLoot = rng.nextInt(LOOT_MAX_ZOMBIE - LOOT_MIN_ZOMBIE) + LOOT_MIN_ZOMBIE;
        } else if(monsterType == TYPE_ORC) {
            returnLoot = rng.nextInt(LOOT_MAX_ORC - LOOT_MIN_ORC) + LOOT_MIN_ORC;
        } else if(monsterType == TYPE_DENEKE) {
            returnLoot = rng.nextInt(LOOT_MAX_DENEKE - LOOT_MIN_DENEKE) + LOOT_MIN_DENEKE;
        } else {
            System.out.print("ERROR GENERATING MONSTER LOOT");
        }
        
        return returnLoot;
    }
    public String getType() {
        return this.monsterType;
    }
    public boolean checkLife() {
        return monsterAlive;
    }
    public void kill() {
        monsterAlive = false;
    }
    public void attack(Player target) {
    	int dmgTurn = damage; //Should damage calculation be inclusive or exclusive?
    	System.out.println("The "+this.monsterType+" hits you for "+dmgTurn+" damage!");
    	target.onHit(dmgTurn);
    }

    public void onHit(int damage) {
    	System.out.println("You smack the "+ this.monsterType + " for "+damage+" damage!");
    	this.health -= damage;
    	if(this.health <= 0) {
    		System.out.println("The "+this.monsterType+" dies!");
    		monsterAlive = false;
    	}
    }

    public int initStats(int encounter){
        if (encounter == 0){
            this.health = HP_GOBLIN;
            this.damage = DMG_GOBLIN;
            this.monsterType = TYPE_GOBLIN;
        } else if (encounter == 1) {
            this.health = HP_ZOMBIE;
            this.damage = DMG_ZOMBIE;
            this.monsterType = TYPE_ZOMBIE;
        } else if (encounter == 2){
            this.health = HP_ORC;
            this.damage = DMG_ORC;
            this.monsterType = TYPE_ORC;
        } else if (encounter == 3){
            this.damage = DMG_DENEKE;
            this.health = HP_DENEKE;
            this.monsterType = TYPE_DENEKE;
        }
        return this.damage;
    }

}
