package model.card;

public class Figure implements Card{
    private final int value = 10;

    @Override
    public int getValue() {
        return value;
    }
}