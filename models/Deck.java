package models;

import java.util.*;

public class Deck {

    private List<Card> cards;
    private Queue<Card> drawPile;

    public Deck() {
        cards = new ArrayList<>();
        createDeck();
        shuffleDeck();
        drawPile = new LinkedList<>(cards);
    }

    private void createDeck() {
        // TODO: Create UNO cards
    }

    public void shuffleDeck() {
        // TODO: Shuffle cards
    }

    public Card drawCard() {
        // TODO: Return top card
        return null;
    }

    public boolean isEmpty() {
        return drawPile.isEmpty();
    }
}
