package com.github.thinkverse.captcha.utilities.inventory.abstraction;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public abstract class GUIHolder implements InventoryHolder {
    private final Map<Integer, InventoryItem> items = new HashMap<>();

    private InventoryItem filler = null;
    private boolean hasFiller = false;

    private final String title;
    private final UUID player;
    private final int size;

    public GUIHolder(UUID player, String title, int size) {
        this.player = player;
        this.title = title;
        this.size = size;
    }

    public GUIHolder(UUID player, String title, int size, boolean hasFiller) {
        this.hasFiller = hasFiller;
        this.player = player;
        this.title = title;
        this.size = size;
    }

    public void setItem(final int position, final InventoryItem item) {
        this.items.put(position, item);
    }

    public void setFiller(final InventoryItem filler) { this.filler = filler; }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.player);
    }

    public int getSize() { return this.size; }

    public InventoryItem getItem(final int position) {
        return this.items.get(position);
    }

    public InventoryItem getFiller() {
        InventoryItem _default = new InventoryItem(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta _meta = _default.getItemMeta();
        _meta.setDisplayName(" ");
        _default.setItemMeta(_meta);

        return Optional.ofNullable(filler).orElse(_default);
    }

    @Override
    public Inventory getInventory() {
        Inventory _inventory = Bukkit.createInventory(this, this.size, this.title);

        if (hasFiller) {
            for (int i = 0; i < getSize(); i++) {
                if (items.get(i) == null) items.put(i, getFiller());
            }
        }

        items.forEach(_inventory::setItem);

        return _inventory;
    }
}
