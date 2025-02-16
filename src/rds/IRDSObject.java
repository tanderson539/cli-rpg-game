package rds;

public interface IRDSObject {
    boolean isEnabled();
    boolean isUnique();
    boolean dropsAlways();
    double getProbability();

    void setProbability(double probability);
    void setIsEnabled(boolean isEnabled);
    void setIsUnique(boolean isUnique);
    void setDropsAlways(boolean dropsAlways);

    @Override
    String toString();
}
