package com.minecraftserver.baseraider;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class Recipes {
    public void addRecipes() {
        ArrayList<String> lore = new ArrayList<String>();
        final ItemStack arrow = new ItemStack(Material.ARROW, 1);
        ItemMeta im = arrow.getItemMeta();
        lore.add("1");
        lore.add("2");
        lore.add("3");
        lore.add("4");
        lore.add("5");
        lore.add("6");
        im.setDisplayName("Arrow of doom");
        im.setLore(lore);
        arrow.setItemMeta(im);
        ShapedRecipe DArrow = new ShapedRecipe(arrow);
        DArrow.shape("  D", " S ", "F  ");
        DArrow.setIngredient('D', Material.DIAMOND);
        DArrow.setIngredient('S', Material.STICK);
        DArrow.setIngredient('F', Material.FEATHER);
        Bukkit.getServer().addRecipe(DArrow);

        ArrayList<String> lore1 = new ArrayList<String>();
        ItemStack is1 = new ItemStack(Material.SULPHUR, 1);
        ItemMeta im1 = is1.getItemMeta();
        im1.setDisplayName(ChatColor.RESET + "Compressed Gunpowder");
        is1.setItemMeta(im1);
        ShapedRecipe recipe1a = new ShapedRecipe(is1);
        recipe1a.shape(new String[] { "   ", "AAA", "   " });
        recipe1a.setIngredient('A', Material.SULPHUR);
        Bukkit.getServer().addRecipe(recipe1a);
        ShapedRecipe recipe1b = new ShapedRecipe(is1);
        recipe1b.shape(new String[] { " A ", " A ", " A " });
        recipe1b.setIngredient('A', Material.SULPHUR);
        Bukkit.getServer().addRecipe(recipe1b);

        ArrayList<String> lore2 = new ArrayList<String>();
        ItemStack is2 = new ItemStack(Material.SULPHUR, 1);
        ItemMeta im2 = is2.getItemMeta();
        im2.setDisplayName(ChatColor.RESET + "Improved Gunpowder");
        is2.setItemMeta(im2);
        ShapedRecipe recipe2 = new ShapedRecipe(is2);
        recipe2.shape(new String[] { "ABA", "BCB", "ABA" });
        recipe2.setIngredient('A', is1.getType());
        recipe2.setIngredient('B', Material.COAL);
        recipe2.setIngredient('C', Material.FIREBALL);
        Bukkit.getServer().addRecipe(recipe2);

        ArrayList<String> lore3 = new ArrayList<String>();
        ItemStack is3 = new ItemStack(Material.FIREBALL, 1);
        ItemMeta im3 = is3.getItemMeta();
        im3.setDisplayName(ChatColor.RESET + "Obby killer");
        is3.setItemMeta(im3);
        ShapedRecipe recipe3 = new ShapedRecipe(is3);
        recipe3.shape(new String[] { "BAB", "ACA", "BAB" });
        recipe3.setIngredient('A', is2.getType());
        recipe3.setIngredient('B', Material.COAL);
        recipe3.setIngredient('C', Material.FIREBALL);
        Bukkit.getServer().addRecipe(recipe3);

        ArrayList<String> lore4 = new ArrayList<String>();
        lore4.add(ChatColor.RESET + "" + ChatColor.GRAY + "Has only 1 use!");
        lore4.add(ChatColor.RESET + "" + ChatColor.GRAY + "Drains flowing water");
        lore4.add(ChatColor.RESET + "" + ChatColor.GRAY + "Radius: 4");
        ItemStack is4 = new ItemStack(Material.BOW, 1);
        ItemMeta im4 = is4.getItemMeta();
        im4.setDisplayName(ChatColor.RESET + "Water drainer Mk. I");
        im4.setLore(lore4);
        is4.setItemMeta(im4);
        ShapedRecipe recipe4 = new ShapedRecipe(is4);
        recipe4.shape(new String[] { "ABC", "A D", "ABC" });
        recipe4.setIngredient('A', Material.STRING);
        recipe4.setIngredient('B', Material.STICK);
        recipe4.setIngredient('C', is2.getType());
        recipe4.setIngredient('D', Material.DIAMOND);
        Bukkit.getServer().addRecipe(recipe4);

        ArrayList<String> lore5 = new ArrayList<String>();
        lore4.add(ChatColor.RESET + "" + ChatColor.GRAY + "Has only 1 use!");
        lore4.add(ChatColor.RESET + "" + ChatColor.GRAY + "Drains source water");
        lore4.add(ChatColor.RESET + "" + ChatColor.GRAY + "Radius: 3");
        ItemStack is5 = new ItemStack(Material.BOW, 1);
        ItemMeta im5 = is5.getItemMeta();
        im5.setDisplayName(ChatColor.RESET + "Water drainer Mk. II");
        im5.setLore(lore5);
        is5.setItemMeta(im5);
        ShapedRecipe recipe5 = new ShapedRecipe(is5);
        recipe5.shape(new String[] { "EDB", "EAC", "EDB" });
        recipe5.setIngredient('A', is4.getType());
        recipe5.setIngredient('B', is3.getType());
        recipe5.setIngredient('C', Material.DIAMOND);
        recipe5.setIngredient('D', Material.STICK);
        recipe5.setIngredient('E', Material.STRING);
        Bukkit.getServer().addRecipe(recipe5);
    }
}