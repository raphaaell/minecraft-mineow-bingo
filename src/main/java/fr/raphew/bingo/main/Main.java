package fr.raphew.bingo.main;

import fr.raphew.bingo.commands.CommandBingo;
import fr.raphew.bingo.commands.CommandParty;
import fr.raphew.bingo.listeners.Listeners;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * This file of '[Minecraft Mineow] Bingo' was created by Raphew on 13/03/2022
 */
public class Main extends JavaPlugin {

    public File configF, tempF, listF, banlistF;
    private FileConfiguration configFC, tempFC, listFC, banlistFC;

    @Override
    public void onEnable() {
        getCommand("bingo").setExecutor(new CommandBingo(this));
        getCommand("party").setExecutor(new CommandParty());
        getServer().getPluginManager().registerEvents(new Listeners(this), this);
        createConfigC();
        createTempC();
        createListC();
        createBanListC();
        console("ON");
        System.out.println(listFC.getStringList("items"));

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

    private void createConfigC() {
        configF = new File(getDataFolder(), "config.yml");
        if(!configF.exists()) {
            configF.getParentFile().mkdirs();
            saveResource("config.yml", true);
        }

        configFC = new YamlConfiguration();

        try {
            configFC.load(configF);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void createTempC() {
        tempF = new File(getDataFolder(), "temp.yml");
        if(!tempF.exists()) {
            tempF.getParentFile().mkdirs();
            saveResource("temp.yml", true);
        }

        tempFC = new YamlConfiguration();

        try {
            tempFC.load(tempF);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void createListC() {
        listF = new File(getDataFolder(), "listItems.yml");
        if(!listF.exists()) {
            listF.getParentFile().mkdirs();
            saveResource("listItems.yml", true);
        }

        listFC = new YamlConfiguration();

        try {
            listFC.load(listF);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void createBanListC() {
        banlistF = new File(getDataFolder(), "banlist.yml");
        if(!banlistF.exists()) {
            banlistF.getParentFile().mkdirs();
            saveResource("banlist.yml", true);
        }

        banlistFC = new YamlConfiguration();

        try {
            banlistFC.load(banlistF);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfigFC() { return configFC; }
    public FileConfiguration getTempFC() { return tempFC; }
    public FileConfiguration getListFC() { return listFC; }
    public FileConfiguration getBanListFC() { return banlistFC; }

}
