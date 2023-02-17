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

    //---------------------------------Accessors---------------------------------------
    /*
     * Precondition:
     *  name is populated
     * Postcondition:
     *  String
     * @return String value name
     */

    public String getName() {
        return this.name;
    }
    /*
     * Precondition:
     *  Stats array is populated with a win value
     * Postcondition:
     *  int
     * @return int value wins (from stats array)
     */

    public int getWins() {
        return this.stats[0];
    }

    /*
     * Precondition:
     *  Stats array is populated with a loss value
     * Postcondition:
     *  int
     * @return int value losses (from stats array)
     */
    public int getLosses() {
        return this.stats[1];
    }

    //-------------------------------------Mutators------------------------------------

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
    
    /*
     * Precondition:
     *  Player p is populated
     *  Stats Array is populated with both a win and loss number
     * Postcondition:
     *  boolean
     * @return boolean value true/false
     */
    public boolean playerEquals(Player p) {
        //if (p == null) return false;
        if (p.getName().equals(this.name) && p.getWins() == this.stats[0] && p.getLosses() == this.stats[1]) return true;
        else return false;
    }
}