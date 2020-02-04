import java.util.*;
public class DungeonGame {
	private DungeonMap map;
	private Player player;
	private String choice;
	private String action = "";
	private String monsterType;
	private int damage;
	private Scanner input = new Scanner(System.in);
	private boolean justMoved = false;
	private boolean monsterAlive;
	private boolean hasAttacked = false;
	private int encounter;
	private Monster monster;
	private int winstate = 0;

	public DungeonGame(Player player, DungeonMap map) {
		this.player = player;
		this.map = map;
	}

	public void combat(int encounter, Player player){
		Monster monster = new Monster();
		damage = monster.initStats(encounter);
		monsterAlive = true;
		while (monsterAlive == true) {
			if(!hasAttacked) {
				monster.attack(damage, player);
				hasAttacked = true;
			}
			System.out.println("What will you do?\nAttack\nFlee");
			action = input.nextLine();
			if (action.equals("Attack")){
				monsterAlive = monster.onHit(10);
				hasAttacked = false;
			} else if (action.equals("Flee")){
				System.out.println("You flee.");
				break;
			} else if (!action.equals("Attack") && !action.equals("Flee")){
				System.out.println("Not a valid input. Please enter 'Attack' or 'Flee'. ");
			}
		}
	}

	public void play() {
		//INIT Player and Starting Pos
		//Allow player to move between rooms
		//Store room data in 2D array
		while (true) {
			//Accessor for player health / gold needed for condition to end when player dies / gold
			System.out.print("Where do you wish to move?");
			this.choice = input.nextLine();
			//MOVES RIGHT
			if (choice.equals("D") || choice.equals("d")) {
				if (player.getLocation()[1] + 1 == map.getWidth() - 1) {
					System.out.println("THERE IS A WALL THERE!");
				} else {

					player.playerMove(0, 1);
					justMoved = true;
				}
				map.print(player.getLocation());
			}
			//MOVES LEFT
			else if (choice.equals("A") || choice.equals("a")) {
				if (player.getLocation()[1] - 1 == 0) {
					System.out.println("THERE IS A WALL THERE!");
				} else {

					player.playerMove(0, -1);
					justMoved = true;
				}
				map.print(player.getLocation());

			}
			//MOVES DOWN
			else if (choice.equals("W") || choice.equals("w")) {
				if (player.getLocation()[0] - 1 == 0) {
					System.out.println("THERE IS A WALL THERE!");
				} else {

					player.playerMove(-1, 0);
					justMoved = true;
				}
				map.print(player.getLocation());

			}


			//MOVES UP
			else if (choice.equals("S") || choice.equals("s")) {
				if (player.getLocation()[0] + 1 == map.getHeight() - 1) {
					System.out.println("THERE IS A WALL THERE!");
				} else {

					player.playerMove(1, 0);
					justMoved = true;
				}
				map.print(player.getLocation());
			}
			//CATCH CASE
			else {
				System.out.println("NOT A VALID INPUT. TRY W A S or D");
			}
			if (justMoved) {
				//Room room = new Room();
				Room room = map.getRooms()[player.getLocation()[0]][player.getLocation()[1]];
				if(room.hasVisited() == false) {
					encounter = room.enter(player);
					if (encounter < 5){
						combat(encounter, player);
					}
				}
				else {
					System.out.println("You already moved here");
				}
				justMoved = false;
				
			}

		}
	}

	}
