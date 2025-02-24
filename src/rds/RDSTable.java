package rds;

import items.ItemRecord;

import java.util.ArrayList;

/**
 * Random Distribution System Table.
 * A data type that holds and controls drop table objects. Contains methods and attributes used to randomly pick
 * an object from a list of items with varying attributes and probabilities.
 */
public class RDSTable implements SubTableTableEntry {

    private final ArrayList<RDSObject<? extends TableEntry>> table;
    private double totalProbability;
    private final int totalDrops;
    private final RDSRandom rand;

    public RDSTable(int totalDrops) {
        this.totalDrops = totalDrops;
        table = new ArrayList<>();
        this.totalProbability = 0.0;
        this.rand = new RDSRandom();
    }

    public RDSTable(ArrayList<RDSObject<? extends TableEntry>> table, int totalDrops) {
        this.table = table;
        this.totalDrops = totalDrops;
        this.rand = new RDSRandom();

        for (RDSObject<? extends TableEntry> obj : table) {
            this.totalProbability += obj.getProbability();
        }
    }

    public boolean add(RDSObject<? extends TableEntry> object) {
        this.totalProbability += object.getProbability();
        return table.add(object);
    }

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
            if (currItem.isItemDrop() && currItem instanceof RDSItemDrop drop) {
                dropArr[dropArrIdx] = drop.generateItemRecord(this.rand);
                dropArrIdx++;

            } else if (currItem.isTable() && currItem.getAssociatedObject() instanceof RDSTable drop) {
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

        System.out.println("Dropped Items:");
        for (ItemRecord item : dropArr) {
            if(item != null){
                System.out.println(item.getAmount() + "x " + item.getItem().getName());
            }
        }

        return dropArr;
    }

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
                if (obj.isItemDrop() && obj instanceof RDSItemDrop drop) {
                    item = drop.generateItemRecord(this.rand);
                    break;
                }else if(obj.isTable() && obj.getAssociatedObject() instanceof RDSTable drop){
                    //TODO: Make this support sub-tables returning more than 1 item.
                    item = drop.runTable()[0];
                    break;
                }else if(obj.isNull()){
                    System.out.println("Dropping nothing!");
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
