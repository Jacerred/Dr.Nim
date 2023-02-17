import java.lang.Math;
import java.util.Scanner;

public class Nim {
    private int pieces;
    private Player player1;
    private Player player2;
    private int turn;
    private boolean gameOver;

    static Scanner sc = new Scanner(System.in);

    public Nim() {
        setupPlayers();

        this.pieces = setPieces();

        // randomize who starts
        this.turn = (int) ((Math.random() * 2) + 0);

        this.gameOver = false;
    }

    // Accessors
    public int getPieces() {
        return this.pieces;
    }


    // Mutators

    /*
     * Precondition:
     *  Nim object is created
     *  The user is either starting or reseting the game
     * Postcondition:
     *  Instance variable pieces is updated
     * @return integer value to set the instance variable pieces to
     */
    private int setPieces() {
        // random integer between 10-50
        return (int) (Math.random() * 40) + 10;
    }

    /*
     * Precondition:
     *  Nim object is created
     * Postcondition:
     *  Player instance variables are updated for a game to be player
     */
    private void setupPlayers() {
        // setup board
        System.out.println("What is Player 1's name?");
        this.player1 = new Player(sc.nextLine());
        System.out.println("What is Player 2's name?");
        this.player2 = new Player(sc.nextLine());
        clearScreen();
    }
    
    // additional methods

    /*
     * Precondition:
     *  Players are created
     *  turn, game_over, and other variables have been initialized
     * Postcondition:
     *  Game continues and resets or is terminated
     *  The two players' stats are changed based on the result
     */
    public void play() {
        resetGame();
        while (!this.gameOver) {
            System.out.printf("\nPieces Left: %s%n", this.pieces);
            switch(this.turn%2) {
                case 0:
                System.out.println("It is " + this.player1.getName() + "'s turn.");
                
                    if(grabPieces(this.player1)) {
                        this.turn++;
                    }
                    break;
                    case 1:
                    System.out.println("It is " + this.player2.getName() + "'s turn.");
                    
                    if (grabPieces(this.player2)) {
                        this.turn++;
                    }   
                    break;
            }
            // gameover
            if (this.pieces == 0) {
                endGame();
            }
            wait(1000);
            clearScreen();
        }
    }
    
    /*
     * Precondition:
     *  A game has been completed
     * Postcondition:
     *  pieces and turn instance variables are reset for play main loop to continue
     */
    private void resetGame() {
        this.gameOver = false;
        this.pieces = setPieces();
        this.turn = (int) ((Math.random() * 2) + 0);
    }
    
    /*
     * Precondition:
     *  A game has been completed
     * Postcondition:
     *  Stats are updated and shown of each player
     *  The game is reset if the user chooses to keep playing
     */
    private void endGame() {
        // check winner
        if (this.turn%2 == 0) {
            System.out.printf("%n%s won the game!%n", this.player2.getName());
            this.player2.incWin();
            this.player1.incLoss();
        }
        else {
            System.out.printf("%n%s won the game!%n", this.player1.getName());
            this.player1.incWin();
            this.player2.incLoss();
        }
        // print stats
        System.out.println(this);
        // play again?
        System.out.println("\nDo you want to play again? (y/n)");
        sc.nextLine();
        if (sc.nextLine().strip().toLowerCase().equals("n")) this.gameOver = true;
        else {
            resetGame();
        }
    }

    /*
     * Precondition:
     *  Players exist
     *  pieces instance variable has been initialized to a random integer and is continually updated
     * Postcondition:
     *  Turn of the player is intended to end or continue in the main loop
     * 
     * @return boolean of whether or not the player's turn is over
     */
    private boolean grabPieces(Player p) {
        System.out.println("How many pieces do you want to grab?");
        int num = sc.nextInt();
        if (num > 3 || num > this.pieces) {
            System.out.println("You are trying to take too many pieces.");
            return false;
        }
        else {
            this.pieces -= num;
            return true;
        }
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        }
        catch(Exception e){
            System.out.println("Broke :(");
        }
    }

    public String toString() {
        String info = "";

        // print stats
        if (this.player1 != null) info += this.player1.getName() + "'s Win/Loss Ratio: " + this.player1.getWins() + "/" + this.player1.getLosses() + "\n";
        else info += "No information is available on Player 1\n";
        if (this.player2 != null) info += this.player2.getName() + "'s Win/Loss Ratio: " + this.player2.getWins() + "/" + this.player2.getLosses();
        else info += "No information is available on Player 2";

        return info;
    }
}
