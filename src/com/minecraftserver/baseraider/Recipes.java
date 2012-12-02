package com.minecraftserver.baseraider;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class Recipes {
    public void addRecipes() {
	ItemStack GPWW1 = new ItemStack(Material.SULPHUR, 1);
	GPWW1.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
	ShapedRecipe recipe1 = new ShapedRecipe(GPWW1);
	recipe1.shape(new String[] { " A ", "AAA", " A " });
	recipe1.setIngredient('A', Material.SULPHUR);
	Bukkit.getServer().addRecipe(recipe1);

	ItemStack GPD1 = new ItemStack(Material.SULPHUR, 1);
	GPD1.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
	ShapedRecipe recipe2 = new ShapedRecipe(GPD1);
	recipe2.shape(new String[] { "ABA", "BCB", "ABA" });
	recipe2.setIngredient('A', GPWW1.getType());
	recipe2.setIngredient('B', Material.TNT);
	recipe2.setIngredient('C', Material.FIREBALL);
	Bukkit.getServer().addRecipe(recipe2);

	ItemStack FCD1 = new ItemStack(Material.FIREBALL, 1);
	FCD1.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
	ShapedRecipe recipe3 = new ShapedRecipe(FCD1);
	recipe3.shape(new String[] { "BAB", "ACA", "BAB" });
	recipe3.setIngredient('A', GPD1.getType());
	recipe3.setIngredient('B', Material.TNT);
	recipe3.setIngredient('C', Material.FIREBALL);
	Bukkit.getServer().addRecipe(recipe3);

	ItemStack BWW1 = new ItemStack(Material.BOW, 1);
	BWW1.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
	ShapedRecipe recipe4 = new ShapedRecipe(BWW1);
	recipe4.shape(new String[] { "ABC", "A D", "ABC" });
	recipe4.setIngredient('A', Material.STRING);
	recipe4.setIngredient('B', Material.STICK);
	recipe4.setIngredient('C', GPD1.getType());
	recipe4.setIngredient('D', Material.DIAMOND);
	Bukkit.getServer().addRecipe(recipe4);

	ItemStack BWW2 = new ItemStack(Material.BOW, 1);
	BWW2.addUnsafeEnchantment(Enchantment.WATER_WORKER, 2);
	ShapedRecipe recipe5 = new ShapedRecipe(BWW2);
	recipe5.shape(new String[] { "EDB", "EAC", "EDB" });
	recipe5.setIngredient('A', BWW1.getType());
	recipe5.setIngredient('B', FCD1.getType());
	recipe5.setIngredient('C', Material.DIAMOND);
	recipe5.setIngredient('D', Material.STICK);
	recipe5.setIngredient('E', Material.STRING);
	Bukkit.getServer().addRecipe(recipe5);
    }
}