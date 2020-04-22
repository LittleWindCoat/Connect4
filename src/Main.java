//FZ and CL
//CSC242 Project 1
//Connect 4
//18 September 2018

	import java.util.Scanner;
	
	/*Standard Connect 4 as a State-space search problem 
	 * States: set of 42 locations being either Red, Yellow or empty
	 * Actions: Drop token into a column
	 * Applicable: Can drop token if column not full
	 * Cost: Doesn't matter
	 * Initial State: All locations empty
	 * Goal state: Four in a line of any color
	 * */
	
	public class Main {
	    
	    static Scanner in;
	    
	    static boolean finished;
	    
	    static GameState current;
	    
	    static Player startingPlayer;
	    static Player currentPlayer;
	    static Player human;
	    static Player computer;
	    
	    static int depth;
	    static int boardNum;
	    
	    public static void main(String[] args) {
	    	int humanChoice=-1;
	        int agentNum=-1;
	        
	        human = new Player("human");
	        computer = new Player("computer");
	        finished = false;
	        in = new Scanner(System.in);
	        int option1=-1;
	        int option2=-1;
	        System.out.println("Connect 4 by Fengyi and Charlene");
	        System.out.println("Choose your game:");
	        System.out.println("1. Tiny 3x3x3 Connect-Three (only valid for Random)");
	        System.out.println("2. Wider 3x5x3 Connect-Three  (only valid for Random)");
	        System.out.println("3. Standard 6x7x4 Connect-Four (valid for Random, Minimax or AlphaBeta players)");
	        
	        if(in.hasNext()){
                boardNum = in.nextInt();
	        }
	       
	        
	        //calls a standard game against minimax players
	        while(option1==-1){
	            System.out.println("If you want to go first press 1. For the computer to go first, press 2.");
	            if(in.hasNext()){
	                humanChoice = in.nextInt();
	                if(humanChoice==1 || humanChoice==2){
	                    option1 = humanChoice;
	                    if(humanChoice==2){
	                        startingPlayer = computer;
	                        currentPlayer = human;
	                        System.out.println("Computer will go first using Yellow.");
	                    }
	                    if(humanChoice==1){
	                        startingPlayer = human;
	                        currentPlayer = computer;
	                        System.out.println("You will go first using Red.");
	                    }
	                }
	                if(humanChoice==1 || humanChoice ==2){
	                    while(option2==-1){
	                        System.out.println("Choose your opponent:");
	                        
	                        System.out.println("1. An agent that uses MINIMAX. Only select if you selected standard board 6x7x4.");
	                        System.out.println("2. An agent that uses MINIMAX with alpha-beta pruning. Only select if you selected standard board 6x7x4.");
	                        System.out.println("3. An agent that plays Randomly.");
	                        //System.out.println("4. An agent that uses H-MINIMAX with fixed depth cutoff");//could not implement
	                        if(in.hasNext()){
	                            agentNum = in.nextInt();
	                            if(agentNum==1 || agentNum==2 || agentNum==3){
	                                option2 = humanChoice;
	                                current = new GameState();
	                                System.out.println("Depth limit? Enter 1.");
	                                if(in.hasNext()){
	    	                            depth = in.nextInt();
	                                }
	                                setMaximumDepth(depth);
	                                if(agentNum == 3){
	                                    System.out.println("Chosen random");
	                                }
	                                if(agentNum == 1){
	                                    System.out.println("Chosen minimax");
	                                }
	                                if(agentNum == 2){
		                                System.out.println("Chosen alpha-beta-pruning");
		                            }
		                            
	                                
	                            }
	                        }
	                    }
	                }
	            }
	        }
	        
	        //calls new random game
	        if (agentNum==3) {
	        Game game = new Game(boardNum, agentNum, 4, humanChoice);
			game.play();
	        }
	        
	        while(!finished && agentNum==1){
	            System.out.println(current);
	            if(option1==1){
	                if(getCurrentPlayer().getinplay().equals("human")){if(playComputer(0)){System.out.println("The computer won!");break;}}
	                else{if(playHuman()){

	                    System.out.println("You won!");break;}
	                }
	            }
	            if(option1==2){if(getCurrentPlayer().getinplay().equals("computer")){if(playHuman()){System.out.println("You won!");break;}}
	                else{if(playComputer(0)){

	                    System.out.println("The computer won!");break;}}
	            }
	        }
	        while(!finished && agentNum==2){
	            System.out.println(current);
	            if(option1==1){
	                if(getCurrentPlayer().getinplay().equals("human")){
	                    if(playComputer(1)){
	                        
	                        System.out.println("The computer won!");
	                        break;
	                    }
	                }
	                else{
	                    if(playHuman()){

	                        System.out.println("You won!");
	                        break;
	                    }
	                }
	            }
	            if(option1==2){
	                if(getCurrentPlayer().getinplay().equals("computer")){
	                    if(playHuman()){
	                        System.out.println("You won!");
	                        break;
	                    }
	                }
	                else{
	                    if(playComputer(1)){
	                        System.out.println("The computer won!");
	                        break;
	                    }
	                }
	            }
	        }
	        
	    }
	    
	    
	    //Method to see if it is computer's turn
	    private static boolean playComputer(int i){
//	        System.out.println(current);
//	        System.out.println("UTILITY: "+MinMax.UTILITY(current));
	        Decision move=null;
	        
	        long endtime=-1;
	        long starttime = System.nanoTime();
	        if(i==0){
	            move = Agent.getMinimaxDecision(current);
	            endtime = System.nanoTime();
	        }
	        if(i==1){
	            move = Agent.getAlphaBetaDecision(current);
	            endtime = System.nanoTime();
	        }
	        /*if(i==2){
	            move = Agent.getRandomDecision(current);
	            endtime = System.nanoTime();
	        }*/
	
	        long e = endtime-starttime;
	        double seconds = (double)e/1000000000.0;
	        
	        System.out.println("Elapsed time:"+ seconds + " secs");
	        
	        //updates state using the move gotten
	        GameState newState = new GameState(current.getBoard(),move,current.getDepth()+1);
	        
	        setCurrentState(newState);
	        setCurrentPlayer(computer);

	        if(Agent.UTILITY(current) == 320){System.out.println(current);return true;}
	        setMaximumDepth(current.getDepth()+depth);

	        return false;
	    }
	    
	  //Method for human to make play
	    private static boolean playHuman(){
	        int result[]=gethumanMove();
	        GameState newState = new GameState(current.getBoard(),new Decision(human,result),current.getDepth()+1);
	        setCurrentState(newState);
	        setCurrentPlayer(human);

	        if(Agent.UTILITY(current) == -320){System.out.println(current);return true;}

	        setMaximumDepth(current.getDepth()+depth);
	        
	        return false;
	    }
	    
	    
	    private static void setCurrentState(GameState state){
	        current = state;
	    }
	    
	    private static void setCurrentPlayer(Player player){
	        currentPlayer = player;
	    }
	    
	    private static Player getCurrentPlayer(){
	        return currentPlayer;
	    }
	    
	    public static int[] gethumanMove(){
	        int option1=-1;
	        int[] pos = null;
	        while(option1==-1){
	            System.out.println("Next to move: You.");
	            System.out.println("Your move?");
	            //System.out.println(current);
	            if(in.hasNext()){
	                int input = in.nextInt();
	                for(int[] s: current.getValidPos()){
	                    if(s[1]==input-1){
	                        return s;
	                    }
	                }
	            }
	        }
	        System.out.println("playing to option"+option1);
	        return pos;
	    }
	    
	    //Method for fixed depth limit
	    private static void setMaximumDepth(int s){
	        Agent.depthLimit = s;
	    }
	    
	}


