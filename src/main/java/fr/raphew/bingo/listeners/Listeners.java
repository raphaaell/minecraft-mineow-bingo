package fr.raphew.bingo.listeners;

import fr.raphew.bingo.main.Main;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * This file of '[Minecraft Mineow] Bingo' was created by Raphew on 14/03/2022
 */
public class Listeners implements Listener {

    private Main main;

    public Listeners(Main main){
        this.main = main;
    }

    @EventHandler
    public void onTakingDamage(EntityDamageEvent event){

        if(event.getEntity().getType() == EntityType.PLAYER){
            Player player = (Player) event.getEntity();
            if(main.getTempFC().getBoolean("list." + player.getName() + ".invulnerable")){
                event.setCancelled(true);
            }

        }

    }

}
