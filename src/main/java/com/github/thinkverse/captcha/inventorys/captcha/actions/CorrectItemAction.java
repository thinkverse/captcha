package com.github.thinkverse.captcha.inventorys.captcha.actions;

import com.github.thinkverse.captcha.managers.PlayerManager;
import com.github.thinkverse.captcha.utilities.inventory.interfaces.InventoryAction;
import org.bukkit.entity.Player;

public class CorrectItemAction implements InventoryAction {

    @Override
    public void execute(Player player) {
        PlayerManager.getInstance().set(player.getUniqueId());
    }

}
