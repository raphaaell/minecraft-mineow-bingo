package fr.raphew.bingo.utils;

import fr.raphew.bingo.main.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * This file of '[Minecraft Mineow] Bingo' was created by Raphew on 14/03/2022
 */
public class Teleport {

    private Main main;
    private int time;
    private int timeInvulnerable;
    private boolean compteurStart = false;
    private boolean alreadyInvulnerable = false;
    private boolean starting = false;

    public Teleport(Main main){
        this.main = main;
    }

    public void teleportPlayers(ArrayList<Player> players){
        time = main.getConfigFC().getInt("spawn.time");
        // Start the countdown
        new BukkitRunnable(){
            @Override
            public void run() {
                if(time < 1){
                    for(Player p : players){
                        p.sendMessage(ChatColor.AQUA + "Starting...");
                        p.setLevel(0);
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.AQUA + "Starting..."));

                    }
                    compteurStart = true;
                    cancel();
                }else{
                    for(Player p : players){
                        p.sendMessage(ChatColor.AQUA + "Start in " + ChatColor.RED + time + ChatColor.AQUA + " seconds !");
                        p.setExp(0);
                        p.setLevel(time);
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.0f);
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.AQUA + "Start in " + ChatColor.RED + time + ChatColor.AQUA + " seconds !"));
                    }
                }
                time--;
            }
        }.runTaskTimer(main, 0, 20);
        // Teleport player
        new BukkitRunnable(){
            @Override
            public void run() {
                if(compteurStart){
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
                    starting = true;
                    cancel();
                }
            }
        }.runTaskTimer(main, 0, 20);
        timeInvulnerable = main.getConfigFC().getInt("spawn.timeInvulnerable");
        // Made player invulnerable
        new BukkitRunnable(){
            @Override
            public void run() {
                if(compteurStart){
                    if(!alreadyInvulnerable){
                        if(timeInvulnerable >= 1){
                            for(Player player : players){
                                main.getTempFC().set("list." + player.getName() + ".invulnerable", true);
                            }
                        }else{
                            for(Player player : players){
                                main.getTempFC().set("list." + player.getName() + ".invulnerable", null);
                            }
                            cancel();
                        }
                        timeInvulnerable--;
                    }
                }
            }
        }.runTaskTimer(main, 0, 20);
        // Launch game function
        new BukkitRunnable(){
            @Override
            public void run() {
                if(starting){
                    Game game = new Game(main);
                    game.game();
                    cancel();
                }
            }
        }.runTaskTimer(main, 0, 20);

    }

    Points randomXandZ(){
        double RADIUS = main.getConfigFC().getDouble("spawn.radius");
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

