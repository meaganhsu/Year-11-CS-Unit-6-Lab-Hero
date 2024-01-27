import java.util.Random;
public class Hero {
    private String name;
    private int hitPoints;
    public Hero(String name) {
        this.name = name;
        hitPoints = 100;
    }
    public String getName() {
        return name;
    }
    public int getHitPoints() {
        return hitPoints;
    }
    public String toString() {
        return "Hero{name='" + name + "', hitPoints=" + hitPoints + "}";
    }
    public void attack(Hero opponent) {
        Random rand = new Random();
        double x = rand.nextDouble(1);
        if (x < 0.5) opponent.hitPoints -= 10;
        else hitPoints -= 10;
    }
    public void senzuBean() {
        hitPoints = 100;
    }
    private void fightUntilTheDeathHelper(Hero opponent) {
        while(hitPoints > 0 && opponent.hitPoints > 0) attack(opponent);
    }
    public String fightUntilTheDeath(Hero opponent) {
        senzuBean();
        opponent.senzuBean();
        fightUntilTheDeathHelper(opponent);
        return opponent.name + ": " + opponent.hitPoints + "\t" + name + ": " + hitPoints;
    }
    private int[] nFightsToTheDeathHelper(Hero opponent, int n) {
        int[] wins = new int[2];                  // {opp, hero}
        for (int i = 0; i < n; i++) {
            fightUntilTheDeathHelper(opponent);
            if (hitPoints <= 0) wins[0]++;     // opp W
            else if (opponent.hitPoints <= 0) wins[1]++;       // hero W
            senzuBean();
            opponent.senzuBean();
        }
        return wins;
    }
    public String nFightsToTheDeath(Hero opponent, int n) {
        int[] wins;
        wins = nFightsToTheDeathHelper(opponent, n);
        String out = name + ": " + wins[1] + " wins\n" + opponent.name + ": " + wins[0] + " wins\n";

        if (wins[1] == wins[0]) out += "OMG! It was actually a draw!";
        else if (wins[1] > wins[0]) out += name + " wins!";
        else out += opponent.getName() + " wins!";

        return out;
    }
    public void dramaticFightToTheDeath(Hero opponent) throws InterruptedException {
        senzuBean();
        opponent.senzuBean();

        while (hitPoints > 0 && opponent.hitPoints > 0) {
            attack(opponent);
            Thread.sleep(1000);
            System.out.println(opponent.name + ": " + opponent.hitPoints + "\t" + name + ": " + hitPoints);
        }
        if (hitPoints == 0) System.out.println(name + " wins!");
        else if (opponent.hitPoints == 0) System.out.println(opponent.name + " wins!");
    }
}