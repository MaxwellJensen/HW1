import java.util.*;
public class Room {
    private boolean visited;
    private Random ranGen;
    private int encounterType;
    
    public Room() {
    	this.visited = false;
    }
    
    public void enter(Player player) {
    	if(this.visited == true) {
    		System.out.println("You have already visited this room!");
    	}
    	else {
    		//ROOM CALCULATIONS HERE
    		encounterType = ranGen.nextInt(3);
    		if(encounterType == 0){
    			System.out.println("You found a bag of gold!");
    			//GOLD CALC
    			//LOOTS 25 GOLD?
    			player.onLoot(ranGen.nextInt(26));
    		}
    		else if(encounterType == 1) {
    			System.out.println("You found a healing elixir!");
    			//ELIXIR CALC
    			player.onHeal(ranGen.nextInt(46));
    		}
    		else {
    			System.out.println("You found a monster!");
    			//MONSTER CALC
    		}
    		this.visited = true;
    		
    	}
    }

    public boolean hasVisited() {
        return this.visited;
    }
}
