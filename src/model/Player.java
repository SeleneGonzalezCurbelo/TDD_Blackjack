package model;

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
    
    public int getValueCards() {
        int value = 0, aces = 0;
        for (Card card : betcards) { 
            value += card.getValue(); 
            if(card instanceof Ace){aces++;}
        }
        if(aces != 0) while(value>21){ value -= 10; }
        return value;
    }
    
    public void addCard(Card card){
        betcards.add(card);
    }

    @Override
    public String toString(){
        if (id == 0){
            return "Croupier";
        }
        return "Player" + id;
    }
}