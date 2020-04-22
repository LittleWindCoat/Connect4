//FZ and CL
//CSC242 Project 1
//Connect 4
//18 September 2018

import java.util.Scanner;

public class Game {
	private int boardNum = 0;
	private int playerNum = 0;
	private int depthLimit = 0;
	private int humanChoice = 0;
	 int turn;
	int playerTurn = 1;
	int compTurn = 2;
	private int row = 0;
	private int col =0;
	char[][] board;
	private int needWin = 0;
	int i, j = 0;
	boolean endGame = false;
	
	
	//Constructor
	public Game(int board, int player, int depthLimit, int humanChoice){
		boardNum = board;
		playerNum = player;
		this.depthLimit = depthLimit;
		this.humanChoice = humanChoice;
		}
	
	public void play() {
		
		Scanner sc = new Scanner(System.in);
		
		//Choose the board 
		if (boardNum == 1) {
			row = 3;
			col = 3;
			needWin =3;
			board = new char[3][3];
			}
		else if (boardNum == 2) {
			row= 3;
			col = 5;
			needWin =3;
			board = new char[3][5];
			
		} else if (boardNum == 3) {
			row = 6;
			col = 7;
			needWin =4;
			board = new char[6][7];
			
		}//end of if
		
		printEmptyBoard();
		
		//while not end of game
		
		if (humanChoice ==1) {//if human chose RED 
			turn=playerTurn;
		} else if (humanChoice==2) {//if human chose YELLOW
			turn=compTurn;
		}
		
		
		do {
		checkEndGame();
		
		if (turn==playerTurn) {
			System.out.println("Next to move: " + "You.");
			System.out.println("Your next move? ");
			int column = sc.nextInt();
			System.out.println("Your move " + "@" + column + " completed below.");
			
			int counter = 0;
			int rowcheck =0;
			i =0;
			j = column;
			
			if(boardNum==3) {
					if (board[i][j]=='_' && board[i+1][j]=='_' && board[i+2][j]=='_' && board[i+3][j]=='_' && board[i+4][j]=='_' && board[i+5][j]=='_' ) {
						rowcheck=5;
					} else if (board[i][j]=='_' && board[i+1][j]=='_' && board[i+2][j]=='_' && board[i+3][j]=='_' && board[i+4][j]=='_') {
						rowcheck =4;
				} else if (board[i][j]=='_' && board[i+1][j]=='_' && board[i+2][j]=='_' && board[i+3][j]=='_') {
					rowcheck =3;
			} else if (board[i][j]=='_' && board[i+1][j]=='_' && board[i+2][j]=='_' ) {
				rowcheck =2;
		}else if (board[i][j]=='_' && board[i+1][j]=='_' ) {
			rowcheck =1;
	}else if (board[i][j]=='_') {
		rowcheck =0;
}} //end num3
			
			if(boardNum==2||boardNum==1) { if (board[i][j]=='_' && board[i+1][j]=='_' && board[i+2][j]=='_' ) {
			rowcheck =2;
	}else if (board[i][j]=='_' && board[i+1][j]=='_' ) {
		rowcheck =1;
}else if (board[i][j]=='_') {
	rowcheck =0;
}} //end num2and1
			
	
			
			updateStatus(playerTurn, rowcheck, column);
			printUpdatedBoard();
			
		} 
		
		else if (turn==compTurn) {
			System.out.println("Next to move: " + "The computer.");
			PlayerRandom playerrandom = new PlayerRandom(boardNum);
			int column = playerrandom.makeMove();
			System.out.println("Computer's move " + "@" + column + " completed below.");
			int counter = 0;
			int rowcheck =0;
			i =0;
			j = column;
			if(boardNum==3) {
					if (board[i][j]=='_' && board[i+1][j]=='_' && board[i+2][j]=='_' && board[i+3][j]=='_' && board[i+4][j]=='_' && board[i+5][j]=='_' ) {
						rowcheck=5;
					} else if (board[i][j]=='_' && board[i+1][j]=='_' && board[i+2][j]=='_' && board[i+3][j]=='_' && board[i+4][j]=='_') {
						rowcheck =4;
				} else if (board[i][j]=='_' && board[i+1][j]=='_' && board[i+2][j]=='_' && board[i+3][j]=='_') {
					rowcheck =3;
			} else if (board[i][j]=='_' && board[i+1][j]=='_' && board[i+2][j]=='_' ) {
				rowcheck =2;
		}else if (board[i][j]=='_' && board[i+1][j]=='_' ) {
			rowcheck =1;
	}else if (board[i][j]=='_') {
		rowcheck =0;
}}//endnum3
			
			if(boardNum==2||boardNum==1) { if (board[i][j]=='_' && board[i+1][j]=='_' && board[i+2][j]=='_' ) {
				rowcheck =2;
		}else if (board[i][j]=='_' && board[i+1][j]=='_' ) {
			rowcheck =1;
	}else if (board[i][j]=='_') {
		rowcheck =0;
	}} //end num2and1
			
			
			updateStatus(compTurn, rowcheck, column);
			printUpdatedBoard();
		}
		
		} while (endGame==false);
		
	
		//if end of game, print who winner is
			if (turn==playerTurn)
			System.out.println("Game ends. You won!");
			else System.out.println("Game ends. You lost!");
		
			
		}
		
