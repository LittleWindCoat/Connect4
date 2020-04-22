//FZ and CL
//CSC242 Project 1
//Connect 4
//18 September 2018
//Class Segment

public class WinSequence implements Comparable{
	
	private int redCount;//keep count of red tokens
    private int yelCount;//keep count of yellow tokens
    
    private int f[] = new int[2];
    private int l[] = new int[2];
    private String direction;
    private int value;
    
    private int[] tmpvals = new int[4];

    public WinSequence(int[] f, int[] l,GameState state) {
        this.f= f;
        this.l= l;
        this.direction = checkDirection(f,l);
        this.value = 0;
        this.redCount = 0;
        this.yelCount = 0;
        this.countTokens(state);
    }
    
    //
    private String checkDirection(int[] f,int[] l){
        if(f[0] == l[0]){
             return "horizontal";
        }
        if(f[1] == l[1]){
             return "vertical";
        }
        return "diagonal";
    }
    
    //Count tokens
    public int[] countTokens(GameState state){
        int tmp[] = new int[4];
        switch(direction){
            //left to right eval
            case "horizontal":{
                for(int i=0;i<4;i++){
                    int x = f[0];
                    int y = f[1];
                    tmp[i]=state.getBoard()[x][y+i];
                }
                break;
            }
            //top to bottom eval
            case "vertical":{
                for(int i=0;i<4;i++){
                    int x = f[0];
                    int y = f[1];
                    tmp[i]=state.getBoard()[x+i][y];
                }
                break;
            }
            case "diagonal":{
                int fx = f[0];
                int fy = f[1];
                int lx = l[0];
                int ly = l[1];
                for(int a=0;a<4;a++){
                    // this orientation \ bigger x bigger y
                    if(l[0]>f[0] && l[1]>f[1]){
                        tmp[a] = state.getBoard()[fx+a][fy+a];
                    }
                    // this orientation / smaller x bigger y
                    if( l[0] < f[0] && f[1]<l[1]){
                        tmp[a] = state.getBoard()[fx-a][fy+a];
                    }
                  
                }
                
               
                break;
            }
        }
        for(int i=0;i<4;i++){
            if(tmp[i]==6){
                this.setredCount(this.getredCount()+1);
            }
            if(tmp[i]==0){
                this.setyelCount(this.getyelCount()+1);
            }
        }
        tmpvals = tmp;
        return tmp;
    }
    
    public int getWinSequenceValue(){
        return this.value;
    }
    
    public String TmpVals(){
        String result="[";
        for(int s=0;s<4;s++){
            result+=tmpvals[s]+",";
        }
        result+="]";
        return result;
    }
    
    //obtains segment utility value
    public void setWinSequenceValue(){
        if(this.redCount==4){
            //System.out.println("found an ending segment: "+this);
            this.value = -320;
        }
        if(this.yelCount==4){
            this.value = 320;
        }
        if(this.redCount==0 && this.yelCount==3){
            this.value = 30;
        }
        if(this.redCount==0 && this.yelCount==2){
            this.value = 10;
        }
        if(this.redCount==0 && this.yelCount==1){
            this.value = 1;
        }
        if(this.redCount==1 && this.yelCount==0){
            this.value = -1;
        }
        if(this.redCount==2 && this.yelCount==0){
            this.value = -10;
        }
        if(this.redCount==3 && this.yelCount==0){
            this.value = -30;
        }
    }
    
    
    public void setF(int[] f) {
        this.f = f;
    }

    public void setL(int[] l) {
        this.l = l;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int[] getF() {
        return f;
    }

    public int[] getL() {
        return l;
    }

    public String getDirection() {
        return direction;
    }
    
    public int getredCount() {
        return redCount;
    }

    public int getyelCount() {
        return yelCount;
    }

    public void setredCount(int redCount) {
        this.redCount = redCount;
    }

    public void setyelCount(int yelCount) {
        this.yelCount = yelCount;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

 
    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    
}