package control;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.card.Ace;
import model.card.Card;
import model.card.Figure;
import model.card.NumericCard;

public class CardFactory {
    private static final List<String> VALID_VALUES = Arrays.asList("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K");
    public static final Map<String, Card> cards = new HashMap<>();
    
    static {
        cards.put("A", new Ace());
        cards.put("J", new Figure());
        cards.put("Q", new Figure());
        cards.put("K", new Figure());
        for (int i = 2; i <= 10; i++) {
            cards.put(Integer.toString(i), new NumericCard(i));
        }
    }
    
    public static Card createCard(String value) {
        if (!VALID_VALUES.contains(value)) {
            return null;
        }
        switch (value) {
            case "A":
                return new Ace();
            case "J":
            case "Q":
            case "K":
                return new Figure();
            default:
                return new NumericCard(Integer.valueOf(value));
        }
    }
}