import java.util.*;
public class Player {
	//constants//
	private final int WIN_CONDITION = 100;
	public int health;
	
	//private variables
    private boolean winState;
    //private int max_hp;
    private int gold;
    private String playerSymbol = "P";
    private int damage;
	private int[] playerPos = {1,1};
	//playerClass seems null because we just check playerSymbol
    //private String playerClass;
	private double lootModifier;
	
    //Setting max hp so elixirs work properly
    private int max_hp;
	private boolean dead = false;
	//private variables

    public Player() {
    	//Move to play() in DungeonGame
		Scanner input = new Scanner(System.in);
		dead = false;
		winState = false;

		System.out.print("You enter the dungeon. What class are you? (Thief or Warrior) ");
		
		//toLowerCase() will convert the string to all lower cases so we don't have to check for that
		String myInput = input.nextLine().toLowerCase();
		
    	while(true) {
        	if(myInput.equals("thief")) {
        		System.out.println("You are a Thief. Welcome to the Dungeon!");
        		this.health = 70;
        		this.playerSymbol = "T";
        		max_hp = this.health;
        		this.damage = 10;
        		this.lootModifier = .20;
        		break;
        		//gets bonus gold (implement by adding loot * lootModifier to loot)
        	}
        	else if(myInput.equals("warrior")) {
        		System.out.println("You are a Warrior. Welcome to the Dungeon!");
        		this.health = 100;
        		this.playerSymbol = "W";
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
	public int attack(){
		return damage;
	}
    public void onHit(int damage) {
    	this.health -= damage;
    	if(this.health <= 0) {
    		System.out.println("You died! Your quest is over :(");
    		dead = true;

    	}
	}
	
    public String getSymbol() {
    	return this.playerSymbol;
    }
    public void onHeal(int health) {
		System.out.println("You found a healing elixir!");

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

    public void onLoot(int incomingGold) {
		//modifies loot depending on class
		incomingGold = (int) (incomingGold + incomingGold * lootModifier);
    	this.gold += incomingGold;
    	if(this.gold >= WIN_CONDITION){
    		winState = true;
    	}
    }
    
    public void playerMove(int y,int x) {
    	this.playerPos[0] += y; 
    	this.playerPos[1] += x;
	}
	public boolean checkForWin() {
		return winState;
	}
	
	public int getGold() {
		return this.gold;
	}
	public int getHealth() {
		return this.health;
	}
	public boolean getLifeStatus(){
		return !dead;
	}
	public int[] getPosition(){
		return playerPos;
	}
	public void printHP() {
		System.out.println("Your HP: " + health);
	}
	public int getDamage(){
		return damage;
	}
	public void print(){
		System.out.println("HP: " + health + "\nGold: " + gold);
	}
}
