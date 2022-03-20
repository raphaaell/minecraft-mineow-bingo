package fr.raphew.bingo.utils;

import fr.raphew.bingo.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This file of '[Minecraft Mineow] Bingo' was created by Raphew on 14/03/2022
 */
public class Display {

    private Main main;
    private ArrayList<String> alreadyIn = new ArrayList<>();

    public Display(Main main){
        this.main = main;
    }

    public void showDisplay(Player player){
        Inventory inventory = createDisplay();
        setItems(inventory);
        player.openInventory(inventory);
    }

    private Inventory createDisplay(){
        return Bukkit.createInventory(null, 45, ChatColor.AQUA + "Bingo");
    }

    private void setItems(Inventory inventory){
        for (int i = 0; i < inventory.getSize(); i++) {
            if(i == 2 || i == 11 || i == 20 || i == 29 || i == 38){
                for (int j = i; j <= i+5; j++) {
                    if(j == 7 || j == 16 || j == 25 || j == 34 || j == 43){
                        break;
                    }else{
                        String item = generateItems().toUpperCase();
                        Material mat = Material.getMaterial(item);
                        System.out.println("item : " + item + " mat : " + mat);

                        inventory.setItem(j, getItem(mat, item, false));
                    }
                }
            }
        }
    }

    public ItemStack getItem(Material mat, String name, boolean enchant) {
        ItemStack it = new ItemStack(mat, 1);
        ItemMeta itM = it.getItemMeta();
        assert itM != null;
        itM.setDisplayName(name);
        if(enchant) {
            itM.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
            itM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        it.setItemMeta(itM);
        return it;
    }

    String generateItems(){
        List<String> items = main.getListFC().getStringList("items");
        Random random = new Random();
        boolean good = false;
        while(true){
            int i = random.nextInt(items.size()-1);
            String item = items.get(i);
            if(!alreadyIn.contains(item) && !banItems().contains(item)) {
                alreadyIn.add(item);
                return item;
            }
        }

    }

    List<String> banItems(){
        return main.getBanListFC().getStringList("ban-list");
    }


}
