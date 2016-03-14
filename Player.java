package telerik;

import java.util.Comparator;

public class Player {
	String name;
	int movesCount;
	public Player(String name, int movesCount){
		this.name = name;{
			this.movesCount = movesCount;
		}
	}
	//creating a Comparator for Player object
	public static class PlayerCompare implements Comparator<Player>{
		@Override
		//Comparison is about the number of moves before escaping the maze
		public int compare(Player o1, Player o2) {
			if(o1.movesCount>=o2.movesCount){
				return 1;
			}
			else return -1;
			
		}
	}


}
