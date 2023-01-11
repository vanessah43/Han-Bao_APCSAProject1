import java.util.Scanner;

public class Ceelo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        public void play () {
            // greeting
            System.out.println("Welcome to Ceelo!");
            System.out.println("The famous Cee-lo dice game featuring a Banker, three players, chips, and wagers!");
            System.out.println("You need three players to start.");
            System.out.println("Are you all here? [Y] [N]");
            String temp = scan.nextLine();
            if (temp.equals("N")) {
                System.out.println("Please gather everyone first.");
                System.out.println("Goodbye!");
                System.exit(0);
            }

            // creating Die objects
            Die die1 = new Die();
            Die die2 = new Die();
            Die die3 = new Die();

            // creating Player objects
            System.out.println("Alright! Let's get started.");
            System.out.println("Player 1, what is your name?");
            temp = scan.nextLine();
            Player player1 = new Player(temp);
            System.out.println("Welcome, " + temp + "!");

            System.out.println("Player 2, what is your name?");
            temp = scan.nextLine();
            Player player2 = new Player(temp);
            System.out.println("Welcome, " + temp + "!");

            System.out.println("Player 3, what is your name?");
            temp = scan.nextLine();
            Player player3 = new Player(temp);
            System.out.println("Welcome, " + temp + "!");

            // creating Banker object
            Banker bank = new Banker();

            // globalizing objects (LOL)
            bank.accessPlayers(player1, player2, player3);
            bank.accessDie(die1, die2, die3);

            player1.accessBanker(bank);
            player2.accessBanker(bank);
            player3.accessBanker(bank);

            player1.accessDie(die1, die2, die3);
            player2.accessDie(die1, die2, die3);
            player3.accessDie(die1, die2, die3);

            // begin game
            int round = 1;
            System.out.println("Let's play Ceelo!");
            System.out.println("Round " + round);

            int bet = 0;
            System.out.println("Players, make your bets!");
            System.out.println(player1.getName() + ", you have " + player1.getChips() + "chips.");
            System.out.print("How much are you betting? ");
            bet = scan.nextInt();
            player1.setWager(bet);
            System.out.println("Alright! You've wagered " + bet + " chips.");

            System.out.println(player2.getName() + ", you have " + player2.getChips() + "chips.");
            System.out.print("How much are you betting? ");
            bet = scan.nextInt();
            player1.setWager(bet);
            System.out.println("Alright! You've wagered " + bet + " chips.");

            System.out.println(player3.getName() + ", you have " + player3.getChips() + "chips.");
            System.out.print("How much are you betting? ");
            bet = scan.nextInt();
            player1.setWager(bet);
            System.out.println("Alright! You've wagered " + bet + " chips.");


            // banker rolls
            System.out.println("Let's roll! 🎲");
            bank.rollDie();

            // player turns
            // [player 1]
            System.out.println("[Player 1]");






        }


        // private helper methods
        private void playerTurn(Player play) {
            String choice = "";

            System.out.println(play.getName() + ", it's your turn!");
            System.out.println("What would you like to do?");
            System.out.println("------------------------");
            System.out.println("[A] I'm ready to roll!");
            System.out.println("[B] View Main Menu");
            System.out.println("[C] Check chip balance");
            System.out.println("------------------------");

            choice = scan.nextLine();
            if (choice.equals("A")) {
                System.out.println("Alright!");
                play.rollDie();
            }
            if (choice.equals("B")) {
                mainMenu();
            }
            if (choice.equals("B")) {

            }
        }

        private void mainMenu(Player play) {
            int choice;
            System.out.println("Main Menu");
            System.out.println("------------------------");
            System.out.println("[1] New game");
            System.out.println("[2] Quit");
            System.out.println("[3] Check chip balance");
            System.out.println("------------------------");
            choice = scan.nextInt();

            if (choice == 1) {
                System.out.print("Are you sure you'd like to restart? ");
                System.out.print("[Y] [N] ");

            }
            if (choice == 2) {

            }
            if (choice == 3) {

            }
        }

        // closing brackets
    }
}
