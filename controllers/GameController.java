package controllers;

import java.util.*;
import models.*;

public class GameController {

    private Deck deck;
    private List<Player> players;
    private Stack<Card> discardPile;
    private int currentPlayerIndex;
    private boolean isClockWise = true;
    private String currentColor;

    public GameController() {
        deck = new Deck();
        players = new ArrayList<>();
        discardPile = new Stack<>();
        currentPlayerIndex = 0;
    }

    public void startGame() {
        setupPlayers();
        dealCards();
        initializeDiscardPile();
        gameLoop();
    }

    private void setupPlayers() {
        String[] names = { "Human", "CPU 1", "CPU 2" };
        for (String n : names) {
            players.add(new Player(n));
        }
    }

    private void dealCards() {
        // TODO
        deck.shuffleDeck();
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                player.addCard(deck.drawCard());
            }
        }
    }

    private void initializeDiscardPile() {
        Card firstCard = deck.drawCard();
        while (firstCard != null && firstCard.getValue().equals("WildDraw4")) {
            deck.addCard(firstCard);
            deck.shuffleDeck();
            firstCard = deck.drawCard();
        }
        if (firstCard != null) {
            discardPile.push(firstCard); // Only push once
            currentColor = firstCard.getColor();
        }
    }

    private void gameLoop() {
        while (!checkWinner()) {
            Player currentPlayer = players.get(currentPlayerIndex);
            Card topCard = discardPile.peek();

            Card chosenCard = currentPlayer.playTurn(topCard);

            if (chosenCard != null && isValidMove(chosenCard)) {
                System.out.println(currentPlayer.getName() + " played: " + chosenCard);
                discardPile.push(chosenCard);
                handleSpecialCard(chosenCard);
            } else if (chosenCard != null) {
                // Safety: If the player tried to play an illegal card,
                // give it back to them and make them draw as a penalty.
                currentPlayer.addCard(chosenCard);
                currentPlayer.addCard(safeDraw());
            } else {
                System.out.println(currentPlayer.getName() + " had to draw.");
                Card drawn = safeDraw();
                if (drawn != null)
                    currentPlayer.addCard(drawn);
            }

            nextTurn();
        }
    }

    private boolean isValidMove(Card cardToPlay) {
        Card topCard = discardPile.peek();

        return cardToPlay.getColor().equals(currentColor) || // Check declaring color
                cardToPlay.getValue().equals(topCard.getValue()) || // Check symbol/number
                cardToPlay.getColor().equals("Wild"); // Wilds are always valid
    }

    private void handleSpecialCard(Card card) {
        String value = card.getValue();

        switch (value) {
            case "Reverse" -> {
                if (players.size() == 2) {
                    nextTurn();
                } else {
                    isClockWise = !isClockWise;
                }
            }
            case "Skip" -> nextTurn();
            case "Draw2" -> {
                nextTurn();
                Player victim = players.get(currentPlayerIndex);
                for (int i = 0; i < 2; i++) {
                    victim.addCard(deck.drawCard());
                }
            }
            case "Wild" -> {
                currentColor = players.get(currentPlayerIndex).chooseColor();
            }
            case "WildDraw4" -> {
                nextTurn();

                currentColor = players.get(currentPlayerIndex).chooseColor();
                Player victim = players.get(currentPlayerIndex);
                for (int i = 0; i < 4; i++)
                    victim.addCard(safeDraw());
            }
        }
    }

    private void nextTurn() {
        // TODO
        int direction = isClockWise ? 1 : -1;
        currentPlayerIndex = (currentPlayerIndex + direction + players.size()) % players.size();
    }

    private boolean checkWinner() {
        // TODO
        for (Player p : players) {
            if (p.getHand().isEmpty()) {
                System.out.println(p.getName() + " has won the game!");
                return true;
            }
        }
        return false;
    }

    // Add this helper to your GameController to handle empty decks safely
    private Card safeDraw() {
        Card drawn = deck.drawCard();
        if (drawn == null) {
            // Safety: If deck is empty, move discard pile (minus top card) back to deck
            Card top = discardPile.pop();
            // Assuming your teammate provides a way to add cards back
            // For now, we'll just log it so you know where it failed
            System.out.println("Deck empty! Reshuffling needed.");
            discardPile.push(top);
            return null; // Or trigger a reshuffle method if it exists
        }
        return drawn;
    }
}
