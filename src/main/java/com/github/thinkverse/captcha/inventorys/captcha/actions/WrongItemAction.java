package com.github.thinkverse.captcha.inventorys.captcha.actions;

import com.github.thinkverse.captcha.CaptchaGUI;
import com.github.thinkverse.captcha.utilities.Common;
import com.github.thinkverse.captcha.utilities.Messages;
import com.github.thinkverse.captcha.utilities.inventory.interfaces.InventoryAction;
import org.bukkit.entity.Player;

public class WrongItemAction implements InventoryAction {
    private CaptchaGUI plugin = CaptchaGUI.getPlugin(CaptchaGUI.class);

    @Override
    public void execute(Player player) {
        player.kickPlayer(Common.translate(plugin.getConfig().getString("inventory.on_wrong_item", Messages.INVENTORY_ON_WRONG_ITEM)));
    }

}
