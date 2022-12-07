package main.java.org.cal.examen;

public class Player {
    private String name;
    private int place;
    private int purse;
    private boolean inPenaltyBox;

    public Player(String name) {
        this.name = name;
        purse = 0;
        place = 0;
        inPenaltyBox = false;
    }

    public void putInPenalty(){
        inPenaltyBox = true;
    }

    public String getName() {
        return name;
    }

    public boolean inPenalty() {
        return inPenaltyBox;
    }

    public int getPlace() {
        return place;
    }

    public void addPlace(int toAdd) {
        place += toAdd;
    }

    public void incrementPurse() {
        purse++;
    }

    public int getPurse() {
        return purse;
    }
}
