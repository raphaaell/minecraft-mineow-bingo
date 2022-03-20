package fr.raphew.bingo.commands;

import fr.raphew.bingo.main.Main;
import fr.raphew.bingo.utils.Display;
import fr.raphew.bingo.utils.Teleport;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This file of '[Minecraft Mineow] Bingo' was created by Raphew on 13/03/2022
 */
public class CommandBingo implements CommandExecutor {

    private final Main main;
    private boolean start = false;
    private ArrayList<Player> playersInGame = new ArrayList<>();

    public CommandBingo(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length < 1) {
                player.sendMessage("no args");
                return false;
            }
            if (args[0].equalsIgnoreCase("start")) {
                System.out.println("-0 : " + playersInGame);
                playersInGame = new ArrayList<>();
                System.out.println("-a : " + playersInGame);
                for(Player p : playersInGame){
                    playersInGame.remove(p);
                }
                System.out.println("-b : " + playersInGame);
                playersInGame.addAll(Bukkit.getOnlinePlayers());
                System.out.println("-c : " + playersInGame);
                if(main.getTempFC().getInt("games.index") < 1){
                    int index = main.getTempFC().getInt("games.index");
                    index++;
                    main.getTempFC().set("games.index", index);
                    for(Player p : playersInGame){
                        main.getTempFC().set("games.game" + index + "." + p.getName() + ".inGame", true);
                    }
                    try { main.getTempFC().save(main.tempF); } catch (IOException e) { e.printStackTrace(); }
                }else{
                    for (Player p : playersInGame) {
                        System.out.println("a : " + playersInGame);
                        for (String gstr : main.getTempFC().getConfigurationSection("games").getKeys(false)) {
                            System.out.println("b : " + gstr);
                            if (!gstr.equalsIgnoreCase("index")) {
                                System.out.println("c");
                                for (String pstr : main.getTempFC().getConfigurationSection("games." + gstr).getKeys(false)) {
                                    System.out.println("d : " + pstr);
                                    System.out.println(p.getName());
                                    System.out.println("equals : " + p.getName().equalsIgnoreCase(pstr));
                                    System.out.println("equals : " + p.getName().equalsIgnoreCase("raphewwww"));
                                    if (!p.getName().equalsIgnoreCase(pstr)) {
                                        System.out.println("e");
                                        playersInGame.removeIf(pS -> pS.getName().equalsIgnoreCase(pstr));
                                        System.out.println("removing");
                                        int index = main.getTempFC().getInt("games.index");
                                        System.out.println("index1 : " + index);
                                        index++;
                                        System.out.println("index2 : " + index);
                                        main.getTempFC().set("games.index", index);
                                        System.out.println("set");
                                        for (Player pIG : playersInGame) {
                                            System.out.println("f");
                                            main.getTempFC().set("games.game" + index + "." + pIG.getName() + ".inGame", true);
                                            System.out.println("set");
                                        }
                                        try { main.getTempFC().save(main.tempF); } catch (IOException e) { e.printStackTrace(); }
                                        System.out.println("save");
                                    }
                                }
                            }
                        }
                    }
                }
                for(String gstr : main.getTempFC().getConfigurationSection("games").getKeys(false)){
                    if(!gstr.equalsIgnoreCase("index")){
                        for(String pstr : main.getTempFC().getConfigurationSection("games." + gstr).getKeys(false)){
                            System.out.println("in " + gstr + ", players : " + pstr);
                        }
                    }
                }
                if (playersInGame.toArray().length < 1) {
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[ERROR] " + ChatColor.BLUE + "Min 2 players for start the game !");
                    return false;
                }
                Teleport teleport = new Teleport(main);
                teleport.teleportPlayers(playersInGame);
            } else if (args[0].equalsIgnoreCase("display")) {
                /*
                if (!start) {
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[ERROR] " + ChatColor.AQUA + " The bingo don't start yet !");
                    return false;
                }

                 */
                Display display = new Display(main);
                display.showDisplay(player);
            } else if (args[0].equalsIgnoreCase("stop")) {
                if (!start) {
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[ERROR] " + ChatColor.AQUA + " The bingo don't start yet !");
                    return false;
                } else {
                    start = false;
                    player.sendMessage(ChatColor.AQUA + "The party stop");
                    return true;
                }
            }

        }

        return false;
    }

}
