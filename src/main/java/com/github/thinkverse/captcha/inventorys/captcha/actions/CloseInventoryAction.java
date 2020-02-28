package com.github.thinkverse.captcha.inventorys.captcha.actions;

import com.github.thinkverse.captcha.utilities.inventory.interfaces.InventoryAction;
import org.bukkit.entity.Player;

public class CloseInventoryAction implements InventoryAction {

    @Override
    public void execute(Player player) {
        player.closeInventory();
    }

}
