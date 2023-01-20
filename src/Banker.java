import java.util.Arrays;

public class Banker {
    private int chips;
    private int score;

    private Player player1;
    private Player player2;
    private Player player3;
    private Die die1;
    private Die die2;
    private Die die3;
    private boolean wonRound;


    public Banker() {
        chips = 1000;
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
    public void accessDie(Die die1, Die die2, Die die3) {
        statement("accessDie");
        this.die1 = die1;
        this.die2 = die2;
        this.die3 = die3;
    }
    public void accessPlayers(Player p1, Player p2, Player p3) {
        statement("accessPlayers");

        player1 = p1;
        player2 = p2;
        player3 = p3;
    }

    public boolean isWonRound() {
        return wonRound;
    }
    public int getChips() {
        return chips;
    }

    public void minusChips(int num) {
        chips -= num;
    }


    public void addChips(int num) {
        chips += num;
    }

    public int getScore() {
        return score;
    }

    public void rollDie() {
        statement("rollDie");

        wonRound = false;
        int roll1 = die1.roll();
        int roll2 = die2.roll();
        int roll3 = die3.roll();
        int[] rolls = {roll1, roll2, roll3};
        Arrays.sort(rolls);
        for(int val : rolls) {
            type(val + " ");
        }

        // triple
        if (rolls[0] == rolls[1] && rolls[1] == rolls[2]) {
            type("Triple! Banker wins. 🎰\n");
            winRound();
        }

        // 4, 5, 6
        else if (rolls[0] == 4 && rolls[1] == 5 && rolls[2] == 6) {
            type("456! Banker wins. ⚃ ⚄ ⚅\n");
            winRound();
        }

        // 1, 2, 3
        else if (rolls[0] == 1 && rolls[1] == 2 && rolls[2] == 3) {
            type("123! Banker loses.\n");
            loseRound();
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
            type("Double! Banker scores " + score + ".\n");

        }

        // all different
        else {
            type("Singles! Reroll.\n");
            rollDie();
        }
        System.out.println();
    }

    public void winRound() {
        statement("winRound");

        chips += player1.getWager();
        chips += player2.getWager();
        chips += player3.getWager();

        player1.minusWager();
        player2.minusWager();
        player3.minusWager();

        type("Banker has " + chips + " chips.\n");
        type(player1.getName() + " has " + player1.getChips() + " chips.\n");
        type(player2.getName() + " has " + player2.getChips() + " chips.\n");
        type(player3.getName() + " has " + player3.getChips() + " chips.\n");

        player1.setRollTurn(false);
        player2.setRollTurn(false);
        player3.setRollTurn(false);

        wonRound = true;
    }

    public void loseRound() {
        statement("loseRound");

        chips -= player1.getWager();
        chips -= player2.getWager();
        chips -= player3.getWager();

        player1.addWager();
        player2.addWager();
        player3.addWager();

        type("Banker has " + chips + " chips.\n");
        type(player1.getName() + " has " + player1.getChips() + " chips.\n");
        type(player2.getName() + " has " + player2.getChips() + " chips.\n");
        type(player3.getName() + " has " + player3.getChips() + " chips.\n");

        player1.setRollTurn(false);
        player2.setRollTurn(false);
        player3.setRollTurn(false);

        isBroke();
        wonRound = false;
    }

    public boolean isBroke() {
        statement("isBroke");

        if (chips <= 0) {
            type("Banker's broke! Game over.\n");
            return true;
        }
        return false;
    }



    // closing bracket
}
