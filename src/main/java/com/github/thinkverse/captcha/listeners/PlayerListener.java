package com.github.thinkverse.captcha.listeners;

import com.github.thinkverse.captcha.CaptchaGUI;
import com.github.thinkverse.captcha.inventorys.captcha.CaptchaInventory;
import com.github.thinkverse.captcha.inventorys.captcha.actions.CloseInventoryAction;
import com.github.thinkverse.captcha.inventorys.captcha.actions.CorrectItemAction;
import com.github.thinkverse.captcha.managers.PlayerManager;
import com.github.thinkverse.captcha.utilities.Common;
import com.github.thinkverse.captcha.utilities.inventory.abstraction.InventoryItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;

public class PlayerListener implements Listener {

    private final CaptchaGUI plugin;

    public PlayerListener(CaptchaGUI plugin) {  this.plugin = plugin; }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        if (!event.getPlayer().hasPermission("captcha.bypass")) {
            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                final Player _player = event.getPlayer();
                final Random _random = new Random();

                final CaptchaInventory _inventory = new CaptchaInventory(_player.getUniqueId(), Common.translate("&8Captcha"), 54, true);

                InventoryItem _item = new InventoryItem(Material.STAINED_GLASS_PANE, 1, (short) 13);

                ItemMeta _meta = _item.getItemMeta();
                _meta.setDisplayName(Common.translate("&aEnter Server"));
                _item.setItemMeta(_meta);

                _item.addAction(new CorrectItemAction()).addAction(new CloseInventoryAction());

                _inventory.setItem(_random.nextInt(_inventory.getSize()), _item);

                _player.openInventory(_inventory.getInventory());
            }, 10L);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerQuit(final PlayerQuitEvent event) {
        PlayerManager.getInstance().remove(event.getPlayer().getUniqueId());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerChat(final AsyncPlayerChatEvent event) {
        if (!PlayerManager.getInstance().contains(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerMove(final PlayerMoveEvent event) {
        if (!PlayerManager.getInstance().contains(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        }
    }

    public CaptchaGUI getPlugin() { return this.plugin; }

}
