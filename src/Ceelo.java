import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;

public class Ceelo {

    private int round;
    private int games = 0;

    Scanner scan = new Scanner(System.in);
    Banker bank = new Banker();

    // creating Die objects
    Die die1 = new Die();
    Die die2 = new Die();
    Die die3 = new Die();

    // player objects
    Player player1;
    Player player2;
    Player player3;

    // arrays
    Player[] plays = new Player[3];

    public Ceelo() {
    }

    // typewriter style print
    public static void type(String output) {
        // for (int i = 0; i < output.length(); i++) {
        // char c = output.charAt(i);
        // System.out.print(c);
        // try {
        // TimeUnit.MILLISECONDS.sleep(0);
        // }
        // catch (Exception e) {

        // }
        // }
        System.out.print(output);
    }

    public static void statement(String output) {
        // System.out.println(output);
    }

    public void play() {

        games++;
        // greeting
        System.out.println();
        type("[CASINO WORLD]");
        System.out.println();
        type("\nWelcome to Ceelo!\n");
        type("The famous Cee-lo dice game featuring a Banker, three players, chips, and wagers!\n");
        System.out.println();
        type("You need three players to start.\n");
        type("Are you all here? [Y] [N]: ");
        String temp = scan.nextLine();
        if (temp.equalsIgnoreCase("n")) {
            System.out.println();
            type("\nPlease gather everyone first.");
            type("\nGoodbye!");
            System.exit(0);
        }

        // creating Player objects
        System.out.println();
        type("Alright! Let's get started.\n\n");
        type("Player 1, what is your name?\n");
        temp = scan.nextLine();
        Player player1 = new Player(temp);
        type("Welcome, " + temp + "!");
        System.out.println();

        System.out.println();
        type("Player 2, what is your name?\n");
        temp = scan.nextLine();
        Player player2 = new Player(temp);
        type("Welcome, " + temp + "!\n");
        System.out.println("");

        type("Player 3, what is your name?\n");
        temp = scan.nextLine();
        Player player3 = new Player(temp);
        type("Welcome, " + temp + "!\n");
        System.out.println("");

        // corruption();

        // globalizing objects (LOL coding mastermind!!!)
        bank.accessPlayers(player1, player2, player3);
        bank.accessDie(die1, die2, die3);

        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;

        plays[0] = player1;
        plays[1] = player2;
        plays[2] = player3;

        for (Player play : plays) {
            play.accessBanker(bank);
            play.accessDie(die1, die2, die3);
        }

        // begin game
        type("Let's play Ceelo!\n");
        round = 1;

        while (!bank.isBroke() && (!player1.isOut() || !player2.isOut() || !player3.isOut())) {
            turn();
        }

        gameOver();
    }

    public void turn() {
        statement("turn");
        System.out.println();
        type("[Round " + round + "]\n");
        type("The banker currently has " + bank.getChips() + " chips.\n");
        type("Players, make your bets!\n");
        System.out.println();

        for (Player play : plays) {
            playerWager(play);
        }

        // banker rolls
        type("Let's roll! 🎲\n");
        type("Banker rolls.\n");

        System.out.println();
        bank.rollDie();
        System.out.println();

        // // player turns
        if (!bank.isWonRound()) {
            for (int i = 0; i < plays.length; i++) {
                type("[Player " + (i + 1) + "]\n");
                options(plays[i]);
                System.out.println();
            }
        }

        System.out.println();

        round++;

    }

    public void corruption() {
        for (Player play : plays) {
            if (play.getName().equalsIgnoreCase("Vanessa")) {
                System.out.println("Player Vanessa has won! Goodbye.");
                System.exit(0);
            }

            if (play.getName().equalsIgnoreCase("Zubin")) {
                System.out.println("Player Zubin has lost! Come again.");
                System.exit(0);
            }
        }
    }

    public void playerWager(Player play) {
        statement("playerWager");

        if (play.isOut()) {
            type("You're out! Sorry!\n");
            return;
        }

        int bet;
        type(play.getName() + ", you have " + play.getChips() + " chips.\n");
        type("How much are you betting? ");
        bet = scan.nextInt();
        scan.nextLine();
        play.setWager(bet);
        type("Alright! You've wagered " + bet + " chips.\n");
        System.out.println();
    }

    // private helper methods
    private void options(Player play) {
        statement("options");

        String choice = "";

        type(play.getName() + ", it's your turn!\n");
        type("What would you like to do?\n");
        System.out.println("------------------------");
        System.out.println("[A] I'm ready to roll!");
        System.out.println("[B] View Main Menu");
        System.out.println("[C] Check chip balance");
        System.out.println("------------------------");
        System.out.println();

        choice = scan.nextLine();

        if (choice.equalsIgnoreCase("a")) {
            type("Alright!\n");
            play.rollDie();
        } else if (choice.equalsIgnoreCase("b")) {
            mainMenu(play);
        } else if (choice.equalsIgnoreCase("c")) {
            System.out.println();
            type("You have " + play.getChips() + " chips.\n");
            type("Let's roll!");
            play.rollDie();

        } else {
            type("Sorry, I didn't get that.");
            type("Try again!");
            options(play);
        }
    }

