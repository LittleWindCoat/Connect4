//FZ and CL
//CSC242 Project 1
//Connect 4
//18 September 2018
//9/18/2018

import java.util.Random;
//Player choice 1
public class PlayerRandom {
	int boardNum;
	int column = 0;
	
	public PlayerRandom(int boardNum){
		this.boardNum = boardNum;
	}

	public int makeMove() {
		Random rand = new Random(); 
		
		if (boardNum == 3) {
        // Random integer from 0 to 6
        column = rand.nextInt(7); 
        
		}
		
		if (boardNum == 2) {
	        // Random integer from 0 to 4
	        column = rand.nextInt(5); 
	        
			}
		
		if (boardNum == 1) {
	        // Random integer from 0 to 2
	        column = rand.nextInt(2); 
	        
			}
		return column;
        
	}
}
