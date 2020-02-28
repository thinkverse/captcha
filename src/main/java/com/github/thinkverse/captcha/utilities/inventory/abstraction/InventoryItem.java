package com.github.thinkverse.captcha.utilities.inventory.abstraction;

import com.github.thinkverse.captcha.utilities.inventory.interfaces.InventoryAction;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public final class InventoryItem extends ItemStack {
    private final List<InventoryAction> actions = new ArrayList<>();

    public InventoryItem(final Material type) { super(type); }

    public InventoryItem(final Material type, final int amount) { super(type, amount); }

    public InventoryItem(final Material type, final int amount, final short data) { super(type, amount, data); }

    public List<InventoryAction> getActions() { return this.actions; }

    public InventoryItem addAction(final InventoryAction action) {
        this.actions.add(action);

        return this;
    }

}
