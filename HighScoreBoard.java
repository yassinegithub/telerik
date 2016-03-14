package telerik;

import java.util.Collections;
import java.util.LinkedList;


public class HighScoreBoard {
	LinkedList list;
	public final int boardSize = 5;
	public HighScoreBoard(){
		list = new LinkedList();
	}
	
	@SuppressWarnings("unchecked")
	public boolean addPlayerToChart(Player player){
		//if the High Score is empty
		if(list.size()==0){
			list.addFirst(player);
			return true;
		}	
		//if the High Score is not full yet
		if((list.size()>0)&&(list.size()<boardSize)){
			list.addLast(player);
			Collections.sort(list,new Player.PlayerCompare());	
			return true;
			
		}
		//if the High Score is already full
		if((list.size()==boardSize)) {
			Player pl = (Player) list.get(list.size()-1);
			//compare the number of moves of the player with the last player on the High Score Board
			if((player.movesCount<pl.movesCount)){
				list.remove(list.size() - 1);
				list.addLast(player);
				Collections.sort(list,new Player.PlayerCompare());	
				return true;
				
			}
		}
		return false;
	}
	//printig the High Score Board
	void printBoard(LinkedList list){
		System.out.println("Score :");
		for(int i=0;i<list.size();i++){
			Player p = (Player) list.get(i);
			System.out.print((i+1)+". Name - "+p.name+" ");
			System.out.print("Escaped in "+p.movesCount+" moves");
			System.out.println();
		}
	}

		
}

