package com.github.thinkverse.captcha;

import com.github.thinkverse.captcha.commands.CaptchaCommand;
import com.github.thinkverse.captcha.listeners.InventoryListener;
import com.github.thinkverse.captcha.listeners.PlayerListener;
import com.github.thinkverse.captcha.managers.PlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CaptchaGUI extends JavaPlugin {

    @Override
    public void onEnable() {
        loadConfig();
        loadListeners();
        loadCommands();
    }

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }

    private void loadListeners() {
        getServer().getPluginManager().registerEvents(new InventoryListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }

    private void loadCommands() {
        final CaptchaCommand captchaCommand = new CaptchaCommand(this);

        getCommand("captcha").setTabCompleter(captchaCommand);
        getCommand("captcha").setExecutor(captchaCommand);
    }

    @Override
    public void onDisable() {
        PlayerManager.getInstance().clear();
    }
}
