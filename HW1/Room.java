import java.util.*;
public class Room {
	//CONSTANTS//
	//we can change the encounter probability with these constants
	//THEY MUST ALWAYS SUM TO 100//
	private final int MONSTER_CHANCE = 70;
	private final int POTION_CHANCE = 15;
	private final int GOLD_CHANCE = 15;

	private final int ID_MONSTER = 0;
	private final int ID_GOLD = 1;
	private final int ID_POTION = 2;
	//CONSTANTS//

	private boolean visited;
	private int roomContents;

	//Each room constructed is immediately given a roomContents integer
	public Room() {
		this.visited = false;
		//contents of room are randomly generated, theses stats are based on the CHANCE_* constants
		Random rng = new Random();
		int chance = rng.nextInt(99) + 1;
		if(chance <= MONSTER_CHANCE){
			roomContents = ID_MONSTER; 
		} else if(chance <= MONSTER_CHANCE + GOLD_CHANCE) {
			roomContents = ID_GOLD;
		} else if(chance <= MONSTER_CHANCE + GOLD_CHANCE +POTION_CHANCE) {
			roomContents = ID_POTION;
		}
	}
	//when the playe enters a room, it is marked as visited, and the contents of the room (monster, gold, potion) is outputted
	public int enter(){
		visited = true;
		return roomContents;
	}
	public boolean hasVisited () {
		return this.visited;
	}
	//I don't think this method is implemented
	public void setVisited(boolean input) {
		visited = input;
	}
}