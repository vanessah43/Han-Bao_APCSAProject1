import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Player {
    // priv instance vars
    private int wins;
    private int losses;
    private int totalChips;
    private int chips;
    private int wager;
    private int score;
    final private String name;
    private boolean rollTurn;
    private boolean out;
    private Banker bank;
    private Die die1;
    private Die die2;
    private Die die3;

    // constructor
    public Player(String name) {
        this.name = name;
        chips = 100;
        wins = 0;
        losses = 0;
        totalChips = 0;
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

    public static void statement(String output) {
       // System.out.println(output);
    }

    // setters & getters
    public void addTotalChips(int chips) {
        totalChips += chips;
    }

    public int getTotalChips() {
        return totalChips;
    }
    public void addWins() {
        wins++;
    }
    public void addLosses() {
        losses++;
    }
    public String getName() {
        return name;
    }

    public int getChips() {
        return chips;
    }
    public void setChips(int chips) {this.chips = chips;}


    public int getScore() {
        return score;
    }
    public void setRollTurn(boolean turn){
        rollTurn = turn;
    }

    public int getWager() {
        return wager;
    }

    public void setWager(int wager) {
        this.wager = wager;
    }

    public boolean isOut() {
        return out;
    }

    // subtractrs wager value from player's chips
    public void minusWager() {
        statement("minusWager");
        chips -= wager;
        // wager = 0;

        // checks if player is out
        outOfChips();
    }

    // adds wager value to player's chips
    public void addWager(){
        chips += wager;
        // wager = 0;
    }

    // allows player to access banker & die objects
    public void accessBanker (Banker bank) {
        this.bank = bank;
    }

    public void accessDie (Die die1, Die die2, Die die3) {
        this.die1 = die1;
        this.die2 = die2;
        this.die3 = die3;
    }

    // public methods
    // player's roll turn
    public void rollDie() {
        statement("rollDIe");

        if (out) {
            type("You're out! Sorry!\n");
            return;
        } else {

            int roll1 = die1.roll();
            int roll2 = die2.roll();
            int roll3 = die3.roll();
            int[] rolls = {roll1, roll2, roll3};
            Arrays.sort(rolls);
            for (int val : rolls) {
                type(val + " ");
            }

            // triple
            if (rolls[0] == rolls[1] && rolls[1] == rolls[2]) {
                type("Triple! " + name + " receives " + wager + " chips from the Banker.\n");
                chips += wager;
                bank.minusChips(wager);
                //wager = 0;
                type(name + " now has " + chips + " chips.\n");

            }

            // 4, 5, 6
            else if (rolls[0] == 4 && rolls[1] == 5 && rolls[2] == 6) {
                type("456! " + name + " receives " + wager + " chips from the Banker.\n");
                chips += wager;
                bank.minusChips(wager);
                // wager = 0;
                type(name + " now has " + chips + " chips.");

            }

            // 1, 2, 3
            else if (rolls[0] == 1 && rolls[1] == 2 && rolls[2] == 3) {
                type("123! " + name + " loses " + wager + " chips to the Banker.\n");
                chips -= wager;
                bank.addChips(wager);
                // wager = 0;
                outOfChips();
                type(name + " now has " + chips + " chips.\n");

            }

            // 2 are the same
            else if (rolls[0] == rolls[1] || rolls[1] == rolls[2] || rolls[0] == rolls[2]) {
                if (rolls[0] == rolls[1]) {
                    score = rolls[2];
                } else if (rolls[1] == rolls[2]) {
                    score = rolls[0];
                } else if (rolls[0] == rolls[2]) {
                    score = rolls[1];
                }

                type("Double! Player scores " + score + ".\n");
                if (score < bank.getScore()) {
                    type("Banker wins! Banker gets " + wager + " chips.\n");
                    chips -= wager;
                    bank.addChips(wager);
                    outOfChips();
                } else {
                    type("You win! " + name + " gets " + wager + " chips.\n");
                    chips += wager;
                    bank.minusChips(wager);
                }
                type(name + " now has " + chips + " chips.\n");

                // wager = 0;
            }

            // all different
            else {
                type("Singles! Reroll.\n");
                rollDie();
            }
            wager = 0;
        }
    }


    // private helpers
    private boolean outOfChips() {
        statement("outOfCHips");

        if (chips <= 0) {
            type(name + " has bankrupted and is out!\n");
            out = true;
            return true;
        }
        return false;
    }

    private void reset() {
        chips = 0;
    }


    // closing bracket
}