		//Method to check for end of game
		public void checkEndGame() {
			if (boardNum==3) {
				if (isHorizontal4() || isVertical4() || isDiagonal4()){//check for possible wins for 674
					endGame=true;
			}}
			if (boardNum==2) {
				if (isHorizontal3() || isVertical3() || isDiagonal3()){//check for possible wins for 353
					endGame=true;
			}}
			if (boardNum==1) {
				if (isHorizontal33() || isVertical33() || isDiagonal33()){//check for possible wins for 3x3x3
					endGame=true;
			}}
			
		}

			
		
		private boolean isDiagonal33() {
			if (board[0][0]!='_' && board[0][0]==board[1][1] && board[1][1]==board[2][2]) {
				return true;
		}
		if (board[2][0]!='_' && board[2][0]==board[1][1] && board[1][1]==board[0][2]) {
				return true;
		}
			return false;
		}

		private boolean isVertical33() {
			if (board[0][0]!='_' && board[0][0]==board[1][0] && board[1][0]==board[2][0]) {
				return true;
		}
		if (board[0][1]!='_' && board[0][1]==board[1][1] && board[1][1]==board[2][1]) {
				return true;
		}if (board[0][2]!='_' && board[0][2]==board[1][2] && board[1][2]==board[2][2]) {
				return true;
		}
			return false;
		}

		private boolean isHorizontal33() {
			if (board[0][0]!='_' && board[0][0]==board[0][1] && board[0][1]==board[0][2]) {
				return true;
		}
		if (board[1][0]!='_' && board[1][0]==board[1][1] && board[1][1]==board[1][2]) {
				return true;
		}if (board[2][0]!='_' && board[2][0]==board[2][1] && board[2][1]==board[2][2]) {
				return true;
		}
			return false;
		}

		private boolean isDiagonal3() {
			
			if (board[0][0]!='_' && board[0][0]==board[1][1] && board[1][1]==board[2][2]) {
					return true;
			}
			if (board[0][1]!='_' && board[0][1]==board[1][2] && board[1][2]==board[2][3]) {
					return true;
			}if (board[0][2]!='_' && board[0][2]==board[1][3] && board[1][3]==board[2][4]) {
					return true;
			}if (board[2][0]!='_' && board[2][0]==board[1][1] && board[1][1]==board[0][2]) {
					return true;
			}if (board[2][1]!='_' && board[2][1]==board[1][2] && board[1][2]==board[0][3]) {
					return true;
			}if (board[2][2]!='_' && board[2][2]==board[1][3] && board[1][3]==board[0][4]) {
					return true;
			}
			return false;
		}

