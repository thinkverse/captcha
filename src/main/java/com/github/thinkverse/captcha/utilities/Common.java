package com.github.thinkverse.captcha.utilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public final class Common {

    private Common() { }

    protected static String INFO_PREFIX = "&8[&9&li&r&8] &7";
    protected static String SUCCESS_PREFIX = "&8[&2\u2714&8] &7";
    protected static String WARN_PREFIX = "&8[&6&l!&r&8] &6";
    protected static String ERROR_PREFIX = "&8[&4\u2715&8] &c";
    protected static String QUESTION_PREFIX = "&8[&a?&8] &7";

    public static CommandSender CONSOLE = Bukkit.getConsoleSender();

    public static void info(final CommandSender sender, final String... messages) {
        tell(INFO_PREFIX, sender, messages);
    }

    public static void success(final CommandSender sender, final String... messages) {
        tell(SUCCESS_PREFIX, sender, messages);
    }

    public static void warn(final CommandSender sender, final String... messages) {
        tell(WARN_PREFIX, sender, messages);
    }

    public static void error(final CommandSender sender, final String... messages) {
        tell(ERROR_PREFIX, sender, messages);
    }

    public static void question(final CommandSender sender, final String... messages) {
        tell(QUESTION_PREFIX, sender, messages);
    }

    public static void tell(final CommandSender sender, final String... messages) {
        Arrays.stream(messages).map(Common::translate).forEach(sender::sendMessage);
    }

    public static void tell(final String prefix, final CommandSender sender, final String... messages) {
        Arrays.stream(messages).map(Common::translate).forEach(message -> tell(sender, prefix + message));
    }

    public static void broadcast(final String... messages) {
        Arrays.stream(messages).map(Common::translate).forEach(Bukkit::broadcastMessage);
    }

    public static String translate(final String value) {
        return ChatColor.translateAlternateColorCodes('&', value);
    }

}
