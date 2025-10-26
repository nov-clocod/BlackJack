package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        ArrayList<String> players = new ArrayList<>();
        ArrayList<Hand> hands = new ArrayList<>();

        //Prompt number of players
        System.out.println("How many players will be playing?");
        int numberOfPlayers = myScanner.nextInt();
        myScanner.nextLine();

        //Prompt player names and creating new hands for each player
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Player " + (i + 1) +", Enter your name: ");
            players.add(i, myScanner.nextLine().trim());
            hands.add(i, new Hand());
        }

        System.out.println();

        //Instantiating deck
        Deck deck = new Deck();

        //Randomize deck
        deck.shuffle();

        //Deal cards
        for (int i = 0; i < hands.size(); i++) {
            hands.get(i).deal(deck.deal());
            hands.get(i).deal(deck.deal());

            System.out.println("==============");
            System.out.println(players.get(i) + "'s");
            hands.get(i).print();
            while (hands.get(i).getValue() < 21) {
                System.out.println("Do you want to hit (enter 1) or stand (enter 2)?");
                int hitOrStand = myScanner.nextInt();
                myScanner.nextLine();

                if (hitOrStand == 1) {
                    hands.get(i).deal(deck.deal());
                    System.out.println("Current hand: ");
                    hands.get(i).print();
                } else if (hitOrStand == 2) {
                    System.out.println("You've stand");
                    break;
                } else {
                    System.out.println("That was an invalid choice");
                }
            }
        }

        System.out.println();

        //Display all hand values
        for (int i = 0; i < players.size(); i++) {
            System.out.println("==============");
            System.out.println(players.get(i) + ": Value is " + hands.get(i).getValue());
            System.out.println("==============");
        }

        //Compare cards with condition logic and displays the result
        compare(hands, players);
    }

    public static void compare(ArrayList<Hand> hands, ArrayList<String> players) {
        int highestPoint = 0;

        //Arraylist to store winner and who has a bust
        ArrayList<String> playersWithHighestPoint = new ArrayList<>();
        ArrayList<String> playersBusted = new ArrayList<>();

        //Sorting hands to determine a winner and who has a bust
        for (int i = 0; i < hands.size(); i++) {
            int currentHandValue = hands.get(i).getValue();
            if (currentHandValue <= 21) {
                if (currentHandValue > highestPoint) {
                    highestPoint = currentHandValue;
                    playersWithHighestPoint.clear();
                    playersWithHighestPoint.add(players.get(i));

                } else if (currentHandValue == highestPoint) {
                    playersWithHighestPoint.add(players.get(i));
                }
            } else {
                playersBusted.add(players.get(i));
            }
        }

        //Displaying either all bust, who won, or who drawn
        if (playersWithHighestPoint.isEmpty()) {
            System.out.println("\nAll players busted!");
        } else if (playersWithHighestPoint.size() == 1) {
            System.out.println();
            System.out.println(playersWithHighestPoint.get(0) + " is the winner!");
        } else {
            System.out.println();
            for (String playerDraw : playersWithHighestPoint) {
                System.out.println(playerDraw + " drawn");
            }
        }

        //Display players who bust
        System.out.println();
        for (String playerBust : playersBusted) {
            System.out.println(playerBust + " bust!");
        }
    }
}