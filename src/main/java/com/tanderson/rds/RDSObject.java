package com.tanderson.rds;

import com.tanderson.items.Item;

/**
 * Random Distribution System Object
 * An RDSObject is a datatype used to add a drop entry to an RDSTable. An RDSObject can support items, null values, other RDSTables,
 * and anything else that can be represented as an entry to a drop table.
 * The probability is cumulative with everything in an RDS table. Meaning, a table with 2 items,
 * both with a probability of 1, means both items have a 50% chance of being selected.
 * @param <T> Must be an Object that implements TableEntry or its inheritors.
 * @see RDSTable
 */

public class RDSObject<T extends TableEntry> implements IRDSObject{

    private double probability;
    private boolean dropsAlways;
    private boolean isUnique;
    private boolean isEnabled;

    private T associatedObject;

    /**
     *
     * @param associatedObject The object that can be chosen from a drop table. For example, an Item or an RDSTable.
     * @param probability The probability, in double form, of this object being chosen by a table.
     */
    public RDSObject(T associatedObject, double probability){
        this.probability = probability;
        this.dropsAlways = false;
        this.isUnique = false;
        this.isEnabled = true;

        this.associatedObject = associatedObject;
    }

    /**
     *
     * @param associatedObject The object that can be chosen from a drop table. For example, an Item or an RDSTable.
     * @param probability The probability, in double form, of this object being chosen by a table.
     * @param dropsAlways A boolean representing if the item should always drop.
     * @param isUnique A boolean representing if the item can only drop once per table roll.
     * @param isEnabled A boolean representing if the item can be chosen by a table roll.
     */
    public RDSObject(T associatedObject, double probability, boolean dropsAlways, boolean isUnique, boolean isEnabled) {
        this.probability = probability;
        this.dropsAlways = dropsAlways;
        this.isUnique = isUnique;
        this.isEnabled = isEnabled;
        this.associatedObject = associatedObject;
    }

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

    public TableEntry getAssociatedObject() {
        return associatedObject;
    }
    public void setAssociatedObject(T associatedObject) {
        this.associatedObject = associatedObject;
    }

    /**
     *
     * @return A boolean representing if the associatedObject is of type Item.
     */
    public boolean isItem(){
        return this.associatedObject instanceof Item;
    }

    /**
     *
     * @return A boolean representing if the associatedObject is null. Used in RDSTables to drop nothing.
     */
    public boolean isNull(){
        return this.associatedObject == null;
    }
}
