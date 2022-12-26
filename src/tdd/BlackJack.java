package tdd;

import java.util.ArrayList;
import java.util.List;
import model.card.Ace;
import model.card.Card;
import model.card.Figure;
import model.card.NumericCard;
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
    
    public List<Player> getWinners(List<Player> players, List<Card> desk){
        List<Player> winnerPlayers = new ArrayList<>();
        
        croupierCards(players.get(0), desk);
        if (isBlackJack(players.get(0).getBetcards())){
            winnerPlayers.add(players.get(0));
            return winnerPlayers;
        }
        for (Player player : players) {
            int playerPoints = getPoints(player.getBetcards());
            if (!(playerPoints > 21)) {
                int croupierPoints = getPoints(players.get(0).getBetcards());
                List<Card> cards = player.getBetcards();

                if ( (cards.get(0).getValue() == 10 && cards.get(1).getValue() == 11) || (cards.get(0).getValue() == 11 && cards.get(1).getValue() == 10)){
                    winnerPlayers.add(player);
                    break;
                }else if (croupierPoints > 21 || playerPoints > croupierPoints) {
                    winnerPlayers.add(player);
                }
            }
        }
        
        if(winnerPlayers.isEmpty() && getPoints(players.get(0).getBetcards()) <= 21){
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
        cards_string.forEach((String card) -> {
            if (null == card) {
                System.out.println("ERROR: UNA CARTA INTRODUCIDA NO ES CORRECTA");
                System.exit(1);
            } else switch (card) {
                case "A":
                    cards.add(new Ace());
                    break;
                case "J":
                case "Q":
                case "K":
                    cards.add(new Figure());
                    break;
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                case "10":
                    cards.add(new NumericCard(Integer.valueOf(card)));
                    break;
                default:
                    System.out.println("ERROR: UNA CARTA INTRODUCIDA NO ES CORRECTA");
                    System.exit(1);
            }
        });
        return cards;
    }
}