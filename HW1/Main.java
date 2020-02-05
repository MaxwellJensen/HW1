import java.util.*;
public class Main {
    public static void main(String[] args) {
    	Player player = new Player();
    	DungeonMap myMap = new DungeonMap(5,5,player);
    	myMap.print(player.getLocation());
    	DungeonGame game = new DungeonGame(player, myMap);
    	game.play();
    }
}