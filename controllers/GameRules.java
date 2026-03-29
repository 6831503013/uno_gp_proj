package controllers;

import models.Card;
import models.Player;
import models.Deck;

public class GameRules {

    public static boolean isValidMove(Card playedCard, Card topCard) {

        //Checks the color, value, and if it's a wild card to determine if the move is valid
        if (playedCard.getColor().equals(topCard.getColor())
                || playedCard.getValue().equals(topCard.getValue())
                || playedCard.getColor().equals("Wild")
                || playedCard.getValue().equals("WildDraw4")) { 
            return true;
        }
        return false;
    }

    //Applies the effects of special cards such as Skip, Reverse, Draw2, Wild, and WildDraw4
    public static void applySpecialCard(Card card,GameController game, Deck deck) {

       
        switch (card.getValue()) {
            case "Skip":
                game.skipNextPlayer();
                break;

            case "Reverse":
                game.reverseDirection();
                break;

            case "Draw2":
                Player next = game.getNextPlayer();
                next.addCard(deck.drawCard());
                next.addCard(deck.drawCard());
                game.skipNextPlayer();
                break;

            case "Wild":
                game.changeColor();
                break;

            case "WildDraw4":
                Player nextPlayer = game.getNextPlayer();
                for (int i = 0; i < 4; i++) {
                    nextPlayer.addCard(deck.drawCard());
                }
                game.skipNextPlayer();
                game.changeColor();
                break;
        }
    }
}