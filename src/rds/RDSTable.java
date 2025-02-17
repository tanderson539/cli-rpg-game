package rds;

import items.DroppableItem;

import java.util.ArrayList;

public class RDSTable {

    private final ArrayList<RDSObject<?>> table;
    private double totalProbability;
    private final int totalDrops;
    private final RDSRandom rand;

    public RDSTable(int totalDrops) {
        this.totalDrops = totalDrops;
        table = new ArrayList<>();
        this.totalProbability = 0.0;
        this.rand = new RDSRandom();
    }

    public RDSTable(ArrayList<RDSObject<?>> table, int totalDrops) {
        this.table = table;
        this.totalDrops = totalDrops;
        this.rand = new RDSRandom();

        for (RDSObject<?> obj : table) {
            this.totalProbability += obj.getProbability();
        }
    }

    public boolean add(RDSObject<?> object) {
        this.totalProbability += object.getProbability();
        return table.add(object);
    }

    public DroppableItem[] runTable(){
        if (table.isEmpty()) {
            return null;
        }

        System.out.println("running table...");

        int totalDrops = this.totalDrops;
        int dropArrIdx = 0;

        DroppableItem[] dropArr = new DroppableItem[totalDrops];

        ArrayList<RDSObject<?>> itemsMarkedAlways = this.getObjectsMarkedAlways();

        for(int i = 0; i < itemsMarkedAlways.size(); i++){
            if(itemsMarkedAlways.get(i).getAssociatedObject() instanceof DroppableItem){
                dropArr[i] = (DroppableItem) itemsMarkedAlways.get(i).getAssociatedObject();
            }
        }

        totalDrops -= itemsMarkedAlways.size();
        dropArrIdx += itemsMarkedAlways.size();

        if(totalDrops > 0){
            for(int i = 0; i < totalDrops; i++){
                dropArr[dropArrIdx] = getNormalDrop();
                dropArrIdx++;
            }
        }

        return dropArr;
    }

    private ArrayList<RDSObject<?>> getObjectsMarkedAlways() {
        ArrayList<RDSObject<?>> markedAlways = new ArrayList<>();

        for (RDSObject<?> obj : table) {
            if(obj.dropsAlways() && obj.isEnabled()){
                markedAlways.add(obj);
            }
        }

        return markedAlways;
    }

    private DroppableItem getNormalDrop() {
        double randHit = this.rand.genDouble(this.totalProbability);
        double currVal = 0.0;

        DroppableItem item = null;

        for (RDSObject<?> rdsObject : this.table) {
            currVal += rdsObject.getProbability();

            if (currVal > randHit) {
                if (rdsObject.getAssociatedObject() instanceof DroppableItem) {
                    item = (DroppableItem) rdsObject.getAssociatedObject();
                    break;
                }
            }
        }

        return item;
    }

    private ArrayList<RDSObject<?>> filterOutAlwaysAndDisabled(){
        ArrayList<RDSObject<?>> notMarkedAlwaysAndEnabled = new ArrayList<>();

        for (RDSObject<?> obj : table) {
            if(!obj.dropsAlways() || obj.isEnabled()){
                notMarkedAlwaysAndEnabled.add(obj);
            }
        }

        return notMarkedAlwaysAndEnabled;
    }

    public double getTotalProbability() {
        return totalProbability;
    }
    public int getTotalDrops() {
        return totalDrops;
    }
}
