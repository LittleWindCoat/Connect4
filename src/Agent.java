//FZ and CL
//CSC242 Project 1
//Connect 4
//18 September 2018
//Class Agent to keep different algorithms 

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

	public class Agent {
	    
	    public static int negativeInf = (int)Double.NEGATIVE_INFINITY;
	    public static int positiveInf = (int)Double.POSITIVE_INFINITY;
	    private static int[] win = {1,positiveInf};
	    private static int[] defeat = {-1,negativeInf};
	    public static int depthLimit;
	    static int expanded_nodes_count=0;
	    
	    
	    public static Decision getAlphaBetaDecision(GameState state){
	        expanded_nodes_count=0;
	        int v = MAX_VAL(state,negativeInf,positiveInf);
	        System.out.println("Expanded nodes count: "+expanded_nodes_count);
	        return getMove(v, state);
	    }
	    
	    public static int MAX_VAL(GameState state,int alpha,int beta){
	        if(TERMINAL_TEST(state)){
	            return UTILITY(state);
	        }
	        int v = negativeInf;
	        for(GameState s:SUCCESSOR(state)){
	            v = Math.max(v, MIN_VAL(s,alpha,beta));
	            if(v>=beta){state.setUtility(v);return v;}
	            alpha = Math.max(alpha, v);
	        }
	        state.setUtility(v);
	        return v;
	    }
	    
	    public static int MIN_VAL(GameState state,int alpha,int beta){
	        if(TERMINAL_TEST(state)){
	            return UTILITY(state);
	        }
	        int v = positiveInf;
	        for(GameState s:SUCCESSOR(state)){
	            v = Math.min(v, MAX_VAL(s,alpha,beta));
	            if(v<=alpha){return v;}
	            beta = Math.min(beta, v);
	        }
	        state.setUtility(v);
	        return v;
	    }
	    
	    
	    public static Decision getMinimaxDecision(GameState state){
	        expanded_nodes_count=0;
	        int v = MAX_VALUE(state);
	        System.out.println("Visited "+expanded_nodes_count + " states");
	        return getMove(v, state);
	    }
	    
	    public static int MAX_VALUE(GameState state){
	        if(TERMINAL_TEST(state)){
	            return UTILITY(state);
	        }
	        int v = negativeInf;
	        for(GameState s:SUCCESSOR(state)){
	            v = Math.max(v, MIN_VALUE(s));
	        }
	        state.setUtility(v);
	        return v;
	    }
	    
	    public static int MIN_VALUE(GameState state){
	        if(TERMINAL_TEST(state)){
	            return UTILITY(state);
	        }
	        int v = positiveInf;
	        for(GameState s:SUCCESSOR(state)){
	            v = Math.min(v, MAX_VALUE(s));
	        }
	        state.setUtility(v);
	        return v;
	    }
	    
	    public static boolean TERMINAL_TEST(GameState state){
	        if(state.isFull()){state.setUtility(UTILITY(state));return true;}
	        if(Math.abs(UTILITY(state))==512){
	            System.out.println(" I'm thinking...");
	            state.setUtility(UTILITY(state));
	            return true;}
	        if(state.getDepth()!=0){
	            if(state.getDepth()>=depthLimit){state.setUtility(UTILITY(state));return true;}
	        }
	        return false;
	    }
	    
	    public static Set<GameState> SUCCESSOR(GameState state){
	        HashSet<GameState> children = new HashSet<GameState>();
	        Decision move = null;
	        if(TERMINAL_TEST(state)){
	            return children;
	        }
	        for(int[] s:state.getValidPos()){
	            if(state.getMove()==null){
	                if(Main.currentPlayer.getinplay().equals("human")){
	                    move = new Decision(Main.computer,s);
	                }
	                if(Main.currentPlayer.getinplay().equals("computer")){
	                    move = new Decision(Main.human,s);
	                }
	            }
	            else{
	                if(state.getMove().getPlayer().getinplay().equals("human")){
	                    move = new Decision(Main.computer,s);
	                }
	                if(state.getMove().getPlayer().getinplay().equals("computer")){
	                    move = new Decision(Main.human,s);
	                }
	            }
	            GameState newState = new GameState(state.getBoard(),move,state.getDepth()+1);
	            children.add(newState);
	            state.addChild(newState);
	            expanded_nodes_count+=1;
	        }
	        return state.getChildren();
	    }
	    
	    public static int UTILITY(GameState state){
	        int total = 0;
	        for(WinSequence s:state.getWinSequence()){
	            s.setWinSequenceValue();
	            int current = s.getWinSequenceValue();
	            if(Math.abs(current)==320){
	                return current;
	            }
	            total+=current;
	        }
	        if(state.getMove()!=null){
	            if(state.getMove().getPlayer().getinplay().equals("computer")){total+=10;}
	            if(state.getMove().getPlayer().getinplay().equals("human")){total+=-10;}
	        }
	        return total;
	    }
	    
	    public static Decision getMove(int v,GameState state){
	        for(GameState s: state.getChildren()){
	            if(s.getUtility()==v){
	                return s.getMove();
	            }
	        }
	        return null;
	    }

		
}
