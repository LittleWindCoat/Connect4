//FZ and CL
//CSC242 Project 1
//Connect 4
//18 September 2018
//Class GameState 

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;


public class GameState {
    private int[][] board;
    private Decision move;
    private int depth;
    private int utility;
    private boolean full;
    private Set<WinSequence> win;
    private LinkedList<int[]> list;
    private HashSet<GameState> childStates;
    private int row = 6;
    private int column = 7;

    
    public GameState(int[][] board, Decision move, int depth) {
        this.board = copyBoard(board);
        this.move = move;
        this.utility = 0;
        this.depth = depth;
        this.full = isFull();
        this.insertMove(this.board, this.move);
        this.list = new LinkedList<int[]>();
        this.list = buildFreePos(this.board);
        this.win = buildSet();
        this.childStates = new HashSet<GameState> ();
    }
    
    
    public void insertMove(int[][] board,Decision move){
 
        switch(move.getPlayer().getinplay()){
            case "human":{
                board[move.getPosition()[0]][move.getPosition()[1]]=6;
                break;
            }
            case "computer":{
                board[move.getPosition()[0]][move.getPosition()[1]]=0;
                break;
            }
        }
    }
    
    
    public LinkedList<int[]> buildFreePos(int[][] board){
        LinkedList<int[]> result = new LinkedList<int[]>();
        for(int i=0;i<6;i++){
            for(int j=0;j<7;j++){
                if(board[i][j]!=-1){
                    if(i-1>=0){
                        if(board[i-1][j]==-1){
                            int[] pos = new int[2];
                            pos[0] = i-1;
                            pos[1] = j;
                            result.add(pos);
                        }
                    }
                }
                else{
                    if(i==5){
                        int[] pos = new int[2];
                        pos[0] = i;
                        pos[1] = j;
                        result.add(pos);
                    }
                }
            }
        }
        return result;
    }

    public GameState(){//constructor for new game
        this.board = new int[row][column];
        this.list = new LinkedList<int[]>();
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                this.board[i][j] = -1;
            }
        }
        this.utility = 0;
        this.move = null;
        this.depth = 0;
        this.full = false;
        this.win = buildSet();
        for(int i=0;i<column;i++){
            int[] available_pos = new int[2];
            available_pos[0]=5;
            available_pos[1]=i;
            this.list.add(available_pos);
        }
        this.childStates = new HashSet<GameState> ();
    }
    
    
    public int[][] copyBoard(int[][] board){
        int[][] newBoard = new int[row][column];
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }
    
    public void setFull(){
        this.full = true;
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(this.board[i][j] == -1){
                    this.full = false;
                }
            }
        }
    }
    
    public void addChild(GameState s){
        this.childStates.add(s);
    }
 
    
    public HashSet<GameState> getChildren(){
        return this.childStates;
    }
    
    public String printChildren(){
        String r = "";
        if(this.getChildren()!=null)
        for(GameState s:this.getChildren()){
            r+=s;
            r+="\n";
            r+=""+s.getUtility();
            r+="\n";
            System.out.println(s.getUtility());
        }
        return r;
    }
    
    public Set<WinSequence> getWinSequence(){
        return this.win;
    }
    
  
    public void printSegmentSet(){
        if(this.win.isEmpty())return;
        for(WinSequence s:this.win){
            s.setWinSequenceValue();

        }

    }
    
    //Method to find win sequence
    public Set<WinSequence> buildSet(){
        LinkedHashSet<WinSequence> temp = new LinkedHashSet<WinSequence>();
        WinSequence seg = null;
        int f=0;
        //verticals
        for(int k=0;k<row-3;k++){
            for(int l=0;l<column;l++){
                    f++;
                    int a[] = {k,l};
                    int b[] = {k+3,l};
                    seg = new WinSequence(a,b,this);
                    seg.setWinSequenceValue();
                    temp.add(seg);
            }
        }
        for(int s=0;s<row;s++){
            for(int h=0;h<column;h++){
                if(h+3<7){
                    //horizontals
                    int a[] = {s,h};
                    int b[] = {s,h+3};
                    seg = new WinSequence(a,b,this);
                    seg.setWinSequenceValue();
                    temp.add(seg);
                }
                // // diagonals
                if(s-3>=0 && h+3<column){
                    int a[] = {s,h};
                    int b[] = {s-3,h+3};
                    seg = new WinSequence(a,b,this);
                    seg.setWinSequenceValue();
                    temp.add(seg);
                // \\ diagonals
                }
                if(h+3<column && s+3<row){
                    int c[] = {s,h};
                    int d[] = {s+3,h+3};
                    seg = new WinSequence(c,d,this);
                    seg.setWinSequenceValue();
                    temp.add(seg);
                }
            }
        }
        return temp;
    }
    
   //Method to print board
    public String printBoard(){
        String result="";
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(this.board[i][j]==-1)result+=" _";
                if(this.board[i][j]==6)result+=" R";
                if(this.board[i][j]==0)result+=" Y";
                if(j==6)result+="\n";
            }
        }
 
        result+="\n";
        for(int i=1;i<8;i++){
            result+=" "+i;
        }
        result+="\n";
        return result;
    }
    
    public boolean isFull(){
        setFull();
        return this.full;
    }

    public LinkedList<int[]> getValidPos(){
        return this.list;
    }
    
    public String getValidPosAsString(){
        String result="valid Pos: ";
        int counter=0;
        for(int[] s:this.list){
            counter+=1;
            result+=""+counter+"- x: "+s[0]+",1:"+s[1]+"| ";
        }
        return result;
    }
    
    public int getUtility() {
        return utility;
    }

    public void setUtility(int utility) {
        this.utility = utility;
    }
    
    public int[][] getBoard() {
        return board;
    }

    public Decision getMove() {
        return move;
    }

    public int getDepth() {
        return depth;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void setMove(Decision move) {
        this.move = move;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public String toString() {
          return printBoard();
//        return "State{" + "board=\n" + printBoard() + ", move=" + move + ", depth=" + depth +" "+getValidPosAsString() +""+ '}';
    }
    

}
