package com.tanderson.systems.rds;

import com.tanderson.systems.rds.interfaces.entries.ItemTableEntry;
import com.tanderson.systems.rds.interfaces.entries.TableEntry;

import java.util.ArrayList;

/**
 * Random Distribution System Table.
 * An abstract type that holds and controls drop table objects. Contains methods and attributes used to randomly pick
 * an object from a list of items with varying attributes and probabilities.
 */
abstract class RDSTable<T extends TableEntry, U> {

    private final ArrayList<RDSObject<T>> table;

    /**
     * Standard practice is for a table to attempt to have a total probability of 100.
     * This way, it is fairly easy to determine and calculate how likely any given entry
     * in the table is to be selected.
     * For example, an RDSObject with 1 probability would be chosen 1/100 rolls. 0.5, 1/200, and so on.
     */
    private double totalProbability;

    private final int totalDrops;
    private final RDSRandom rand;

    /**
     * Creates an RDSItemTable Object with an empty ArrayList and a chosen amount of different objects to drop.
     * I.E. totalDrops of 2 mean that 2 entries from the table will be selected. This could be the same entry,
     * assuming the object chosen has isUnique set to false.
     * @param totalDrops An Integer amount of individual drops to be chosen from the given table.
     */
    public RDSTable(int totalDrops) {
        this.totalDrops = totalDrops;
        table = new ArrayList<>();
        this.totalProbability = 0.0;
        this.rand = new RDSRandom();
    }

    /**
     * Creates an RDSItemTable Object with a given ArrayList of drops and a chosen amount of different objects to drop.
     * I.E. totalDrops of 2 mean that 2 entries from the table will be selected. This could be the same entry,
     * assuming the object chosen has isUnique set to false.
     * @param table An ArrayList of RDSObjects with associatedObjects of parent type TableEntry
     * @param totalDrops An Integer amount of individual drops to be chosen from the given table.
     */
    public RDSTable(ArrayList<RDSObject<T>> table, int totalDrops) {
        this.table = table;
        this.totalDrops = totalDrops;
        this.rand = new RDSRandom();

        for (RDSObject<T> obj : table) {
            this.totalProbability += obj.getProbability();
        }
    }

    public RDSTable(int totalDrops, int seed) {
        this.totalDrops = totalDrops;
        this.rand = new RDSRandom(seed);
        this.table = new ArrayList<>();
    }

    /**
     * Adds an RDSObject to the table ArrayList and returns a boolean stating if it was successful or not.
     * @param object An RDSObject with an associatedObject that extends or implements type TableEntry.
     * @return A boolean stating if the add operation was successful.
     */
    public boolean add(RDSObject<T> object) {
        this.totalProbability += object.getProbability();
        return table.add(object);
    }

    public void remove(int index){
        RDSObject<T> obj = table.get(index);
        this.table.remove(index);
        this.totalProbability -= obj.getProbability();
    }

    abstract U runTable();

    protected ArrayList<RDSObject<T>> getObjectsMarkedAlways() {
        ArrayList<RDSObject<T>> markedAlways = new ArrayList<>();

        for (RDSObject<T> obj : this.getTable()) {
            if(obj.dropsAlways() && obj.isEnabled()){
                markedAlways.add(obj);
            }
        }

        return markedAlways;
    }

    protected ArrayList<RDSObject<T>> filterOutAlwaysAndDisabled(){
        ArrayList<RDSObject<T>> notMarkedAlwaysAndEnabled = new ArrayList<>();

        for (RDSObject<T> obj : this.getTable()) {
            if(!obj.dropsAlways() && obj.isEnabled()){
                notMarkedAlwaysAndEnabled.add(obj);
            }
        }

        return notMarkedAlwaysAndEnabled;
    }

    public ArrayList<RDSObject<T>> getTable() {
        return table;
    }

    public double getTotalProbability() {
        return totalProbability;
    }

    public void setTotalProbability(double totalProbability) {
        this.totalProbability = totalProbability;
    }

    public int getTotalDrops() {
        return totalDrops;
    }

    protected RDSRandom getRand() {
        return rand;
    }
}
