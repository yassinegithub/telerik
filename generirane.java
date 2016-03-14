package telerik;

import java.util.Random;
import java.util.Scanner;
import java.lang.*;


public class generirane {
	public boolean isVisited[][] = new boolean[7][7];
	public char maze[][] = new char[7][7];
	public int playersCurrentRow;
	public int playersCurrentColumn;
	public String command;
	public boolean isExit = false;
	public int playersMovesCount = 0;
	HighScoreBoard board;
	

	
	
	
	
	
	
	// Generating a maze
	void initializeMaze(){
		Random randomgenerator = new Random();	
		// Generates a new maze until at least one solution is found
		do{
		for(int row=0;row<7;row++){
			for(int column=0;column<7;column++){
				isVisited[row][column]=false;
				if(randomgenerator.nextInt(2)==1){
					maze[row][column] = 'X';
				}
				else {
					maze[row][column] = '-';
				}
			}
		}
		}
		while(isSolvable(3, 3)==false); // while the Maze generated is not a Solvable one
		playersCurrentRow = 3;
		playersCurrentColumn = 3;
		
		
		maze[playersCurrentRow][playersCurrentColumn] = '*';
		printMaze();
	}	
	public void initializeScoreBoard(){
		board = new HighScoreBoard();
	}	
	//creating a method to check the solvability of mazes using recursivity
	public boolean isSolvable(int row, int col){
		if((row==6)||(col==6)||(row==0)||(col==0)){
			isExit = true;
			return isExit;
		}
		if((maze[row-1][col]=='-')){
			if((isVisited[row-1][col]==false)) {
				isVisited[row][col] = true;
				isSolvable(row - 1, col);
			}
		}
		if((maze[row+1][col]=='-')){
			if((isVisited[row+1][col]==false)){
			isVisited[row][col]=true;
			isSolvable(row+1, col);
			}
		}
		if((maze[row][col-1]=='-')){
			if((isVisited[row][col-1]==false)) {
				isVisited[row][col] = true;
				isSolvable(row, col - 1);
			}
		}
		if((maze[row][col+1]=='-')){
			if((isVisited[row][col+1]==false)) {
				isVisited[row][col] = true;
				isSolvable(row, col + 1);
			}
		}
		return isExit;
	}
	

	void printMaze(){
		for(int row=0;row<7;row++){
			for(int column=0;column<7;column++){
				System.out.print(maze[row][column]+" ");
			}
				System.out.println();
			
		}
	}	
	//method to analyze the input command 
	public void inputCommand(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your next move : L(left), " +
				"R(right), U(up), D(down) ");
		command = scanner.next();
		int size = command.length();
		if (!command.equals("exit")) {
			if(command.equals("restart")){ //restarting the game
                isExit = false;
                initializeMaze();
            }
            else if(command.equals("top")){ // printing the score board if not empty
                if(board.list.size()>0){   
                    board.printBoard(board.list); 
                }
                else{
                    System.out.println("The High score board is empty!");
                }
            }
            else if(size>1){
                System.out.println("Invalid command!");
            }
            else {
                movePlayer(command.charAt(0));
            }
		} else {
			System.out.println("Good bye!");
			System.exit(0);
		}
	}	
	// a method to move the player to the right when called
	public  void movePlayerRight(){
	//checking if the move is possible	
		if (maze[playersCurrentRow][playersCurrentColumn + 1] != 'X') {
			swapCells(playersCurrentRow, playersCurrentRow,
					playersCurrentColumn, playersCurrentColumn + 1);

			playersCurrentColumn++;
			playersMovesCount++;

		} else {
			System.out.println("Invalid move!");
			printMaze();
		}		
	}
	// a method to move the player to the left when called
	public  void movePlayerLeft(){
		//checking if the move is possible
		if (maze[playersCurrentRow][playersCurrentColumn - 1] != 'X') {
			swapCells(playersCurrentRow, playersCurrentRow,
					playersCurrentColumn, playersCurrentColumn - 1);

			playersCurrentColumn--;
			playersMovesCount++;
		} else {
			System.out.println("Invalid move!");
			printMaze();
		}		
	}
	// a method to move the player up when called
	public  void movePlayerUp(){
		//checking if the move is possible
		if (maze[playersCurrentRow - 1][playersCurrentColumn] != 'X') {
			swapCells(playersCurrentRow, playersCurrentRow - 1,
					playersCurrentColumn, playersCurrentColumn);

			playersCurrentRow--;
			playersMovesCount++;

		} else {
			System.out.println("Invalid move!");
			printMaze();
		}		
	}
	// a method to move the player down when called
	public void movePlayerDown(){
		//checking if the move is possible
		if (maze[playersCurrentRow + 1][playersCurrentColumn] != 'X') {
			swapCells(playersCurrentRow, playersCurrentRow + 1,
					playersCurrentColumn, playersCurrentColumn);
			
			playersCurrentRow++;
			playersMovesCount++;

		} else {
			System.out.println("Invalid move!");
			printMaze();
		}		
	}
	public  void movePlayer(char firstLetter){
		// using toLowerCase to have "L" and "l" in the same, for the sake of decreasing complexity
		if (Character.toLowerCase(firstLetter) == 'l') {
			movePlayerLeft();
		} else if (Character.toLowerCase(firstLetter) == 'r') {
			movePlayerRight();
		} else if (Character.toLowerCase(firstLetter) == 'u') {
			movePlayerUp();
		} else if (Character.toLowerCase(firstLetter) == 'd') {
			movePlayerDown();
		} else {
			System.out.println("Invalid command!");
		}
	}
		
	// method to swap two cells
	void swapCells(int currentRow, int newRow, int currentColumn, int newColumn){
		boolean evaluate=true;//evaluate()
		if(evaluate) {
			char previousCell = maze[currentRow][currentColumn];
			maze[currentRow][currentColumn] = maze[newRow][newColumn];
			maze[newRow][newColumn] = previousCell;
			System.out.println();
			printMaze();
		}
	}
	
	
}