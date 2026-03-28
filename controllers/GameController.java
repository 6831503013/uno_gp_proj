package controllers;

import java.util.*;
import models.*;

public class GameController {

    private Deck deck;
    private List<Player> players;
    private Stack<Card> discardPile;
    private int currentPlayerIndex;

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
        // TODO: Initialize players
    }

    private void dealCards() {
        // TODO
    }

    private void initializeDiscardPile() {
        // TODO
    }

    private void gameLoop() {
        // TODO: Main loop (iteration)
    }

    private void nextTurn() {
        // TODO
    }

    private boolean checkWinner() {
        // TODO
        return false;
    }
}
