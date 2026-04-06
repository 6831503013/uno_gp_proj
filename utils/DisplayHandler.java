package utils;

import java.util.ArrayList;
import java.util.List;
import models.Card;

public class DisplayHandler {

    public static void displayCard(int index, Card card) {
        System.out.println(index + ": " + card.getColor() + " " + card.getValue());
    }

    // ANSI color codes
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String BLUE = "\u001B[34m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";

    // Get ANSI color from card color
    private static String getColorCode(String color) {
        switch (color.toUpperCase()) {
            case "RED":
                return RED;
            case "BLUE":
                return BLUE;
            case "GREEN":
                return GREEN;
            case "YELLOW":
                return YELLOW;
            default:
                return RESET;
        }
    }

    //Render SINGLE card
    public static List<String> renderCard(Card card) {
        List<String> lines = new ArrayList<>();

        String colorCode = getColorCode(card.getColor());
        String value = card.getValue();

        String border = colorCode + ".-------." + RESET;

        lines.add(border);
        lines.add(colorCode + "|       |" + RESET);
        lines.add(colorCode + String.format("|   %-3s |", value) + RESET);
        lines.add(colorCode + "|       |" + RESET);
        lines.add(border);

        return lines;
    }

    public static void renderTopCard(Card card) {
        System.out.println("Top Card:");
        List<String> cardLines = renderCard(card);
        for (String line : cardLines) {
            System.out.println(line);
        }

    }

    // Display ALL cards (side by side)
    public static void displayHand(List<Card> hand) {
        if (hand == null || hand.isEmpty()) {
            System.out.println("No cards.");
            return;
        }

        System.out.println("Your hand:");

        List<List<String>> renderedCards = new ArrayList<>();

        for (Card card : hand) {
            renderedCards.add(renderCard(card));
        }

        int height = renderedCards.get(0).size();

        for (int i = 0; i < height; i++) {
            for (List<String> cardLines : renderedCards) {
                System.out.print(cardLines.get(i) + "  ");
            }
            System.out.println();
        }
    }

    public static void displayWinner(String playerName) {
        System.out.println("Congratulations " + playerName + "! You win!");
    }
}
