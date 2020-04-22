//FZ and CL
//CSC242 Project 1
//Connect 4
//18 September 2018
//Class Decision

public class Decision {
    private Player player;
    private int[] position;

    public Decision(Player player, int[] position) {
        this.player = player;
        this.position = position;
    }
    
    public Player getPlayer() {
        return player;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    /*public String getPositionAsString(){
        if(this.position!=null){return "pos 0: "+this.position[0]+"pos 1: "+this.position[1];}
        return "pos 0: null "+" pos 1: null";
    }*/
    
    
    
}