package fr.raphew.bingo.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Random;

/**
 * This file of '[Minecraft Mineow] Bingo' was created by Raphew on 13/03/2022
 */
public class CommandBingo implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            if(args.length < 1){
                sender.sendMessage("no args");
                return false;
            }
            if(args[0].toString().equalsIgnoreCase("start")){
                Collection<? extends Player> playersInGame = Bukkit.getOnlinePlayers();
                if(playersInGame.toArray().length < 1){
                    sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[ERROR]" + ChatColor.BLUE + "Min 2 players for start the game !");
                    return false;
                }
                teleportPlayers(playersInGame);

            }


        }


        return false;
    }

    void teleportPlayers(Collection<? extends Player> players){
        for(Player player : players){
            Points points = randomXandZ();
            int x = points.x;
            int z = points.z;
            World world = player.getWorld();
            Block y = world.getHighestBlockAt(x,z);
            while(y.getType().name().equalsIgnoreCase("LAVA")){
                x++;
                y = world.getHighestBlockAt(x,z);
            }
            Location loc = new Location(world, x+0.5, y.getY()+1.5, z+0.5);
            player.teleport(loc);
        }
    }

    Points randomXandZ(){
        double RADIUS = 5000;
        Random random = new Random();
        double a = random.nextInt(10000);
        double angle = (double) a * 360;

        return new Points(Math.cos(angle) * RADIUS, Math.sin(angle) * RADIUS);
    }
}

class Points{
    int x;
    int z;

    Points(double x, double z){
        this.x = (int) x;
        this.z = (int) z;
    }
}
