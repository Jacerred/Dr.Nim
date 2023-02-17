import java.lang.Math;
import java.util.Scanner;

public class Nim {
    private int pieces;
    private Player player1;
    private Player player2;
    private int turn;
    private boolean game_over;

    static Scanner sc = new Scanner(System.in);

    public Nim() {
        this.pieces = setPieces();

        // randomize who starts
        this.turn = (int) ((Math.random() * 2) + 0);

        this.game_over = false;
    }

    // Accessors
    public int getPieces() {
        return this.pieces;
    }


    // Mutators

    private int setPieces() {
        // random integer between 10-50
        return (int) (Math.random() * 40) + 10;
    }

    
    // additional methods
    private void setupPlayers() {
        // setup board
        System.out.println("What is Player 1's name?");
        this.player1 = new Player(sc.nextLine());
        System.out.println("What is Player 2's name?");
        this.player2 = new Player(sc.nextLine());
        clearScreen();
    }

    public void play() {
        
        setupPlayers();

        while (!this.game_over) {
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
    
    public void resetGame() {
        this.pieces = setPieces();
        this.turn = (int) ((Math.random() * 2) + 0);
    }
    
    public void endGame() {
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
        if (sc.nextLine().strip().toLowerCase().equals("n")) this.game_over = true;
        else {
            resetGame();
        }
    }

    public boolean grabPieces(Player p) {
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