		private boolean isVertical3() {
			for (int i=0; i <row-2; i++) {
				for(int j=0; j<col;j++) {
					if (board[i][j]!='_' && board[i][j]==board[i+1][j] && board[i+1][j]==board[i+2][j]) {
						return true;
					}
				}
			}
			
			return false;
		}

		private boolean isHorizontal3() {
			for (int i=0; i < row; i++) {
				for(int j=0; j<col-2;j++) {
					if (board[i][j]!='_' && board[i][j]==board[i][j+1] && board[i][j+1]==board[i][j+2]) {
						return true;
					}
				}
			}
			return false;
		}

		private boolean isDiagonal4() {
			for (int i=0; i < row-3; i++) {
				for(int j=0; j<col-3;j++) {
					if (board[i][j]!='_' && board[i][j]==board[i+1][j+1] && board[i+1][j+1]==board[i+2][j+2] && board[i+2][j+2]==board[i+3][j+3]) {
						return true;
					}
				}
			}
			for (int i=row-1; i >=3; i--) {
				for(int j=0; j<col-3;j++) {
					if (board[i][j]!='_' && board[i][j]==board[i-1][j+1] && board[i-1][j+1]==board[i-2][j+2] && board[i-2][j+2]==board[i-3][j+3]) {
						return true;
					}
				}
			}
			
			return false;
		}

		private boolean isVertical4() {
			for (int i=0; i < row-3; i++) {
				for(int j=0; j<col;j++) {
					if (board[i][j]!='_' && board[i][j]==board[i+1][j] && board[i+1][j]==board[i+2][j] && board[i+2][j]==board[i+3][j]) {
						return true;
					}
				}
			}
			
			return false;
		}

		private boolean isHorizontal4() {
			for (int i=0; i < row; i++) {
				for(int j=0; j<col-3;j++) {
					if (board[i][j]!='_' && board[i][j]==board[i][j+1] && board[i][j+1]==board[i][j+2] && board[i][j+2]==board[i][j+3]) {
						return true;
					}
				}
			}
			return false;
		}

		//Method to update status
				private void updateStatus(int turn, int updaterow, int updatecol) {
					System.out.println("");
				char marker='_';
				if (turn == playerTurn && humanChoice ==1) {
					marker = 'R';
				} else if (turn == playerTurn && humanChoice ==2) {
					marker = 'Y';
				} else if (turn == compTurn && humanChoice ==1) {
					marker = 'Y';
				} else if (turn == compTurn && humanChoice ==2) {
					marker = 'R';
				} 
				
				
				for (i=0; i<row; i++) {
					for (j=0; j<col; j++)
						if (i==updaterow && j==updatecol)
							board[i][j] = marker;
							
				}
				changeTurn(turn);
				}
					
		
		
		

 	//Method to print empty board
	private void printEmptyBoard() {
		for (i=0; i<row; i++) 
			for (j=0; j<col; j++) 
				board[i][j] = '_';
		
		
		for (i=0; i<row; i++) {
			for (j=0; j<col; j++) {
				System.out.print(board[i][j]+" ");
			}
		System.out.println();
		}
		
		for (i=0; i<col; i++) {
		System.out.print(i + " ");
		}
		System.out.println();
	}
	
	

		// Method to print updated board
		private void printUpdatedBoard() {
			for (i=0; i<row; i++) {
				for (j=0; j<col; j++) {
					System.out.print(board[i][j]+" ");
				}
			System.out.println();
			}
			
			for (i=0; i<col; i++) {
			System.out.print(i + " ");
			}
			System.out.println();
		}
		
		//Method to change turn
		void changeTurn(int t) {
			if (t==playerTurn) {
				turn=compTurn;
			} else if (t==compTurn) {
				turn=playerTurn;
			}
			
			
			
		}
		
		
		
		
}