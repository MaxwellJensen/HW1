import java.util.*;
public class Room {
	//CONSTANTS//
	private final int MONSTER_CHANCE = 70;
	private final int POTION_CHANCE = 15;
	private final int GOLD_CHANCE = 15;

	private final int ID_MONSTER = 0;
	private final int ID_GOLD = 1;
	private final int ID_POTION = 2;
	//CONSTANTS//

	private boolean visited;
	private int roomContents;

	public Room() {
		this.visited = false;
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
		//what is this doing?
	public int enter(){
		visited = true;
		return roomContents;
	}

		public boolean hasVisited () {
			return this.visited;
		}
		public void setVisited(boolean input) {
			visited = input;
		}
	}
