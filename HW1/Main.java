import java.util.*;
public class Main {
    public static void main(String[] args) {

    	int rows = 0;
		int columns = 0;

			try {
				rows = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				System.out.println("Please enter an integer value for command line argument one and two.");
			}


			try {
				columns = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				System.out.println("Please enter an integer value for command line argument one and two.");
			}


    	Player player = new Player();
    	DungeonMap myMap = new DungeonMap(rows, columns, player);
    	DungeonGame game = new DungeonGame(player, myMap);
    	game.play();
				}
}