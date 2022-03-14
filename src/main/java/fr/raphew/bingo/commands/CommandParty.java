package fr.raphew.bingo.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This file of '[Minecraft Mineow] Bingo' was created by Raphew on 13/03/2022
 */
public class CommandParty implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            if(args.length < 1){
                sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[ERROR] " + ChatColor.AQUA + "/party commands need arguments. Check /party help.");
                return false;
            }
            if(args[0].toString().equalsIgnoreCase("help")){
                sender.sendMessage(ChatColor.AQUA + "Here the help :");
                sender.sendMessage(ChatColor.YELLOW + "/party help" + ChatColor.AQUA + " : Commands for show the help");
                sender.sendMessage(ChatColor.YELLOW + "/party create" + ChatColor.AQUA + " : Commands for create a party");
                sender.sendMessage(ChatColor.YELLOW + "/party join [player]" + ChatColor.AQUA + " : Commands for create a party");
                sender.sendMessage(ChatColor.YELLOW + "/party invite [player]" + ChatColor.AQUA + " : Commands for create a party");
                sender.sendMessage(ChatColor.YELLOW + "/party remove [player]" + ChatColor.AQUA + " : Commands for create a party");

            }

        }
        return false;
    }
}
