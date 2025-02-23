package skills;

public class WoodcuttingSkill extends Skill{
    public WoodcuttingSkill() {
        super("Woodcutting");
    }

    public WoodcuttingSkill(int totalXp) {
        super("Woodcutting", 99, totalXp);
    }

    public void printMiningLevel(){
        System.out.println("Woodcutting Level: " + this.getCurrentLevel());
        System.out.println("Total XP: " + this.getTotalXp());
        System.out.println("XP to next level: " + this.getXpToNextLevel());
    }
}
