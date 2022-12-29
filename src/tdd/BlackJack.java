package tdd;

import java.util.ArrayList;
import java.util.List;

import control.CardFactory;
import model.card.Ace;
import model.card.Card;
import model.Player;

public class BlackJack {
    
    private final List<Player> players;
    private final List<Card> deck;
    
    public BlackJack(List<Player> players, List<Card> deck) {
        this.players = players;
        this.deck = deck;
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
        List<Ace> aces = new ArrayList<>();

        for (Card card : betcards){
               if(card instanceof Ace){
                   aces.add((Ace) card);
               }
               totalPoints += card.getValue();
        }
        
        for (Ace ace : aces) {
            if (totalPoints > 21) {
                totalPoints -= 10;
            }
        }
        return totalPoints;
    }
    
    public List<Player> getWinners(List<Player> players, List<Card> desk){
        List<Player> winnerPlayers = new ArrayList<>();
        
        croupierCards(players.get(0), desk);
        if (isBlackJack(players.get(0).getBetcards())){
            winnerPlayers.add(players.get(0));
            return winnerPlayers;
        }
        
        for (Player player : players) {
            if (isBlackJack(player.getBetcards())){
                winnerPlayers.add(player);
                break;
            }
        }
        
        if (winnerPlayers.isEmpty()){
            for (Player player : players) {
                int playerPoints = getPoints(player.getBetcards());
                if (!(playerPoints > 21)) {
                    int croupierPoints = getPoints(players.get(0).getBetcards());
                    if (croupierPoints > 21 || playerPoints > croupierPoints) {
                        winnerPlayers.add(player);
                    }
                }
            }
        }
        if (winnerPlayers.isEmpty() && getPoints(players.get(0).getBetcards()) <= 21){
            winnerPlayers.add(players.get(0));
        }
        return winnerPlayers;
    }
    
    public void croupierCards(Player croupier, List<Card> desk){
        int value = croupier.getValueCards();
        for(int i = 0; value < 17; i++){
            Card card= desk.get(i);
            croupier.addCard(card);
            value = croupier.getValueCards();
        }
    }
    
    public int getValueCards(List<Card> desk){
        int value = 0;
        value = desk.stream().map((card) -> card.getValue()).reduce(value, Integer::sum);
        return value;
    }
    
    public static List<Card> stringToCard(List<String> cards_string){
        List<Card> cards = new ArrayList<>();
        for (String card : cards_string) {
            cards.add(CardFactory.cards.get(card));
        }
        return cards;
    }
}