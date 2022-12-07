package main.java.org.cal.strategyexercice;

import java.util.ArrayList;
import java.util.List;

public class StrategyMain {
    
    public static void main(String[] args) {

        List<Personne> personnes = new ArrayList<>();
        personnes.add(new Personne(new Malheureux()));
        personnes.add(new Personne(new Heureux()));
        personnes.add(new Personne(new Triste()));

        personnes.forEach(Personne::printHumeur);
        }
}
