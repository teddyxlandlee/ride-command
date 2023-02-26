package xland.mcplugin.ridecommand;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class RideCommandPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(getCommand("ride")).setExecutor(new RideCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
