package com.tanderson.systems.rds;

import com.tanderson.display.TextAreaAppender;
import com.tanderson.items.ItemRecord;

import java.util.ArrayList;

/**
 * Random Distribution System Table.
 * A data type that holds and controls drop table objects. Contains methods and attributes used to randomly pick
 * an object from a list of items with varying attributes and probabilities.
 */
public class RDSTable implements SubTableTableEntry {

    private final ArrayList<RDSObject<? extends TableEntry>> table;

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
     * Creates an RDSTable Object with an empty ArrayList and a chosen amount of different objects to drop.
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
     * Creates an RDSTable Object with a given ArrayList of drops and a chosen amount of different objects to drop.
     * I.E. totalDrops of 2 mean that 2 entries from the table will be selected. This could be the same entry,
     * assuming the object chosen has isUnique set to false.
     * @param table An ArrayList of RDSObjects with associatedObjects of parent type TableEntry
     * @param totalDrops An Integer amount of individual drops to be chosen from the given table.
     */
    public RDSTable(ArrayList<RDSObject<? extends TableEntry>> table, int totalDrops) {
        this.table = table;
        this.totalDrops = totalDrops;
        this.rand = new RDSRandom();

        for (RDSObject<? extends TableEntry> obj : table) {
            this.totalProbability += obj.getProbability();
        }
    }

    /**
     * Adds an RDSObject to the table ArrayList and returns a boolean stating if it was successful or not.
     * @param object An RDSObject with an associatedObject that extends or implements type TableEntry.
     * @return A boolean stating if the add operation was successful.
     */
    public boolean add(RDSObject<? extends TableEntry> object) {
        this.totalProbability += object.getProbability();
        return table.add(object);
    }

    /**
     * Uses table the ArrayList and attributes of its related RDSObjects to choose *totalDrops* amount of
     * RDSObjects and returns an ItemRecord array of the results.
     * @return an ItemRecord array of the results of size *totalDrops*.
     * TODO: Rewrite to have this return RDSObjects. Leave implementation of what associatedObject the table should return up to individual drop managers.
     * TODO: Write individual drop managers for items, enemies, or whatever else.
     */
    public ItemRecord[] runTable(){
        if (table.isEmpty()) {
            return null;
        }

        int totalDrops = this.totalDrops;
        int dropArrIdx = 0;

        ItemRecord[] dropArr = new ItemRecord[totalDrops];

        ArrayList<RDSObject<? extends TableEntry>> itemsMarkedAlways = this.getObjectsMarkedAlways();

        ArrayList<RDSObject<? extends TableEntry>> currTable = this.table;
        double totalProb = this.totalProbability;

        for (RDSObject<? extends TableEntry> currItem : itemsMarkedAlways) {
            if (currItem instanceof RDSItemDrop drop) {
                dropArr[dropArrIdx] = drop.generateItemRecord(this.rand);
                dropArrIdx++;

            } else if (currItem.getAssociatedObject() instanceof RDSTable drop) {
                dropArr[dropArrIdx] = drop.runTable()[0];
                dropArrIdx++;
            }

            if (currItem.isUnique()) {
                currTable.remove(currItem);
                totalProb -= currItem.getProbability();
            }
        }

        currTable = filterOutAlwaysAndDisabled();

        totalDrops -= dropArrIdx;

        if(totalDrops > 0){
            for(int i = 0; i < totalDrops; i++){
                dropArr[dropArrIdx] = getNormalDrop(currTable, totalProb);

                dropArrIdx++;
            }
        }

        TextAreaAppender.appendln("Dropped Items:");
        for (ItemRecord item : dropArr) {
            if(item != null){
                TextAreaAppender.appendln(item.getAmount() + "x " + item.getItem().getName());
            }
        }

        return dropArr;
    }

    /**
     *
     * @return
     */
    private ArrayList<RDSObject<? extends TableEntry>> getObjectsMarkedAlways() {
        ArrayList<RDSObject<? extends TableEntry>> markedAlways = new ArrayList<>();

        for (RDSObject<? extends TableEntry> obj : table) {
            if(obj.dropsAlways() && obj.isEnabled()){
                markedAlways.add(obj);
            }
        }

        return markedAlways;
    }

    private ItemRecord getNormalDrop(ArrayList<RDSObject<? extends TableEntry>> currTable, double probability) {
        double randHit = this.rand.genDouble(probability);
        double currVal = 0.0;

        ItemRecord item = null;

        for (RDSObject<? extends TableEntry> obj : currTable) {
            currVal += obj.getProbability();
            if (currVal > randHit) {
                if(obj.isUnique()){
                    currTable.remove(obj);
                    probability -= obj.getProbability();
                }
                if (obj instanceof RDSItemDrop drop) {
                    item = drop.generateItemRecord(this.rand);
                    break;
                } else if (obj.getAssociatedObject() instanceof RDSTable drop) {
                    //TODO: Make this support sub-tables returning more than 1 item.
                    item = drop.runTable()[0];
                    break;
                } else if (obj.isNull()) {
                    TextAreaAppender.appendln("Dropping nothing!");
                    break;
                }
            }
        }

        return item;
    }

    private ArrayList<RDSObject<? extends TableEntry>> filterOutAlwaysAndDisabled(){
        ArrayList<RDSObject<? extends TableEntry>> notMarkedAlwaysAndEnabled = new ArrayList<>();

        for (RDSObject<? extends TableEntry> obj : table) {
            if(!obj.dropsAlways() && obj.isEnabled()){
                notMarkedAlwaysAndEnabled.add(obj);
            }
        }

        return notMarkedAlwaysAndEnabled;
    }

    public void removeFromTable(int index){
        RDSObject<? extends TableEntry> obj = table.get(index);
        this.table.remove(index);
        this.totalProbability -= obj.getProbability();
    }

    public double getTotalProbability() {
        return totalProbability;
    }
    public int getTotalDrops() {
        return totalDrops;
    }
}
