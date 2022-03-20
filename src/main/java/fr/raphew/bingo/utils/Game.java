package fr.raphew.bingo.utils;

import fr.raphew.bingo.main.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.SimpleDateFormat;
import java.time.Duration;

/**
 * This file of '[Minecraft Mineow] Bingo' was created by Raphew on 15/03/2022
 */
public class Game {

    private Main main;
    private int timeGame = 300;

    public Game(Main main){
        this.main = main;
    }

    void game(){
        new BukkitRunnable(){
            @Override
            public void run() {
                Duration duration = Duration.ofSeconds(timeGame);
                long minutes = duration.toMinutes();
                long seconds = duration.minusMinutes(minutes).getSeconds();
                for(Player p : Bukkit.getOnlinePlayers()) {
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.AQUA + "" + String.format("%d:%02d", minutes, seconds)));
                }
                if(timeGame==0){
                    cancel();
                }
                timeGame--;
            }
        }.runTaskTimer(main, 0, 20);
    }

}
