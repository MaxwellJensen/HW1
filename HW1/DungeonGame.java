import java.util.*;
public class DungeonGame {
	///Constants///
		//Win states
	private static final int STATE_LOSE = 2;
	private static final int STATE_WIN = 1;
	private static final int STATE_NEUTRAL = 0;
	private static final int STATE_CLEAR = 3;

		//monster IDs
	private static final int ID_MONSTER = 0;
	private static final int ID_GOLD = 1;
	private static final int ID_POTION = 2;
	///Constants///

	//Variables//
	private DungeonMap map;
	private Player player;
	private Scanner input;
	private int winState;

	public DungeonGame(Player player, DungeonMap map) {
		input = new Scanner(System.in);
		winState = STATE_NEUTRAL;
		this.player = player;
		this.map = map;
	}

	//Getters and Setters for dungeon's player
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player inputPlayer) {
		player = inputPlayer;
	}

	//this method handles the turn based combat when fighting monster
	public void combat(){
		//create monster & announce their arrival
		Monster monster = new Monster(); //constructor generates a random monster
		System.out.println("You encounter a " + monster.getType() + "!");

		//Keep combat going until monster is dead
		while ( monster.checkLife() ) {
			//action will hold the user input data
			String action;
			player.printHP();
			//prompt and recieve user input
			System.out.print("What will you do?\n1: Attack\n2: Flee\n-");
			action = input.nextLine().toLowerCase();
			
			//User can respond in multiple fashions for the two choices. This while loop prevents user from inputting invalid data
            while (!action.equals("1") && !action.equals("2") && !action.equals("attack") && !action.equals("Attack") && !action.equals("flee") && !action.equals("Flee")){
                System.out.println("Not a valid input. Please enter 'Attack / attack / 1' or 'Flee / flee / 2'. ");
                action = input.nextLine();
			}
			//User Attacks
			if (action.equals("1") || action.equals("Attack") || action.equals("attack")){
				monster.onHit(player.getDamage());
				//monster can't hit player if monster is dead
				if(monster.checkLife())
					monster.attack(player);
				//check if player died, if so game is lost
				if(!player.getLifeStatus()){
					winState = STATE_LOSE;
					break;
				}
			//User runs
			} else if (action.equals("2") || action.equals("Flee") || action.equals("flee")) {
                System.out.println("You flee, but are struck as you run.");
                monster.attack(player);
                if(!player.getLifeStatus()){
					winState = STATE_LOSE;
					break;
				}
                break;
			}
			System.out.println("");
		}
		//Give player gold if monster is dead
        if(!monster.checkLife()) {
			//monsters generate how much loot they drop, it varies per monster (as defined in monster class)
            int droppedLoot = monster.generateLoot();
			System.out.println(monster.getType() + " has dropped " + droppedLoot + " gold.");
			player.onLoot(droppedLoot);
			//checks to see if the player has enough gold to win
			if(player.checkForWin())
				winState = STATE_WIN;
        }
	}
	//Play is the main game loop. While in this loop (not including the combat sub-loop) they will be navigating the map
	public void play() {
		//INIT Player and Starting Pos
		//Allow player to move between rooms
		//Store room data in 2D array
		boolean justMoved = false;
		//choice is where user input data is stored
		String choice;
		
		//as long as the player has not won or lost (STATE_NEUTRAL), the game will continue to loop
		while (winState == STATE_NEUTRAL) {
			printStatus();
			//Request user Input
			System.out.println("Where do you wish to move?");
			choice = input.nextLine().toLowerCase();

			//--////Check if user has input valid movement////--//

			//MOVES RIGHT
			if (choice.equals("d")) {
				if (player.getLocation()[1] + 1 == map.getWidth() - 1) {
					System.out.println("THERE IS A WALL THERE!");
				} else {

					player.playerMove(0, 1);
					justMoved = true;
				}
			}
			//MOVES LEFT
			else if (choice.equals("a")) {
				if (player.getLocation()[1] - 1 == 0) {
					System.out.println("THERE IS A WALL THERE!");
				} else {

					player.playerMove(0, -1);
					justMoved = true;
				}


			}
			//MOVES DOWN
			else if (choice.equals("w")) {
				if (player.getLocation()[0] - 1 == 0) {
					System.out.println("THERE IS A WALL THERE!");
				} else {

					player.playerMove(-1, 0);
					justMoved = true;
				}


			}


			//MOVES UP
			else if (choice.equals("s")) {
				if (player.getLocation()[0] + 1 == map.getHeight() - 1) {
					System.out.println("THERE IS A WALL THERE!");
				} else {

					player.playerMove(1, 0);
					justMoved = true;
				}

			}
			//CATCH CASE
			else {
				System.out.println("NOT A VALID INPUT. TRY W A S or D");
			}
			
			//the player has moved, we must now determine what to do based on what the contents in the room are
			if (justMoved) {
				//little seperator to indicate change of room
				System.out.println("______________________________");
				//Checkroom handles what happens in the room
				//this includes picking up gold, picking up a health potion, and initiating combat
				checkRoom();
				
				justMoved = false;

			}
			//before we loop through the main loop again, we check for game-ending states
			if(player.checkForWin())
				winState = STATE_WIN;
			//check to see if player has cleared all the rooms without winning
			if(map.checkForAllClear()){
				map.print(player.getLocation());
				winState = STATE_CLEAR;
			}
			//Loop ends here
		}
		//If the player has won
		if (winState == STATE_WIN){
			System.out.println("You collected enough gold to escape the dungeon. YOU WIN!");
		//STATE_CLEAR happens when the player has cleared all the rooms in the dungeon without winning. It is technically a loss.
		} else if (winState == STATE_CLEAR){
			System.out.println("You ran out of rooms to clear, YOU LOSE!");
		} else if (winState == STATE_LOSE) {
			System.out.println("You have died, YOU LOSE!");
		}
		//END OF PLAY METHOD
	}

	//Prints the map and the player's status
	private void printStatus(){
		map.print(player.getLocation());
		player.print();
	}
	//This method determines what happens in the room, based on whatever objects/monster is in the room
	private void checkRoom(){
		Random rng = new Random();
		Room room = map.getRoom(player.getLocation());
		//check to see if room has been visited
		if(room.hasVisited()) {
			System.out.println("You already moved here");
		} else	{
			//when the player enters a room, the .enter() method spits out an integer based on what object is in the room
				//(These are represented with ID_* constants)
			int contents = room.enter();
			if (contents == ID_MONSTER) {
				combat();
			} else if(contents == ID_GOLD){
				int generatedGold = rng.nextInt(20) + 5;
				System.out.println("You find "+ generatedGold +" gold in this room!");
				player.onLoot(generatedGold);
			} else if(contents == ID_POTION){
				//player gains a minimum of 25 health, and a max of 75 health
				int healthGained = rng.nextInt(50)+25;
				player.onHeal(healthGained);
			}
		}
	}
}
