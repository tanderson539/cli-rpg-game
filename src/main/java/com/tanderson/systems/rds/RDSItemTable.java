package com.tanderson.systems.rds;

import com.tanderson.display.TextAreaAppender;
import com.tanderson.items.ItemRecord;
import com.tanderson.systems.rds.interfaces.entries.ItemTableEntry;
import com.tanderson.systems.rds.interfaces.entries.SubTableTableEntry;

import java.util.ArrayList;

public class RDSItemTable extends RDSTable<ItemTableEntry, ItemRecord[]> implements SubTableTableEntry {

    public RDSItemTable(int totalDrops) {
        super(totalDrops);
    }

    public RDSItemTable(ArrayList<RDSObject<ItemTableEntry>> table, int totalDrops) {
        super(table, totalDrops);
    }

    public RDSItemTable(int totalDrops, int seed) {
        super(totalDrops, seed);
    }


    /**
     * Uses table the ArrayList and attributes of its related RDSObjects to choose *totalDrops* amount of
     * RDSObjects and returns an ItemRecord array of the results.
     * @return an ItemRecord array of the results of size *totalDrops*.
     * TODO: Rewrite to have this return RDSObjects. Leave implementation of what associatedObject the table should return up to individual drop managers.
     * TODO: Write individual drop managers for items, enemies, or whatever else.
     */
    public ItemRecord[] runTable(){
        if (this.getTable().isEmpty()) {
            return null;
        }

        int totalDrops = this.getTotalDrops();
        int dropArrIdx = 0;

        ItemRecord[] dropArr = new ItemRecord[totalDrops];

        ArrayList<RDSObject<ItemTableEntry>> itemsMarkedAlways = this.getObjectsMarkedAlways();

        ArrayList<RDSObject<ItemTableEntry>> currTable = this.getTable();
        double totalProb = this.getTotalProbability();

        for (RDSObject<ItemTableEntry> currItem : itemsMarkedAlways) {
            if (currItem instanceof RDSItemDrop drop) {
                dropArr[dropArrIdx] = drop.generateItemRecord(this.getRand());
                dropArrIdx++;

            } else if (currItem.getAssociatedObject() instanceof RDSItemTable drop) {
                dropArr[dropArrIdx] = drop.runTable()[0];
                dropArrIdx++;
            }

            if (currItem.isUnique()) {
                currTable.remove(currItem);
                totalProb -= currItem.getProbability();
            }
        }

        currTable = this.filterOutAlwaysAndDisabled();

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

    private ItemRecord getNormalDrop(ArrayList<RDSObject<ItemTableEntry>> currTable, double probability) {
        double randHit = this.getRand().genDouble(probability);
        double currVal = 0.0;

        ItemRecord item = null;

        for (RDSObject<ItemTableEntry> obj : currTable) {
            currVal += obj.getProbability();
            if (currVal > randHit) {
                if(obj.isUnique()){
                    currTable.remove(obj);
                    probability -= obj.getProbability();
                }
                if (obj instanceof RDSItemDrop drop) {
                    item = drop.generateItemRecord(this.getRand());
                    break;
                } else if (obj.getAssociatedObject() instanceof RDSItemTable drop) {
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



}
