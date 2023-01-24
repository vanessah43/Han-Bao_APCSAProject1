import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;

public class Ceelo {

    // private instance methods
    private int round;
    private int games = 0;

    // creating scanner / die objects
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

    // array of players (streamlines redundant method use)
    Player[] plays = new Player[3];

    // constructor
    public Ceelo() {
        games = 0;
        round = 1;
    }

    // godforsaken typewriter style print
    public static void type(String output) {
         for (int i = 0; i < output.length(); i++) {
         char c = output.charAt(i);
         System.out.print(c);
         try {
         TimeUnit.MILLISECONDS.sleep(30);
         }
         catch (Exception e) {

         }
         }
//        System.out.print(output);
    }

    // method used during testing
    public static void statement(String output) {
        // System.out.println(output);
    }

    // main logic of the game
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

        // if the first game, creates new players
        if (games == 1) {
            createPlayers();
        }

        // begin game
        type("Let's play Ceelo!\n");

        // set players' chips
        player1.setChips(100);
        player2.setChips(100);
        player3.setChips(100);

        // allows for rounds to continue given:
        // 1. bank is not broke
        // 2. at least one player is in
        while (!bank.isBroke() && (!player1.isOut() || !player2.isOut() || !player3.isOut())) {
            turn();
        }

        // ends game + shows methods
        gameOver();
        System.out.println();
        mainMenu();
    }

    // each turn/round of the game
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
        if (!bank.isWonRound() && !bank.isBroke()) {
            for (int i = 0; i < plays.length; i++) {
                type("[Player " + (i + 1) + "]\n");
                options(plays[i]);
                System.out.println();
            }
        }

        System.out.println();

        round++;

    }

    // silly method between alex zubin and i; just ignore this! :-) :3
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

    // method for each player to set a wager before the roll tunrs
    public void playerWager(Player play) {
        statement("playerWager");

        if (play.isOut()) {
            type( play.getName() + ": You're out! Sorry!\n\n");
            return;
        }

        int bet;
        type(play.getName() + ", you have " + play.getChips() + " chips.\n");
        type("How much are you betting? ");
        bet = scan.nextInt();

        if (bet > play.getChips()) {
            type("Sorry, you don't have enough chips. Try again!\n\n");
            playerWager(play);
        } else if (bet < 0) {
            type("Sorry, you can't do that. Try again!\n\n");
            playerWager(play);
        } else {
            scan.nextLine();
            play.setWager(bet);
            type("Alright! You've wagered " + bet + " chips.\n");
            System.out.println();
        }
    }

    // private helper methods
    // creates player objects
    private void createPlayers() {
        String temp = "";
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
    }

    // options given to players during their roll
    private void options(Player play) {
        statement("options");

        String choice = "";

        if (play.isOut()) {
            type(play.getName() + ": Sorry, you're out!\n");
        } else {
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
                mainMenu();
                type("Let's roll!\n");
                play.rollDie();
            } else if (choice.equalsIgnoreCase("c")) {
                System.out.println();
                type("You have " + play.getChips() + " chips.\n");
                type("Let's roll!\n");
                play.rollDie();

            } else {
                type("Sorry, I didn't get that.\n");
                type("Try again!\n");
                options(play);
            }
        }
    }

    // main menu that can be viewed + shown at end of game
    private void mainMenu() {
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
        scan.nextLine();

        if (choice == 1) {
            type("Are you sure you'd like to continue? ");
            type("[Y] [N] ");
            selection = scan.nextLine();
            if (selection.equalsIgnoreCase("Y")) {
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
            if (selection.equalsIgnoreCase("Y")) {
                type("Alright! See you again.\n");
                System.exit(0);
            } else {
                type("No worries! Your progress is saved.\n");
            }
        } else {

            type("No worries! Your progress is saved.\n");
        }
        type("Ready to roll?\n");
    }

    // method used at end of game; allows players to select options
    private void gameOver() {
        statement("gameOver");

        Player winner = player1;
        int temp;
        if (player1.isOut() && player2.isOut() && player3.isOut()) {
            type("All players are out!\n");
            type("The bank wins.\n");
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
            updateStats(winner);
            System.out.println();

        }

    }

    // updates each player's stats
    private void updateStats(Player winner) {
        for (Player play : plays) {
            play.addTotalChips(play.getChips());
            if (play == winner) {
                play.addWins();
            }
        }
    }

    // prints total chips (across all rounds) a player has
    private void printTotalChips() {
        for (Player play : plays) {
            type(play.getName() + " has " + play.getTotalChips() + " total chips.");
        }
    }

    // method that i couldnt get to fully work but did pretty well
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
                type(play.getName() + " has " + play.getChips() + " chips.");
            }
        }
    }




}
    // closing brackets

