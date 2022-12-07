package main.java.org.cal.examen;

public class GameRunner {


    public static void main(String[] args) {
        Game aGame = new Game();

        aGame.addPlayer("Mathieu");
        aGame.addPlayer("Samir");
        aGame.addPlayer("Manolo");
        aGame.start();



    }
}
