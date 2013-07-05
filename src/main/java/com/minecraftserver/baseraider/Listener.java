package com.minecraftserver.baseraider;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.Arrays;

public class Listener implements org.bukkit.event.Listener {

    static String DisplayName;

    @EventHandler
    public static void onPrepareItemcraftevent(PrepareItemCraftEvent event) {
        if(event.getRecipe() instanceof ShapedRecipe) {
            // get crafting result (DisplayName)
            String resultName = event.getInventory().getResult().getItemMeta().getDisplayName();
            // DisplayName=null -> normal enchant
            // if its not null -> special enchant
            if(resultName != null) {
                // get receipe of the item and check it
                String[] receipe = Recipes.receipes.get(resultName);
                // if receipe is null (not on the list), dont check it to
                // prevent crashing of
                // other plugins
                if(receipe != null) {
                    // create array of current workbench items
                    String[] currentreceipe = new String[9];
                    for(int i = 0; i < 9; i++) {
                        ItemStack itemInSlot = event.getInventory().getItem(i + 1);
                        if(itemInSlot != null) {
                            DisplayName = itemInSlot.getItemMeta().getDisplayName();
                        }
                        // add it to array if its null add empty ""
                        currentreceipe[i] = (DisplayName != null ? DisplayName : "");
                    }
                    // compare both array if they dont match cancel craft
                    if(!Arrays.equals(receipe, currentreceipe))
                        event.getInventory().setResult(new ItemStack(Material.AIR));
                }
            }
        }
    }

    BaseRaider parent;

    public Listener(BaseRaider plugin) {
        this.parent = plugin;
    }
}
