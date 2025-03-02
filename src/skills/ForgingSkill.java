package skills;

public class ForgingSkill extends Skill {
    public ForgingSkill() {
        super("Mining");
    }

    public ForgingSkill(int totalXp) {
        super("Forging", 99, totalXp);
    }

    public void printForgingLevel(){
        System.out.println("Mining Level: " + this.getCurrentLevel());
        System.out.println("Total XP: " + this.getTotalXp());
        System.out.println("XP to next level: " + this.getXpToNextLevel());
    }
}
