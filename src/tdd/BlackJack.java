package tdd;

import java.util.ArrayList;
import java.util.List;
import model.Ace;
import model.Card;
import model.Player;

public class BlackJack {
    
    private final List<Player> players;
    private final List<Card> deck;
    private final Player croupier;
    
    public BlackJack(List<Player> players, List<Card> deck) {
        this.players = players;
        this.deck = deck;
        this.croupier = new Player(0, croupierCards()); 
    }
    
    public List<Player> getPlayers() {
        return players;
    }

    public List<Card> getDeck() {
        return deck;
    }
    
    public boolean isBlackJack(List<Card> betcards){
        return getPoints(betcards) == 21 && betcards.size() == 2;
    }

    public static int getPoints(List<Card> betcards) {
        int totalPoints = 0;
        int numberAces = 0;
        for (Card card : betcards){
               if(card instanceof Ace){
                   numberAces++;
               }
               totalPoints += card.getValue();
        }
        
        while(totalPoints > 21 && numberAces >= 1){
            totalPoints -= 10;
            numberAces--;
        }
        return totalPoints;
    }

    private List<Card> croupierCards() {
        List<Card> croupierCards = new ArrayList<>();
        while (getPoints(croupierCards) < 17 && deck.size() > 0){
            croupierCards.add(deck.get(0));
            deck.remove(0);
        }
        return croupierCards;
    }
    
    public List<Player> getWinners(List<Player> players){
        List<Player> winnerPlayers = new ArrayList<>();
        
        if (isBlackJack(croupier.getBetcards())){
            winnerPlayers.add(croupier);
            return winnerPlayers;
        }
        players.forEach((player) -> {
            int playerPoints = getPoints(player.getBetcards());
            if (!(playerPoints > 21)) {
                int croupierPoints = getPoints(croupier.getBetcards());
                if (croupierPoints > 21 || playerPoints > croupierPoints) {
                    winnerPlayers.add(player);
                }
            }
        });
        
        if(winnerPlayers.isEmpty() && getPoints(croupier.getBetcards()) <= 21){
            winnerPlayers.add(croupier);
        }
        return winnerPlayers;
    }
}