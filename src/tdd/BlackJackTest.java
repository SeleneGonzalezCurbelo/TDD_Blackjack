package tdd;

import java.util.stream.Stream;
import model.Ace;
import model.Card;
import model.Figure;
import model.NumericCard;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BlackJackTest {
    
    public void test(){
        test_hand_value_with_one_card();
        test_hand_value_with_two_cards();
        test_hand_is_black_jack_with_two_cards();
        test_hand_is_not_black_jack_with_three_cards();
        test_hand_is_not_bust_with_two_cards();
        test_hand_is_not_bust_with_three_cards();
        case1();
        case2();
    }
    
    @Test
    public void test_hand_value_with_one_card() {
        System.out.println("Testing value with one card");
        NumericCard card3 = new NumericCard(3);
        NumericCard card10 = new NumericCard(10);
        Figure Jack = new Figure();
        Figure Queen = new Figure();
        Figure King = new Figure();
        Ace Ace = new Ace();
        
        assertEquals(3, createHand(card3).value());
        assertEquals(10, createHand(card10).value());
        assertEquals(10, createHand(Jack).value());
        assertEquals(10, createHand(Queen).value());
        assertEquals(10, createHand(King).value());
        assertEquals(11, createHand(Ace).value());
    }
    
    @Test
    public void test_hand_value_with_two_cards() {
        System.out.println("Testing value with two cards");
        NumericCard card3 = new NumericCard(3);
        NumericCard card5 = new NumericCard(5);
        NumericCard card6 = new NumericCard(6);
        Ace Ace = new Ace();
        
        assertEquals(8, createHand(card3, card5).value()); 
        assertEquals(11, createHand(card5, card6).value());        
        assertEquals(12, createHand(Ace, Ace).value()); 
    }
    
    @Test
    public void test_hand_is_black_jack_with_two_cards() {
        System.out.println("Testing is Blackjack with two cards");
        NumericCard card3 = new NumericCard(3);
        NumericCard card5 = new NumericCard(5);
        NumericCard card6 = new NumericCard(6);
        NumericCard card10 = new NumericCard(10);
        Figure Jack = new Figure();
        Figure Queen = new Figure();
        Figure King = new Figure();
        Ace Ace = new Ace();
        
        assertEquals(false, createHand(card3, card5).isBlackJack());      
        assertEquals(false, createHand(card5, card6).isBlackJack());    
        assertEquals(true, createHand(Ace, Jack).isBlackJack());
        assertEquals(true, createHand(Ace, King).isBlackJack());
        assertEquals(true, createHand(Ace, Queen).isBlackJack());
        assertEquals(true, createHand(card10, Ace).isBlackJack());        
    }
    
    @Test
    public void test_hand_is_not_black_jack_with_three_cards() {
        System.out.println("Testing is not Blackjack with three cards");
        NumericCard card5 = new NumericCard(5);
        NumericCard card6 = new NumericCard(6);
        Figure Queen = new Figure();
        
        assertEquals(false, createHand(card5, card6, Queen).isBlackJack());
    }
    
    @Test
    public void test_hand_is_not_bust_with_two_cards() {
        System.out.println("Testing is not bust with two cards");
        NumericCard card3 = new NumericCard(3);
        NumericCard card4 = new NumericCard(4);
        
        assertEquals(false, createHand(card4,card3).isBust());
    }
    
    @Test
    public void test_hand_is_not_bust_with_three_cards() {
        System.out.println("Testing is not bust with three cards");
        NumericCard card2 = new NumericCard(2);
        NumericCard card3 = new NumericCard(3);
        NumericCard card4 = new NumericCard(4);
        Figure Jack = new Figure();
        Figure Queen = new Figure();
        Figure King = new Figure();
        
        assertEquals(true, createHand(card4, Jack, King).isBust());               
        assertEquals(false, createHand(card4, card2, card3).isBust());        
    }
    
    @Test
    public void case1() {
    }
    
    @Test
    public void case2() {
    }
    
    private Hand createHand(Card... cards) {
        return new Hand() {
            @Override
            public int value() {
                int sum = 0;
                for (Card card : cards){
                    sum += card.getValue();
                    if(sum > 21 && card.getValue() == 11) {
                        sum -= card.getValue();
                        sum++;
                    }
                }
                return sum;
            }

            private int sum() {
                return Stream.of(cards).mapToInt(c->c.getValue()).sum();
            }
            
            private boolean canUseAceExtendedValue() {
                return sum() <= 11 && containsAce();
            }
            
            private boolean containsAce() {
                Ace Ace = new Ace();
                return Stream.of(cards).anyMatch(c->c==Ace);
            }
            
            @Override
            public boolean isBlackJack() {
                return value() == 21 && cards.length == 2;
            }

            @Override
            public boolean isBust() {                
                return value() > 21;
            }
        };
    }

    public interface Hand {
        public int value();
        public boolean isBlackJack();
        boolean isBust();
    }
    

    

}