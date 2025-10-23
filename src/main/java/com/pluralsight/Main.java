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
            players.add(i, myScanner.nextLine());
            hands.add(i, new Hand());
        }

        //Instantiating deck
        Deck deck = new Deck();

        //Randomize deck
        deck.shuffle();

        //Deal cards


        //Display cards
        System.out.println("==============");
        System.out.println(player1 + "'s");
        hand1.print();
        System.out.println("==============");
        System.out.println(player2 + "'s");
        hand2.print();
        System.out.println("==============");

        int value1 = hand1.getValue();
        int value2 = hand2.getValue();

        System.out.println("==============");
        System.out.println(player1 + ": Value is "+ value1);
        System.out.println(player2 + ": Value is "+ value2);
        System.out.println("==============");

        //Compare cards with condition logic and displays the result
        System.out.println(compare(value1, value2, player1, player2));
    }

    public static String compare(int hand1, int hand2, String player1, String player2) {
        if (hand1 > 21) {
            return player1 + ": You went over. You lose";
        } else if (hand2 > 21) {
            return player2 + ": You went over. You lose";
        } else if (hand1 == hand2) {
            return "Draw";
        } else if (hand1 == 21) {
            return player1 + ": Win with a Blackjack";
        } else if (hand2 == 21) {
            return player2 + ": Win with a Blackjack";
        } else if (hand1 > hand2) {
            return player1 + ": You win";
        } else {
            return player2 + ": You win";
        }
    }
}