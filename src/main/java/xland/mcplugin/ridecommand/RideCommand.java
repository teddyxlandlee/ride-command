package xland.mcplugin.ridecommand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

import java.util.List;

// Thanks ChatGPT
final class RideCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length < 2) return false;
 
        Entity target = findEntity(sender, args[0], "target");  // $NLS-1$
        if (target == null) {
            return true;
        }
 
        if ("mount".equalsIgnoreCase(args[1])) {    // $NON-NLS-1$
            if (args.length < 4) {
                sender.sendMessage("Usage: /ride <target> mount <vehicle>");    // $NLS-1$
                return true;
            }
 
            Entity vehicle = findEntity(sender, args[3], "vehicle");    // $NLS-1$
            if (vehicle == null) {
                return true;
            }
 
            target.addPassenger(vehicle);
            sender.sendMessage(String.format("Mounted %s onto %s.", target.getName(), vehicle.getName()));  // $NLS-1$
        } else if ("dismount".equalsIgnoreCase(args[1])) {  // $NON-NLS-1$
            target.leaveVehicle();
            sender.sendMessage(String.format("Dismounted %s.", target.getName()));  // $NLS-1$
        } else {
            return false;
        }
 
        return true;
    }
 
    private static Entity findEntity(CommandSender sender, String selector, String tipName) {
        List<Entity> entities;
        try {
            entities = Bukkit.selectEntities(sender, selector);
        } catch (IllegalArgumentException e) {
            sender.sendMessage(String.format(ChatColor.RED + "Illegal selector: %s", selector));    // $NLS-1$
            return null;
        }
 
        if (entities.size() == 0) {
            sender.sendMessage(String.format(ChatColor.RED + "Invalid %s specified.", tipName));    // $NLS-1$
            return null;
        } else if (entities.size() > 1) {
            sender.sendMessage(String.format(ChatColor.RED + "Too many %ss specified.", tipName));  // $NLS-1$
            return null;
        }
 
        return entities.get(0);
    }
}