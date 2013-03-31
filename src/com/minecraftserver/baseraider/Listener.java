package com.minecraftserver.baseraider;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class Listener implements org.bukkit.event.Listener {
    static String DisplayName;
    @EventHandler
    public static void onPrepareItemcraftevent(PrepareItemCraftEvent event) {
        if (event.getRecipe() instanceof ShapedRecipe) {
            // get crafting result (DisplayName)
            String resultName = event.getInventory().getResult().getItemMeta()
                    .getDisplayName();
            // DisplayName=null -> normal enchant
            // if its not null -> special enchant
            if (resultName != null) {
                // get receipe of the item and check it
                String[] receipe = Recipes.receipes.get(resultName);
                // create array of current workbench items
                String[] currentreceipe = new String[9];
                for (int i = 0; i < 9; i++) {
                    ItemStack itemInSlot = event.getInventory()
                            .getItem(i + 1);
                    if (itemInSlot != null){
                        DisplayName = itemInSlot.getItemMeta().getDisplayName();
                    }
                    // add it to array if its null add empty ""
                    currentreceipe[i] = (DisplayName != null ? DisplayName
                            : "");
                }
                // compare both array if they dont match cancel craft
                if (!Arrays.equals(receipe, currentreceipe))
                    event.getInventory().setResult(new ItemStack(Material.AIR));
            }
            // try {
            // // control recipe Obby killer
            // if (event.getInventory().getItem(2).getType() == Material.COAL
            // && event.getInventory().getItem(4).getType() == Material.COAL
            // && (!event.getInventory().getItem(1).getItemMeta()
            // .getDisplayName()
            // .equals(ChatColor.RESET + "Improved Gunpowder")
            // || !event
            // .getInventory()
            // .getItem(3)
            // .getItemMeta()
            // .getDisplayName()
            // .equals(ChatColor.RESET
            // + "Improved Gunpowder")
            // || !event
            // .getInventory()
            // .getItem(5)
            // .getItemMeta()
            // .getDisplayName()
            // .equals(ChatColor.RESET
            // + "Improved Gunpowder") || !event
            // .getInventory().getItem(7).getItemMeta()
            // .getDisplayName()
            // .equals(ChatColor.RESET + "Improved Gunpowder"))) {
            // event.getInventory().setResult(new ItemStack(Material.AIR));
            // }
            // // Improved Gunpowder
            // if (event.getInventory().getItem(2).getType() == Material.COAL
            // && event.getInventory().getItem(4).getType() == Material.COAL
            // && (!event
            // .getInventory()
            // .getItem(1)
            // .getItemMeta()
            // .getDisplayName()
            // .equals(ChatColor.RESET
            // + "Compressed Gunpowder")
            // || !event
            // .getInventory()
            // .getItem(3)
            // .getItemMeta()
            // .getDisplayName()
            // .equals(ChatColor.RESET
            // + "Compressed Gunpowder")
            // || !event
            // .getInventory()
            // .getItem(7)
            // .getItemMeta()
            // .getDisplayName()
            // .equals(ChatColor.RESET
            // + "Compressed Gunpowder") || !event
            // .getInventory()
            // .getItem(9)
            // .getItemMeta()
            // .getDisplayName()
            // .equals(ChatColor.RESET
            // + "Compressed Gunpowder")))
            // event.getInventory().setResult(new ItemStack(Material.AIR));
            //
            // // Water drainer Mk. II
            // if ((event.getInventory().getItem(1).getType() == Material.STRING
            // && event.getInventory().getItem(4).getType() == Material.STRING
            // && event.getInventory().getItem(7).getType() == Material.STRING
            // && event
            // .getInventory().getItem(6).getType() == Material.DIAMOND)
            // && (!event.getInventory().getItem(3).getItemMeta()
            // .getDisplayName()
            // .equals(ChatColor.RESET + "Obby killer")
            // || !event
            // .getInventory()
            // .getItem(9)
            // .getItemMeta()
            // .getDisplayName()
            // .equals(ChatColor.RESET + "Obby killer") || !event
            // .getInventory()
            // .getItem(5)
            // .getItemMeta()
            // .getDisplayName()
            // .equals(ChatColor.RESET + "Water drainer Mk. I"))) {
            // event.getInventory().setResult(new ItemStack(Material.AIR));
            // }
            // // Water drainer Mk. I
            // if ((event.getInventory().getItem(1).getType() == Material.STRING
            // && event.getInventory().getItem(4).getType() == Material.STRING
            // && event.getInventory().getItem(7).getType() == Material.STRING
            // && event
            // .getInventory().getItem(6).getType() == Material.DIAMOND)
            // && (!event.getInventory().getItem(3).getItemMeta()
            // .getDisplayName()
            // .equals(ChatColor.RESET + "Improved Gunpowder") || !event
            // .getInventory().getItem(9).getItemMeta()
            // .getDisplayName()
            // .equals(ChatColor.RESET + "Improved Gunpowder"))) {
            // event.getInventory().setResult(new ItemStack(Material.AIR));
            // }
            // } catch (Exception e) {
            // // go away error!
            // }
        }
    }

    BaseRaider parent;

    public Listener(BaseRaider plugin) {
        this.parent = plugin;
    }
}
