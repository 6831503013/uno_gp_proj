package models;

public class Move {

    public enum Type {
        PLAY,
        DRAW
    }

    private Type type;
    private Card card; // null if DRAW

    public Move(Type type, Card card) {
        this.type = type;
        this.card = card;
    }

    public Type getType() {
        return type;
    }

    public Card getCard() {
        return card;
    }
}
