package models;

import java.util.*;

public class Player {

    private String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
    }

    public void addCard(Card card) {
        // TODO
    }

    public void removeCard(int index) {
        // TODO
    }

    public Card getCard(int index) {
        // TODO
        return null;
    }

    public int getHandSize() {
        return hand.size();
    }

    public void showHand() {
        // TODO: Display cards
    }

    public String getName() {
        return name;
    }
}
