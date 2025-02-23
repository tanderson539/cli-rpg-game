package rds;

public class RDSObject<T> implements IRDSObject{

    private double probability;
    private boolean dropsAlways;
    private boolean isUnique;
    private boolean isEnabled;

    private T associatedObject;

    public RDSObject(T associatedObject, double probability){
        this.probability = probability;
        this.dropsAlways = false;
        this.isUnique = false;
        this.isEnabled = true;

        this.associatedObject = associatedObject;
    }

    public RDSObject(T associatedObject, double probability, boolean dropsAlways, boolean isUnique, boolean isEnabled) {
        this.probability = probability;
        this.dropsAlways = dropsAlways;
        this.isUnique = isUnique;
        this.isEnabled = isEnabled;
        this.associatedObject = associatedObject;
    }

    public RDSObject(T associatedObject, double probability, int min, int max){
        this.associatedObject = associatedObject;
        this.probability = probability;
        this.dropsAlways = false;
        this.isUnique = false;
        this.isEnabled = true;
    }

    public RDSObject(T associatedObject, double probability, int min, int max, boolean dropsAlways, boolean isUnique, boolean isEnabled){
        this.associatedObject = associatedObject;
        this.probability = probability;
        this.dropsAlways = dropsAlways;
        this.isUnique = isUnique;
        this.isEnabled = isEnabled;
    }

    //Getters/Setters

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public boolean isUnique() {
        return isUnique;
    }

    @Override
    public boolean dropsAlways() {
        return dropsAlways;
    }

    @Override
    public double getProbability() {
        return probability;
    }

    @Override
    public void setProbability(double probability) {
        this.probability = probability;
    }

    @Override
    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Override
    public void setIsUnique(boolean isUnique) {
        this.isUnique = isUnique;
    }

    @Override
    public void setDropsAlways(boolean dropsAlways) {
        this.dropsAlways = dropsAlways;
    }

    public T getAssociatedObject() {
        return associatedObject;
    }
    public void setAssociatedObject(T associatedObject) {
        this.associatedObject = associatedObject;
    }
}
