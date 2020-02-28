package com.github.thinkverse.captcha.managers;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class PlayerManager {
    private static volatile PlayerManager instance;

    private Set<UUID> players = new HashSet<>();

    private PlayerManager() { }

    public boolean contains(final UUID uuid) {
        return this.players.contains(uuid);
    }

    public void set(final UUID uuid) {
        this.players.add(uuid);
    }

    public void remove(final UUID uuid) {
        this.players.remove(uuid);
    }

    public void clear() {
        this.players.clear();
    }

    public static PlayerManager getInstance() {
        PlayerManager result = instance;
        if (result == null) {
            synchronized (PlayerManager.class) {
                result = instance;
                if (result == null) {
                    instance = result = new PlayerManager();
                }
            }
        }
        return instance;
    }
}