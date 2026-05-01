package com.tanderson.items.wood;

import com.tanderson.items.Item;
import com.tanderson.items.RegisteredItem;
import com.tanderson.systems.rds.interfaces.entries.ItemTableEntry;

@RegisteredItem(id = "branch_tree")
public class TreeBranch extends Item implements ItemTableEntry {

    public TreeBranch() {
        super("Tree Branch", true);
        this.setDescription("A thin, firm branch from a tree. It could probably be used as the handle for at tool.");
    }
}
