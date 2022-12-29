package model;

import model.card.Ace;
import model.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final int id;
    private String name;
    private List<Card> betCards = new ArrayList<>();

    public Player(int id, String name, List<Card> betCards) {
        this.id = id;
        this.name = name;
        this.betCards = betCards;
    }
    
    public int getId() {
        return id;
    }

    public List<Card> getBetcards() {
        return betCards;
    }
    
    public void setBetCards(List<Card> betCards) {
        this.betCards = betCards;
    }
    
    public int getValueCards() {
        int value = 0, aces = 0;
        for (Card card : betCards) { 
            value += card.getValue(); 
            if(card instanceof Ace){aces++;}
        }
        if(aces != 0) while(value>21){ value -= 10; }
        return value;
    }
    
    public void addCard(Card card){
        betCards.add(card);
    }

    @Override
    public String toString() {
        return this.name;
    }
}