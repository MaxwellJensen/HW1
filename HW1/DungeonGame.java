import java.util.*;
public class DungeonGame {
    private DungeonMap map;
    private Player player;
    private String choice;
	private Scanner input = new Scanner(System.in);
	//win condition, while false game continues to run
	private boolean win;
    public DungeonGame(Player player, DungeonMap map) {
    	this.player = player;
		this.map = map;
		//while win condition is false, game continues to run
		this.win = false;
    }
    public void play() {
    	//INIT Player and Starting Pos
    	//Allow player to move between rooms
    	//Store room data in 2D array
    	while(!this.win) {
    		//Accessor for player health / gold needed for condition to end when player dies / gold
    		System.out.print("Where do you wish to move?");
    		this.choice = input.nextLine();
    		//MOVES RIGHT
    		if(choice.equals("D")) {
    			if(player.getLocation()[1] + 1 == map.getWidth() - 1) {
    				System.out.println("THERE IS A WALL THERE!");
    			}
    			else {
    
    				player.playerMove(0,1);
    			}
    			map.print(player.getLocation());
    		}
    		//MOVES LEFT
    		else if(choice.equals("A")) {
    			if(player.getLocation()[1] - 1 == 0) {
    				System.out.println("THERE IS A WALL THERE!");
    			}
    			else {
    
    				player.playerMove(0,-1);
    			}
    			map.print(player.getLocation());
    		}
    		//MOVES DOWN
    		else if(choice.equals("W")) {
    			if(player.getLocation()[0] - 1 == 0) {
    				System.out.println("THERE IS A WALL THERE!");
    			}
    			else {
    
    				player.playerMove(-1,0);
    			}
    			map.print(player.getLocation());
    		}
    		//MOVES UP
    		else if(choice.equals("S")) {
    			if(player.getLocation()[0] + 1 == map.getHeight() - 1) {
    				System.out.println("THERE IS A WALL THERE!");
    			}
    			else {
    
    				player.playerMove(1,0);
    			}
    			map.print(player.getLocation());
    		}
    		//CATCH CASE
    		else {
    			System.out.println("NOT A VALID INPUT. TRY W A S or D");
    		}
    	}
    	
    }
}
