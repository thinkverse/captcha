package com.github.thinkverse.captcha.inventorys.captcha;

import com.github.thinkverse.captcha.inventorys.captcha.actions.CloseInventoryAction;
import com.github.thinkverse.captcha.inventorys.captcha.actions.WrongItemAction;
import com.github.thinkverse.captcha.utilities.inventory.abstraction.GUIHolder;
import com.github.thinkverse.captcha.utilities.inventory.abstraction.InventoryItem;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class CaptchaInventory extends GUIHolder {

    public CaptchaInventory(UUID player, String title, int size) { super(player, title, size);  }

    public CaptchaInventory(UUID player, String title, int size, boolean hasFiller) {
        super(player, title, size, hasFiller);

        this.setFiller();
    }

    public void setFiller() {
        InventoryItem _item = new InventoryItem(Material.STAINED_GLASS_PANE, 1, (short) 14);

        ItemMeta _meta = _item.getItemMeta();
        _meta.setDisplayName(" ");
        _item.setItemMeta(_meta);

        _item.addAction(new WrongItemAction()).addAction(new CloseInventoryAction());

        super.setFiller(_item);
    }

}
