import java.util.*;
public class DungeonGame {
	//Constants//
	//Win states
	private static final int STATE_LOSE = 2;
	private static final int STATE_WIN = 1;
	private static final int STATE_NEUTRAL = 0;
	private static final int STATE_CLEAR = 3;

	//monster IDs
	private final int ID_MONSTER = 0;
	private final int ID_GOLD = 1;
	private final int ID_POTION = 2;
	//Constants//

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

	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player inputPlayer) {
		player = inputPlayer;
	}
	public void combat(){
		//create monster
		Monster monster = new Monster();
		System.out.println("You encounter a " + monster.getType() + "!");

		//Keep combat going until monster is dead
		while ( monster.checkLife() ) {
			String action;
			player.printHP();
			System.out.print("What will you do?\n1: Attack\n2: Flee\n-");
			action = input.nextLine().toLowerCase();
			
            while (!action.equals("1") && !action.equals("2") && !action.equals("attack") && !action.equals("Attack") && !action.equals("flee") && !action.equals("Flee")){
                System.out.println("Not a valid input. Please enter 'Attack / attack / 1' or 'Flee / flee / 2'. ");
                action = input.nextLine();
            }
			if (action.equals("1") || action.equals("Attack") || action.equals("attack")){
				monster.onHit(player.getDamage());
				//monster can't hit player if it's dead
				if(monster.checkLife())
					monster.attack(player);
				//check for players death
				if(!player.getLifeStatus()){
					winState = STATE_LOSE;
					break;
				}
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
		//Give player gold for beating monster
        if(!monster.checkLife()) {
            int droppedLoot = monster.generateLoot();
            System.out.println(monster.getType() + " has dropped " + droppedLoot + " gold.");
			player.onLoot(droppedLoot);
			//checks to see if the player has enough gold to win
			if(player.checkForWin())
				winState = STATE_WIN;
        }
	}

	public void play() {
		//INIT Player and Starting Pos
		//Allow player to move between rooms
		//Store room data in 2D array
		boolean justMoved = false;
		String choice;

		while (winState == STATE_NEUTRAL) {
			printStatus();
			System.out.println("Where do you wish to move?");
			choice = input.nextLine().toLowerCase();
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
				//little seperator to seperate what just happened with what is now going on
				System.out.println("______________________________");

				checkRoom();
				
				justMoved = false;

			}
			if(player.checkForWin())
				winState = STATE_WIN;
			if(map.checkForAllClear()){
				map.print(player.getLocation());
				winState = STATE_CLEAR;
			}
			
		}
		if (winState == STATE_WIN){
		    System.out.println("You collected enough gold to escape the dungeon. YOU WIN!");
		} else if (winState == STATE_CLEAR){
			System.out.println("You ran out of rooms to clear, YOU LOSE!");
		} else if (winState == STATE_LOSE) {
			System.out.println("You have died, YOU LOSE!");
		}
	}
	/*private int checkForWin() {
		//If the player is not alive, don't even check for win state, go straight to lose
		if(player.getLifeStatus()){
			//if the player has enough gold, return win state
			if(player.getGold() >= WIN_CONDITION)
				return STATE_WIN;
			else
				return STATE_NEUTRAL;
		} else
			return STATE_LOSE;
	}*/
	private void printStatus(){
		map.print(player.getLocation());
		player.print();
	}
	private void checkRoom(){
		Random rng = new Random();
		Room room = map.getRoom(player.getLocation());
		//check to see if room has been visited
		if(room.hasVisited()) {
			System.out.println("You already moved here");
		} else	{
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
