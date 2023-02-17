public class Player {
    private String name;
    private int[] stats = new int[2]; // win, loss

    public Player(String name) {
        // set name
        this.name = name;

        // initalize win/loss
        this.stats[0] = 0;
        this.stats[1] = 0;
    }

    // default if no parameters sent
    public Player() {
        this("Dr. Nim");
    }

    // Accessors
    
    public String getName() {
        return this.name;
    }

    public int getWins() {
        return this.stats[0];
    }

    public int getLosses() {
        return this.stats[1];
    }

    // Mutators

    public void setName(String name) {
        this.name = name;
    }

    public void incWin() {
        this.stats[0]++;
    }

    public void incLoss() {
        this.stats[1]++;
    }

    // other

    public boolean playerEquals(Player p) {
        return false;
    }

    public String toString() {
        String info = "";

        // print stats
        info += getName() + "'s Win/Loss Ratio: " + getWins() + "/" + getLosses();
        
        return info;
    }
}
