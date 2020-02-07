
public class DungeonMap {
	//Room[][] is a 2D array. Our Room class is different.
    private Room[][] rooms;
    private Player player;
    public DungeonMap (int rows, int columns, Player player){
    	this.player = player;
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
		rooms[player.getPosition()[0]][player.getPosition()[1]].setVisited(true);
    }
    
    public Room[][] getRooms(){
    	return this.rooms;
	}
	public Room getRoom(int[] coordinates) {
		return this.rooms[coordinates[0]][coordinates[1]];
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
    				System.out.print(player.getSymbol());
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
	public boolean checkForAllClear() {
		//this bool will get flipped off if we find a room that hasn't been visited
		boolean allVisited = true;
		for(int i = 1;i<rooms.length - 1;i++) {
    		for(int j = 1;j<rooms[i].length - 1;j++) {
				//checks if room hasn't been visited
				if(!rooms[i][j].hasVisited())
					allVisited = false;
    		}
		}
		return allVisited;
	}
}
