package model.card;

public class Ace implements Card{
    private int value;
    
    public Ace() {
        this.value = 11;
    }
    
    @Override
    public int getValue() {
        return value;
    }
    
    public void updateValue() {
        if (this.value == 11) {
            this.value = 1;
        }
    }
    
    public boolean isValueOne() {
        return this.value == 1;
    }
}