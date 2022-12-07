package main.java.org.cal.examen;

import java.util.*;

public class Game {
    private final Queue<Player> players = new ArrayDeque<>();
    private final LinkedList<String> popQuestions = new LinkedList<>();
    private final LinkedList<String> scienceQuestions = new LinkedList<>();
    private final LinkedList<String> sportsQuestions = new LinkedList<>();
    private final LinkedList<String> rockQuestions = new LinkedList<>();

    private Player currentPlayer;
    private boolean isGettingOutOfPenaltyBox;

    public  Game(){
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast("Science Question " + i);
            sportsQuestions.addLast("Sports Question " + i);
            rockQuestions.addLast("Rock Question " + i);
        }
    }

    public void addPlayer(String playerName) {
        if(playersCount() == 6){
            System.out.println("Limite maximale atteinte");
            return;
        }
        Player player = new Player(playerName);
        players.add(player);
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + playersCount());
    }
    private int playersCount() {
        return players.size();
    }

    private void roll() {
        int roll = getRandomNum(5) + 1;
        System.out.println(currentPlayer.getName() + " is the current player");
        System.out.println("They have rolled a " + roll);
        if (!currentPlayer.inPenalty()) {
            addPlace(roll);
            askQuestion();
            return;
        }
        if (roll % 2 == 1) {
            isGettingOutOfPenaltyBox = true;
            System.out.println(currentPlayer.getName() + " is getting out of the penalty box");
            addPlace(roll);
            return;
        }
        System.out.println(currentPlayer.getName() + " is not getting out of the penalty box");
        isGettingOutOfPenaltyBox = false;
    }

    private void addPlace(int roll){
        currentPlayer.addPlace(roll);
        if (currentPlayer.getPlace() > 11) currentPlayer.addPlace(-12);

        System.out.println(currentPlayer.getName()
                + "'s new location is "
                + currentPlayer.getPlace());
        System.out.println("The category is " + currentCategory());
    }
    private void askQuestion() {
        if (currentCategory() == Categories.Rock)
            System.out.println(popQuestions.removeFirst());
        if (currentCategory() == Categories.Rock)
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory() == Categories.Rock)
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory() == Categories.Rock)
            System.out.println(rockQuestions.removeFirst());
    }


    private Categories currentCategory() {
        return switch (currentPlayer.getPlace() % 4){
            case 0 -> Categories.Pop;
            case 1 -> Categories.Science;
            case 2 -> Categories.Sports;
            default -> Categories.Rock;
        };
    }

    private boolean wasCorrectlyAnswered() {
        if (!currentPlayer.inPenalty() || isGettingOutOfPenaltyBox) {
            correctAnswer();
            return didPlayerWin();
        }
        nextPlayer();
        return true;
    }
    private void nextPlayer(){
        players.add(currentPlayer);
        currentPlayer = players.poll();
    }
    private void correctAnswer() {
        System.out.println("Answer was correct!!!!");
        currentPlayer.incrementPurse();
        System.out.println(currentPlayer.getName()
                + " now has "
                + currentPlayer.getPurse()
                + " Gold Coins.");
        nextPlayer();
    }

    private void wrongAnswer(){
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer.getName() + " was sent to the penalty box");
        currentPlayer.putInPenalty();
        nextPlayer();
    }


    private boolean didPlayerWin() {
        return currentPlayer.getPurse() != 6;
    }


    private int getRandomNum(int bound){
        Random rand = new Random();
        return rand.nextInt(bound);
    }
    private boolean isNotWinner() {
        if (getRandomNum(9) != 7) {
            return wasCorrectlyAnswered();
        }
        wrongAnswer();
        return false;
    }
    public void start() {
        currentPlayer = players.peek();
        nextPlayer();
        do {
            roll();
        } while (isNotWinner());
    }
}
