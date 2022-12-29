package model.card;

public class NumericCard implements Card{
    private final int value;

    public NumericCard(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}