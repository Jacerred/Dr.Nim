public class NimRunner {
    public static void main(String[] args) {

        Nim game = new Nim();

        //System.out.println(game);
        game.play();

        Nim.sc.close();
    }
}
