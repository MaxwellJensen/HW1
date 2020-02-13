public class Main {
    public static void main(String[] args) {
		//Default dungeon size variables, if args are not set
    	int rows = 10;
		int columns = 10;
		boolean defaultDimensions = false;

			//We parse the program start args (custom dungeon size)
			try {
				rows = Integer.parseInt(args[0]);
			} catch (NumberFormatException argumentNotInt) {
				System.out.println("Command line argument 1 is wrong format. Defaulting to 10 rows.");
				defaultDimensions = true;
			} catch (ArrayIndexOutOfBoundsException noArguments) {
				System.out.println("No command line arguments entered. Defaulting to 10 by 10 map size.");
				defaultDimensions = true;
			}


			try {
				columns = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				System.out.println("Command line argument 2 is wrong format. Defaulting to 10 columns.");
				defaultDimensions = true;
			} catch (ArrayIndexOutOfBoundsException noArguments){
				defaultDimensions = true;
			}

			if(defaultDimensions || rows < 1 || columns < 1){
				rows = 10;
				columns = 10;
			}

		//Initialize the objects
    	Player player = new Player();
    	DungeonMap myMap = new DungeonMap(rows, columns, player);
		DungeonGame game = new DungeonGame(player, myMap);
		//Start the game
    	game.play();
				}
}