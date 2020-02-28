package com.github.thinkverse.captcha.listeners;

import com.github.thinkverse.captcha.CaptchaGUI;
import com.github.thinkverse.captcha.inventorys.captcha.CaptchaInventory;
import com.github.thinkverse.captcha.managers.PlayerManager;
import com.github.thinkverse.captcha.utilities.Common;
import com.github.thinkverse.captcha.utilities.Messages;
import com.github.thinkverse.captcha.utilities.inventory.abstraction.InventoryItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {

    private final CaptchaGUI plugin;

    public InventoryListener(CaptchaGUI plugin) { this.plugin = plugin; }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        if (event.getView().getTopInventory().getHolder() instanceof CaptchaInventory) {
            event.setCancelled(true);

            if (event.getWhoClicked() instanceof Player) {
                final Player _player = (Player) event.getWhoClicked();
                final ItemStack _currentItem = event.getCurrentItem();

                if (_currentItem == null || _currentItem.getType() == Material.AIR) return;

                final CaptchaInventory _inventory = (CaptchaInventory) event.getView().getTopInventory().getHolder();

                InventoryItem _item = _inventory.getItem(event.getRawSlot());

                if (_item == null) return;

                _item.getActions().forEach(action -> action.execute(_player));
            }
        }
    }

    @EventHandler
    public void onInventoryClose(final InventoryCloseEvent event) {
        if (event.getView().getTopInventory().getHolder() instanceof CaptchaInventory) {
            final Player _player = (Player) event.getPlayer();

            if (!PlayerManager.getInstance().contains(_player.getUniqueId())) {
                _player.kickPlayer(Common.translate(plugin.getConfig().getString("inventory.on_close", Messages.INVENTORY_ON_CLOSE)));
            }
        }
    }

    public CaptchaGUI getPlugin() { return this.plugin; }

}
