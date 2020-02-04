import java.util.*;
public class Player {
    public int health;
    //private int max_hp;
    private int gold;
    private int damage;
    private int[] playerPos = {1,1};
    private String playerClass;
    private double lootModifier;
    //Setting max hp so elixirs work properly
    int max_hp;
    private boolean dead = false;
    public Player() {
    	//Move to play() in DungeonGame
    	Scanner input = new Scanner(System.in);
    	System.out.print("You enter the dungeon. What class are you? (Thief or Warrior) ");
    	String myInput = input.nextLine();
    	while(true) {
        	if(myInput.equals("Thief")) {
        		System.out.println("You are a Thief. Welcome to the Dungeon!");
        		this.health = 70;
        		max_hp = this.health;
        		this.damage = 10;
        		this.lootModifier = .20;
        		break;
        		//gets bonus xp (implement by adding loot * lootModifier to loot)
        	}
        	else if(myInput.equals("Warrior")) {
        		System.out.println("You are a Warrior. Welcome to the Dungeon!");
        		this.health = 100;
        		max_hp = this.health;
        		this.damage = 15;
        		this.lootModifier = 0;
        		break;
        	}
        	else {
        		System.out.println("Sorry, "+myInput+" is not a valid input. Try again.");
            	System.out.print("You enter the dungeon. What class are you? (Thief or Warrior) ");
            	myInput = input.nextLine();
        	}
    	}
    }
    public int[] getLocation() {
    	return this.playerPos;
    }

    public void onHit(int damage) {
    	this.health -= damage;
    	if(this.health <= 0) {
    		System.out.println("You died! Your quest is over :(");

    	}
    }

    public void onHeal(int health) {
    	//Condition to make sure player cannot heal past full
    	if(health + this.health > this.max_hp) {
    		System.out.println("You heal as much as possible to full health.");
    		this.health = this.max_hp;
    	}
    	else {
    		System.out.println("You heal "+health+" hp points.");
    		this.health += health;
    	}
    }

    public void onLoot(int gold) {
    	this.gold += gold;
    	if(this.gold >= 100){
    		//Trigger GAME WIN CONDITION in Controller
    	}
    }
    
    public void playerMove(int y,int x) {
    	this.playerPos[0] += y; 
    	this.playerPos[1] += x;
    }
}
