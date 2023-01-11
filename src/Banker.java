import java.util.Arrays;

public class Banker {
    int chips;
    int score;

    Player player1;
    Player player2;
    Player player3;
    Die die1;
    Die die2;
    Die die3;

    public Banker() {
        chips = 1000;
    }


    public void accessDie(Die die1, Die die2, Die die3) {
        this.die1 = die1;
        this.die2 = die2;
        this.die3 = die3;
    }
    public void accessPlayers(Player p1, Player p2, Player p3) {
        player1 = p1;
        player2 = p2;
        player3 = p3;
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
        int roll1 = die1.roll();
        int roll2 = die2.roll();
        int roll3 = die3.roll();
        int[] rolls = {roll1, roll2, roll3};
        Arrays.sort(rolls);
        for(int val : rolls) {
            System.out.print(val + " ");
        }

        // triple
        if (rolls[1] == rolls[2] && rolls[2] == rolls[3]) {
            System.out.print("Triple! Banker wins. 🎰");
            winRound();
        }

        // 4, 5, 6
        else if (rolls[1] == 4 && rolls[2] == 5 && rolls[3] == 6) {
            System.out.print("456! Banker wins. ⚃ ⚄ ⚅");
            winRound();
        }

        // 1, 2, 3
        else if (rolls[1] == 1 && rolls[2] == 2 && rolls[3] == 3) {
            System.out.print("123! Banker loses.");
            loseRound();
        }

        // 2 are the same
        else if (rolls[1] == rolls[2] || rolls [2] == rolls[3] || rolls[1] == rolls[3]) {
            if (rolls[1] == rolls[2]) {
                score = rolls[3];
            } else if (rolls[2] == rolls[3]) {
                score = rolls[1];
            } else if (rolls[1] == rolls[3]) {
                score = rolls[2];
            }
            System.out.print("Double! Banker scores " + score);

        }

        // all different
        else {
            System.out.println("Singles! Reroll.");
            rollDie();
        }
    }

    public void winRound() {
        chips += player1.getWager();
        chips += player2.getWager();
        chips += player3.getWager();

        player1.minusWager();
        player2.minusWager();
        player3.minusWager();

        System.out.println("Banker has " + chips + " chips.");
        System.out.println(player1.getName() + " has " + player1.getChips() + " chips.");
        System.out.println(player2.getName() + " has " + player2.getChips() + " chips.");
        System.out.println(player3.getName() + " has " + player3.getChips() + " chips.");

        player1.setRollTurn(false);
        player2.setRollTurn(false);
        player3.setRollTurn(false);
    }

    public void loseRound() {
        chips -= player1.getWager();
        chips -= player2.getWager();
        chips -= player3.getWager();

        player1.addWager();
        player2.addWager();
        player3.addWager();

        System.out.println("Banker has " + chips + " chips.");
        System.out.println(player1.getName() + " has " + player1.getChips() + " chips.");
        System.out.println(player2.getName() + " has " + player2.getChips() + " chips.");
        System.out.println(player3.getName() + " has " + player3.getChips() + " chips.");

        player1.setRollTurn(false);
        player2.setRollTurn(false);
        player3.setRollTurn(false);

        isBroke();
    }

    public boolean isBroke() {
        if (chips >= 0) {
            System.out.println("Banker's broke! Game over.");
            return true;
        }
        return false;
    }



    // closing bracket
}
