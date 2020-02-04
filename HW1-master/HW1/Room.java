import java.util.*;
public class Room {
	private boolean visited;
	private Random ranGen = new Random();
	// assigned Random instance -C
	private int encounterType;
	private int numCoins;
	private int healthRestored;
	private int monsterIndex;
	private String [] monsterName = {"Goblin", "Zombie", "Orc", "Deneke"};

	public Room() {
		this.visited = false;
	}
		//what is this doing?

		public int enter(Player player){
			if (this.visited == true) {
				System.out.println("You have already visited this room!");
			} else {
				//ROOM CALCULATIONS HERE
				encounterType = ranGen.nextInt(3);
				if (encounterType == 0) {
					numCoins = ranGen.nextInt(25)+1;
					player.onLoot(numCoins);
					System.out.println("You found a bag of " + numCoins + " gold!");
					//GOLD CALC
					//LOOTS 25 GOLD?
				} else if (encounterType == 1) {
					healthRestored  = ranGen.nextInt(45)+1;
					player.onHeal(healthRestored);
					System.out.println("You found a healing elixir! You regain " + healthRestored + " hp.");

				} else {
					monsterIndex = ranGen.nextInt(4);
					System.out.println("You encounter a " + monsterName[monsterIndex] + "!");
					//MONSTER CALC
				}
				this.visited = true;


			}
			if (encounterType != 2){
				return 5;
			}
			else{
				return monsterIndex;
			}
		}
		
		public void setVisited(boolean opt) {
			this.visited = opt;
		}

		public boolean hasVisited () {
			return this.visited;
		}
	}
