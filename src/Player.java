import java.util.Arrays;

public class Player {
    // priv instance vars
    int chips;
    int wager;
    int score;
    String name;
    boolean rollTurn;
    boolean out;
    Banker bank;
    Die die1;
    Die die2;
    Die die3;

    // constructor
    public Player(String name) {
        this.name = name;
        chips = 100;
    }

    // setters & getters
    public String getName() {
        return name;
    }

    public int getChips() {
        return chips;
    }

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

    public void minusWager() {
        chips -= wager;
        wager = 0;

        // checks if player is out
        outOfChips();
    }

    public void addWager(){
        chips += wager;
        wager = 0;
    }

    public void accessBanker (Banker bank) {
        this.bank = bank;
    }

    public void accessDie (Die die1, Die die2, Die die3) {
        this.die1 = die1;
        this.die2 = die2;
        this.die3 = die3;
    }

    // public methods
    public void rollDie(){
        int roll1 = die1.roll();
        int roll2 = die2.roll();
        int roll3 = die3.roll();
        int[] rolls = {roll1, roll2, roll3};
        Arrays.sort(rolls);
        for (int val : rolls) {
            System.out.print(val + " ");
        }

        // triple
        if (rolls[0] == rolls[1] && rolls[1] == rolls[2]) {
            System.out.print("Triple! " + name + " receives " + wager + " chips from the Banker.");
            chips += wager;
            bank.minusChips(wager);
            wager = 0;
        }

        // 4, 5, 6
        else if (rolls[0] == 4 && rolls[1] == 5 && rolls[2] == 6) {
            System.out.print("456! " + name + " receives " + wager + " chips from the Banker.");
            chips += wager;
            bank.minusChips(wager);
            wager = 0;
        }

        // 1, 2, 3
        else if (rolls[0] == 1 && rolls[1] == 2 && rolls[2] == 3) {
            System.out.print("123! " + name + " loses " + wager + " chips to the Banker.");
            chips -= wager;
            bank.addChips(wager);
            wager = 0;
        }

        // 2 are the same
        else if (rolls[0] == rolls[1] || rolls [1] == rolls[2] || rolls[0] == rolls[2]) {
            if (rolls[0] == rolls[1]) {
                score = rolls[2];
            } else if (rolls[1] == rolls[2]) {
                score = rolls[0];
            } else if (rolls[0] == rolls[2]) {
                score = rolls[1];
            }

            System.out.print("Double! Player scores " + score);
            if (score < bank.getScore()) {
                System.out.print("Banker wins! Banker gets " + wager + " chips.");
                chips -= wager;
                bank.addChips(wager);
                outOfChips();
            } else {
                System.out.print("You win! " + name + " gets " + wager + " chips.");
                chips += wager;
                bank.minusChips(wager);
            }

            wager = 0;
        }

        // all different
        else {
            System.out.println("Singles! Reroll.");
            rollDie();
        }
    }


    // private helpers
    private boolean outOfChips() {
        if (chips >= 0) {
            System.out.println(name + " has bankrupted and is out!");
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