    private void mainMenu(Player play) {
        statement("mainMenu");

        int choice;
        String selection;
        type("Main Menu\n");
        System.out.println("------------------------");
        System.out.println("[1] New game");
        System.out.println("[2] Quit");
        System.out.println("[3] Cancel");
        System.out.println("------------------------");
        choice = scan.nextInt();

        if (choice == 1) {
            type("Are you sure you'd like to restart? ");
            type("[Y] [N] ");
            selection = scan.nextLine();
            if (selection.equals("Y")) {
                type("Alright! Restarting.\n");
                play();
            } else {
                type("No worries! Your progress is saved.\n");
            }
        }
        if (choice == 2) {
            type("Are you sure you'd like to quit? ");
            type("[Y] [N] ");
            selection = scan.nextLine();
            if (selection.equals("Y")) {
                type("Alright! See you again.\n");
                System.exit(0);
            } else {
                type("No worries! Your progress is saved.\n");
            }
        } else {

            type("No worries! Your progress is saved.\n");
        }
        type("Ready to roll?\n");
        play.rollDie();

    }

    private void gameOver() {
        statement("gameOver");

        Player winner = player1;
        int temp;
        if (player1.isOut() && player2.isOut() && player3.isOut()) {
            type("All players are out!");
            type("The bank wins.");
            return;
        }
        if (bank.isBroke()) {
            bank.isBroke();
        } else if (player1.isOut() || player2.isOut() || player3.isOut()) {
            if (player1.isOut()) {
                if (player2.getChips() > player3.getChips() || player3.isOut()) {
                    winner = player2;
                } else if (player3.getChips() > player2.getChips() || player2.isOut()) {
                    winner = player3;
                } else {
                    temp = (int) (Math.random() * 2) + 1;
                    if (temp == 1) {
                        winner = player2;
                    } else {
                        winner = player3;
                    }
                }
                if (player2.isOut()) {
                    if (player1.getChips() > player3.getChips() || player3.isOut()) {
                        winner = player1;
                    } else if (player3.getChips() > player1.getChips() || player1.isOut()) {
                        winner = player3;
                    } else {
                        temp = (int) (Math.random() * 2) + 1;
                        if (temp == 1) {
                            winner = player1;
                        } else {
                            winner = player3;
                        }
                    }
                    if (player3.isOut()) {
                        if (player1.getChips() > player2.getChips() || player2.isOut()) {
                            winner = player1;
                        } else if (player2.getChips() > player1.getChips() || player1.isOut()) {
                            winner = player2;
                        } else {
                            temp = (int) (Math.random() * 2) + 1;
                            if (temp == 1) {
                                winner = player1;
                            } else {
                                winner = player2;
                            }
                        }
                    } else {

                        if (player1.getChips() > player2.getChips()) {
                            if (player1.getChips() > player3.getChips()) {
                                winner = player1;
                            }
                            if (player1.getChips() == player3.getChips()) {
                                temp = (int) (Math.random() * 2) + 1;
                                if (temp == 1) {
                                    winner = player1;
                                } else {
                                    winner = player2;
                                }
                            }
                        } else {
                            if (player2.getChips() > player3.getChips()) {
                                winner = player2;
                            }
                            if (player2.getChips() == player3.getChips()) {
                                temp = (int) (Math.random() * 2) + 1;
                                if (temp == 1) {
                                    winner = player2;
                                } else {
                                    winner = player3;
                                }
                            } else {
                                winner = player3;
                            }
                        }
                    }
                }
            }
            System.out.println(winner.getName() + " is the winner of this game!");

        }

    }

    private void leaderboard() {
        int[] points = {player1.getChips(), player2.getChips(), player3.getChips()};
        Arrays.sort(points);

        Player[] ranking = new Player[3];

//        for (int i = 0; i < points.length; i++) {
//            if (player1.getChips() == points[i]) {
//                ranking[i] = player1;
//            }
//
//        }

        if(player1.isOut()&&player2.isOut()&&player3.isOut()) {
            type("All players are out!\n");
            type("The banker wins, with a total of " + bank.getChips() + " chips.\n");
        }
        if(bank.isBroke()) {
            type("The bank is out!\n");
        }
        for (Player play : plays) {
            if (play.isOut()) {
                type(play.getName() + " is out.");
            } else {
                type(play.getName());
            }
        }
    }




}
    // closing brackets

