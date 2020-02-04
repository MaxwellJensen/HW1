
public class DungeonMap {
	//Room[][] is a 2D array. Our Room class is different.
    private Room[][] rooms;
    private Player player;
    public DungeonMap (int rows, int columns){
    	
    	rooms = new Room[rows+2][columns+2];
    	//GENERATING ROOMS
    	for(int i = 0;i<rooms.length;i++) {
    		for(int j = 0;j<rooms[i].length;j++) {
    			//Every spot in our generated dungeon is assigned to the Room class.
    			rooms[i][j] = new Room();
    			//Testing
    			//System.out.print(rooms[i][j].hasVisited());
    			
    			
    		}
    	}
    }
    

    public int getHeight() {
    	return rooms.length;
    }
    
    public int getWidth() {
    	return rooms[0].length;
    }

    public void print(int[] playerLoc) {
    	for(int i = 0;i<rooms.length;i++) {
    		for(int j = 0;j<rooms[i].length;j++) {
    			if(i == 0 || i == rooms.length - 1 || j == 0 || j == rooms[0].length - 1) {
    				System.out.print("+");
    			}
    			else if(i == playerLoc[0] && j == playerLoc[1]) {
    				System.out.print("P");
    				//CHANGE TO T OR W
    			}
    			else {
    				if(!rooms[i][j].hasVisited()) {
    					System.out.print("-");
    				}
    				else {
    					System.out.print("*");
    				}
    			}
    		}
    		System.out.println();
    	}
    }
}
