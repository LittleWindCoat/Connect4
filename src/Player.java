//FZ and CL
//CSC242 Project 1
//Connect 4
//18 September 2018
//Class Player to keep who is in play and color of player

public class Player {
    private String inplay;
    private String color;

    public Player(String inplay) {
        this.inplay = inplay;
        if(this.inplay.equals("computer")){
            this.color = "Y";
        }
        if(this.inplay.equals("human")){
            this.color = "R";
        }
    }

    public String getinplay() {
        return inplay;
    }

    public String getcolor() {
        return color;
    }

    public void setinplay(String inplay) {
        this.inplay = inplay;
    }

    public void setcolor(String color) {
        this.color = color;
    }


    
    
}
