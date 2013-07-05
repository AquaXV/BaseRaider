package com.minecraftserver.baseraider;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;

public class Recipes {

    public static HashMap<String, String[]> receipes = new HashMap<>();

    public static void addRecipes() {
        /*
         * Compressed Gunpowder
         * receipe (#=empty spot)
         * G G #
         * G G #
         * # # #
         * 2. receipe
         * # G G
         * # G G
         * # # #
         * # # #
         * G G #
         * G G #
         * # # #
         * # G G
         * # G G
         * no check needed
         */
        ItemStack is1 = new ItemStack(Material.SULPHUR, 1);
        ItemMeta im1 = is1.getItemMeta();
        im1.setDisplayName(ChatColor.RESET + "Compressed Gunpowder");
        is1.setItemMeta(im1);
        ShapedRecipe recipe1a = new ShapedRecipe(is1);
        recipe1a.shape("AA ", "AA ", "   ");
        recipe1a.setIngredient('A', Material.SULPHUR);
        Bukkit.getServer().addRecipe(recipe1a);
        ShapedRecipe recipe1b = new ShapedRecipe(is1);
        recipe1b.shape(" AA", " AA", "   ");
        recipe1b.setIngredient('A', Material.SULPHUR);
        Bukkit.getServer().addRecipe(recipe1b);
        ShapedRecipe recipe1c = new ShapedRecipe(is1);
        recipe1c.shape("   ", " AA", " AA");
        recipe1c.setIngredient('A', Material.SULPHUR);
        Bukkit.getServer().addRecipe(recipe1c);
        ShapedRecipe recipe1d = new ShapedRecipe(is1);
        recipe1d.shape("   ", "AA ", "AA ");
        recipe1d.setIngredient('A', Material.SULPHUR);
        Bukkit.getServer().addRecipe(recipe1d);
        /*
         * Improved Gunpowder
         * receipe (#=empty spot; A=Compressed Gunpowder; N=Netherrack)
         * A C A
         * C N C
         * A C A
         * check needed
         */
        ItemStack is2 = new ItemStack(Material.SULPHUR, 2);
        ItemMeta im2 = is2.getItemMeta();
        im2.setDisplayName(ChatColor.RESET + "Improved Gunpowder");
        is2.setItemMeta(im2);
        ShapedRecipe recipe2 = new ShapedRecipe(is2);
        recipe2.shape("ABA", "BCB", "ABA");
        recipe2.setIngredient('A', is1.getType());
        recipe2.setIngredient('B', Material.COAL);
        recipe2.setIngredient('C', Material.NETHERRACK);
        String[] receipe = {im1.getDisplayName(), "", im1.getDisplayName(), "", "", "", im1.getDisplayName(), "",
                                   im1.getDisplayName()};
        receipes.put(im2.getDisplayName(), receipe);
        Bukkit.getServer().addRecipe(recipe2);

        /*
         * Obby killer
         * receipe (#=empty spot; A=Improved Gunpowder)
         * C A C
         * A F A
         * C A C
         * check needed
         */
        ItemStack is3 = new ItemStack(Material.FIREBALL, 1);
        ItemMeta im3 = is3.getItemMeta();
        im3.setDisplayName(ChatColor.RESET + "Obby killer");
        is3.setItemMeta(im3);
        ShapedRecipe recipe3 = new ShapedRecipe(is3);
        recipe3.shape("BAB", "ACA", "BAB");
        recipe3.setIngredient('A', is2.getType());
        recipe3.setIngredient('B', Material.COAL);
        recipe3.setIngredient('C', Material.FIREBALL);
        String[] receipe1 = {"", im2.getDisplayName(), "", im2.getDisplayName(), "", im2.getDisplayName(), "",
                                    im2.getDisplayName(), ""};
        receipes.put(im3.getDisplayName(), receipe1);
        Bukkit.getServer().addRecipe(recipe3);

        /*
         * Water drainer Mk. I
         * receipe (#=empty spot; A=Improved Gunpowder; G=String; K=Stick)
         * G K A
         * G # D
         * G K A
         * check needed
         */
        ArrayList<String> lore4 = new ArrayList<>();
        lore4.add(ChatColor.RESET + "" + ChatColor.GRAY + "Has only 1 use!");
        lore4.add(ChatColor.RESET + "" + ChatColor.GRAY + "Drains flowing water");
        lore4.add(ChatColor.RESET + "" + ChatColor.GRAY + "Radius: 4");
        ItemStack is4 = new ItemStack(Material.BOW, 1);
        ItemMeta im4 = is4.getItemMeta();
        im4.setDisplayName(ChatColor.RESET + "Water drainer Mk. I");
        im4.setLore(lore4);
        is4.setItemMeta(im4);
        ShapedRecipe recipe4 = new ShapedRecipe(is4);
        recipe4.shape("ABC", "A D", "ABC");
        recipe4.setIngredient('A', Material.STRING);
        recipe4.setIngredient('B', Material.STICK);
        recipe4.setIngredient('C', is2.getType());
        recipe4.setIngredient('D', Material.DIAMOND);
        String[] receipe2 = {"", "", im2.getDisplayName(), "", "", "", "", "", im2.getDisplayName()};
        receipes.put(im4.getDisplayName(), receipe2);
        Bukkit.getServer().addRecipe(recipe4);

        /*
         * Water drainer Mk. II
         * recipe (#=empty spot; A=Water drainer Mk. I; B=Obby killer;
         * G=String; K=Stick)
         * G K B
         * G A D
         * G K B
         * check needed
         */
        ArrayList<String> lore5 = new ArrayList<>();
        lore4.add(ChatColor.RESET + "" + ChatColor.GRAY + "Has only 1 use!");
        lore4.add(ChatColor.RESET + "" + ChatColor.GRAY + "Drains source water");
        lore4.add(ChatColor.RESET + "" + ChatColor.GRAY + "Radius: 3");
        ItemStack is5 = new ItemStack(Material.BOW, 1);
        ItemMeta im5 = is5.getItemMeta();
        im5.setDisplayName(ChatColor.RESET + "Water drainer Mk. II");
        im5.setLore(lore5);
        is5.setItemMeta(im5);
        ShapedRecipe recipe5 = new ShapedRecipe(is5);
        recipe5.shape("EDB", "EAC", "EDB");
        recipe5.setIngredient('A', is4.getType());
        recipe5.setIngredient('B', is3.getType());
        recipe5.setIngredient('C', Material.DIAMOND);
        recipe5.setIngredient('D', Material.STICK);
        recipe5.setIngredient('E', Material.STRING);
        String[] receipe3 = {"", "", im3.getDisplayName(), "", im4.getDisplayName(), "", "", "", im3.getDisplayName()};
        receipes.put(im5.getDisplayName(), receipe3);
        Bukkit.getServer().addRecipe(recipe5);
    }
}