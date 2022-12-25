package model;

import tdd.BlackJack;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private final int id;
    private List<Card> betcards = new ArrayList<>();

    public Player(int id, List<Card> betcards) {
        this.id = id;
        this.betcards = betcards;
    }

    public int getId() {
        return id;
    }

    public List<Card> getBetcards() {
        return betcards;
    }
    
    @Override
    public String toString(){
        if (id == 0){
            return "Croupier: " + BlackJack.getPoints(betcards);
        }
        return "Jugador " + id + ": " + BlackJack.getPoints(betcards);
    }
}