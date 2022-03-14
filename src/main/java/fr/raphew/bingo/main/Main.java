package fr.raphew.bingo.main;

import fr.raphew.bingo.commands.CommandBingo;
import fr.raphew.bingo.commands.CommandParty;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This file of '[Minecraft Mineow] Bingo' was created by Raphew on 13/03/2022
 */
public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("bingo").setExecutor(new CommandBingo());
        getCommand("party").setExecutor(new CommandParty());
        console("ON");
    }

    @Override
    public void onDisable() {
        console("OFF");
    }

    private void console(String etat) {
        PluginDescriptionFile pdf = getDescription();
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[" + pdf.getName() + "] Etat : " + ChatColor.AQUA + etat  + ChatColor.RED + " | Version : " + ChatColor.AQUA + pdf.getVersion() + ChatColor.RED
                + " | Author : " + ChatColor.AQUA + pdf.getAuthors().toString().replace("[", "").replace("]", ""));
    }
}
