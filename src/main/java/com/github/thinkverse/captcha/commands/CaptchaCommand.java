package com.github.thinkverse.captcha.commands;

import com.github.thinkverse.captcha.CaptchaGUI;
import com.github.thinkverse.captcha.utilities.Common;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class CaptchaCommand implements CommandExecutor, TabExecutor {
    private CaptchaGUI plugin;

    public CaptchaCommand(CaptchaGUI plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            final Player _player = (Player) sender;

            plugin.reloadConfig();
            Common.success(_player, "Configuration successfully reloaded.");

            return true;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Collections.emptyList();
    }

    public CaptchaGUI getPlugin() { return this.plugin; }

}
