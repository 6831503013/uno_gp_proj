package utils;

import java.util.ArrayList;
import java.util.List;
import models.Card;

public class DisplayHandler {

    public static void displayPlayerTurn(String playerName) {
        System.out.println("It's " + playerName + "'s turn!");
    }

    public static void declareWinner(String playerName) {
        System.out.println("Congratulations " + playerName + "! You win!");
    }

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

    // Render SINGLE card
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

    // Display functions for wild cards and special cards can be added here as
    // needed
    public static void displaySkipCard() {
        String RED = "\033[91m"; // bright red
        String RESET = "\033[0m";

        String skipArt = """
                  ███████╗██╗  ██╗██╗██████╗
                  ██╔════╝██║ ██╔╝██║██╔══██╗
                  ███████╗█████╔╝ ██║██████╔╝
                  ╚════██║██╔═██╗ ██║██╔═══╝
                  ███████║██║  ██╗██║██║
                  ╚══════╝╚═╝  ╚═╝╚═╝╚═╝

                        O
                       /

                     TURN LOST
                     You got SKIPPED!
                """;

        String[] lines = skipArt.split("\n");

        for (String line : lines) {
            System.out.println(RED + line + RESET);
            try {
                Thread.sleep(120);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void displayReverseCard() {
        String BLUE = "\033[94m"; // bright blue
        String RESET = "\033[0m";

        String reverseArt = """
                  ██████╗ ███████╗██╗   ██╗███████╗██████╗ ███████╗███████╗
                  ██╔══██╗██╔════╝██║   ██║██╔════╝██╔══██╗██╔════╝██╔════╝
                  ██████╔╝█████╗  ██║   ██║█████╗  ██████╔╝███████╗█████╗
                  ██╔══██╗██╔══╝  ╚██╗ ██╔╝██╔══╝  ██╔══██╗╚════██║██╔══╝
                  ██║  ██║███████╗ ╚████╔╝ ███████╗██║  ██║███████║███████╗
                  ╚═╝  ╚═╝╚══════╝  ╚═══╝  ╚══════╝╚═╝  ╚═╝╚══════╝╚══════╝

                        <<<<<<   >>>>>>
                         DIRECTION FLIP
                """;

        String[] lines = reverseArt.split("\\n");

        for (String line : lines) {
            System.out.println(BLUE + line + RESET);
            try {
                Thread.sleep(120);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void displayDraw2Card() {
        String YELLOW = "\033[93m"; // bright yellow
        String RESET = "\033[0m";

        String draw2Art = """
                  ██████╗ ██████╗  █████╗ ██╗    ██╗  ██████╗
                  ██╔══██╗██╔══██╗██╔══██╗██║    ██║ ██╔═══██╗
                  ██║  ██║██████╔╝███████║██║ █╗ ██║     ██╔╝
                  ██║  ██║██╔══██╗██╔══██║██║███╗██║  ██╔╝
                  ██████╔╝██║  ██║██║  ██║╚███╔███╔╝ ███████╗
                  ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝ ╚══╝╚══╝  ╚══════╝

                         ▌▌   +2   ▌▌
                         DRAW  TWO
                """;

        String[] lines = draw2Art.split("\\n");

        for (String line : lines) {
            System.out.println(YELLOW + line + RESET);
            try {
                Thread.sleep(120);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void displayWildCard() {
        String CYAN = "\033[96m";
        String RED = "\033[91m";
        String YELLOW = "\033[93m";
        String BLUE = "\033[34m";
        String GREEN = "\033[92m";
        String RESET = "\033[0m";

        String wildArt = """
                  ██╗    ██╗██╗██╗     ██████╗
                  ██║    ██║██║██║     ██╔══██╗
                  ██║ █╗ ██║██║██║     ██║  ██║
                  ██║███╗██║██║██║     ██║  ██║
                  ╚███╔███╔╝██║███████╗██████╔╝
                   ╚══╝╚══╝ ╚═╝╚══════╝╚═════╝

                       [R]  [Y]  [B]  [G]
                         CHOOSE A COLOR
                """;

        String[] lines = wildArt.split("\\n");

        for (String line : lines) {

            // Apply cyan to entire line first
            line = CYAN + line + RESET;

            // Then override specific parts with their own colors
            if (line.contains("[R]")) {
                line = line.replace("[R]", RED + "[R]" + CYAN)
                        .replace("[Y]", YELLOW + "[Y]" + CYAN)
                        .replace("[B]", BLUE + "[B]" + CYAN)
                        .replace("[G]", GREEN + "[G]" + CYAN);
            }

            System.out.println(line);

            try {
                Thread.sleep(120);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void displayWild4Card() {
        String PURPLE = "\033[95m";
        String RED = "\033[91m";
        String YELLOW = "\033[93m";
        String BLUE = "\033[94m";
        String GREEN = "\033[92m";
        String RESET = "\033[0m";

        String wild4Art = """
                  ██╗    ██╗██╗██╗     ██████╗
                  ██║    ██║██║██║     ██╔══██╗
                  ██║ █╗ ██║██║██║     ██║  ██║
                  ██║███╗██║██║██║     ██║  ██║
                  ╚███╔███╔╝██║███████╗██████╔╝
                   ╚══╝╚══╝ ╚═╝╚══════╝╚═════╝

                       [R]  [Y]  [B]  [G]

                            ██╗  ██╗
                            ██║  ██║
                            ███████║
                                 ██║
                                 ██║

                        >>>  +4  <<<
                       DRAW   FOUR
                """;

        String[] lines = wild4Art.split("\\n");

        for (String line : lines) {

            // Step 1: apply red base
            line = RED + line + RESET;

            // Step 2: multicolor for [R][Y][B][G]
            if (line.contains("[R]")) {
                line = line.replace("[R]", RED + "[R]" + PURPLE)
                        .replace("[Y]", YELLOW + "[Y]" + PURPLE)
                        .replace("[B]", BLUE + "[B]" + PURPLE)
                        .replace("[G]", GREEN + "[G]" + PURPLE);
            }

            // Step 3: highlight +4 and FOUR in red
            if (line.contains("+4")) {
                line = line.replace("+4", RED + "+4" + RED);
            }

            if (line.contains("FOUR")) {
                line = line.replace("FOUR", RED + "FOUR" + PURPLE);
            }

            System.out.println(line + RESET);

            try {
                Thread.sleep(120);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
