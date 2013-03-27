package com.minecraftserver.baseraider;

import java.util.List;
import java.util.UUID;
import java.util.Vector;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import net.sacredlabyrinth.Phaed.PreciousStones.PreciousStones;

public class BaseRaider extends JavaPlugin implements Listener {
    Logger      log            = Logger.getLogger("Minecraft");
    Recipes     recipes        = new Recipes();
    List<UUID>  lvlOneArrows   = new Vector<>();
    List<UUID>  lvlTwoArrows   = new Vector<>();

    private int fireballflying = 0;
    
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        recipes.addRecipes();
        this.log.info("[BaseRaider] System Enabled.");
    }

    public void onDisable() {
        this.log.info("[BaseRaider] System Disabled.");
    }

    @EventHandler
    public void onFireBallIgnites(BlockIgniteEvent event) {
        if (event.isCancelled()) return;
        if (event.getPlayer() == null && fireballflying > 0) {
            fireballflying--;
            explode(event.getBlock());
        }
    }

    @EventHandler
    public void onDispenceFireball(BlockDispenseEvent event) {
        if (event.isCancelled()) return;
        if (event.getItem().getType() == Material.FIREBALL
                && event.getItem().getEnchantmentLevel(Enchantment.DURABILITY) == 1) {
            event.getItem().getEnchantmentLevel(Enchantment.DURABILITY);
            fireballflying++;
        }
    }

    @EventHandler
    public void shootArrow(EntityShootBowEvent event) {
        if (event.isCancelled()) return;
        if (event.getBow().getEnchantmentLevel(Enchantment.WATER_WORKER) == 1) {
            if (event.getBow() != null) {
                Player player = (Player) event.getEntity();

                if (player.getItemInHand().getType() == Material.BOW) {
                    if (!player.hasPermission("baseraider.bow.cankeepbow1")) {
                        player.getInventory().remove(player.getItemInHand());
                    }
                    lvlOneArrows.add(event.getProjectile().getUniqueId());
                }
            }
        } else if (event.getBow().getEnchantmentLevel(Enchantment.WATER_WORKER) == 2) {
            if (event.getBow() != null) {
                Player player = (Player) event.getEntity();
                if (player.getItemInHand().getType() == Material.BOW) {
                    if (!player.hasPermission("baseraider.bow.cankeepbow2")) {
                        player.getInventory().remove(player.getItemInHand());
                    }
                    lvlTwoArrows.add(event.getProjectile().getUniqueId());
                }
            }
        }
    }

    @EventHandler
    public void onArrowHit(ProjectileHitEvent event) {
        if (event.getEntityType() == EntityType.ARROW) {
            Arrow arrow = (Arrow) event.getEntity();
            if (lvlOneArrows.contains(arrow.getUniqueId())) {
                Block hitBlock = arrow.getLocation().getBlock();
                hitBlock.getWorld().createExplosion(arrow.getLocation(), 0.1F);
                removeFloatingLiquid(hitBlock);
                lvlOneArrows.remove(arrow.getUniqueId());
            } else if (lvlTwoArrows.contains(arrow.getUniqueId())) {
                Block hitBlock = arrow.getLocation().getBlock();
                hitBlock.getWorld().createExplosion(arrow.getLocation(), 0.1F);
                removeStationaryLiquid(hitBlock);
                lvlTwoArrows.remove(arrow.getUniqueId());
            }
        }
    }

    @EventHandler
    public void onPrepareItemcraftevent(PrepareItemCraftEvent event) {
        if (event.getRecipe() instanceof ShapedRecipe) {
            event.getRecipe();
            try {
                // control recipe fc lvl 1
                if (event.getInventory().getItem(2).getType() == Material.COAL
                        && event.getInventory().getItem(4).getType() == Material.COAL
                        && (!event.getInventory().getItem(2).getItemMeta().getDisplayName().equals(ChatColor.RESET + "Compressed Gunpowder")
                                || !event.getInventory().getItem(2).getItemMeta().getDisplayName().equals(ChatColor.RESET + "Compressed Gunpowder")
                                || !event.getInventory().getItem(2).getItemMeta().getDisplayName().equals(ChatColor.RESET + "Compressed Gunpowder")
                                || !event.getInventory().getItem(2).getItemMeta().getDisplayName().equals(ChatColor.RESET + "Compressed Gunpowder"))) {
                    event.getInventory().setResult(new ItemStack(Material.AIR));
                }
                // Gunpowder lvl 2
                if (event.getInventory().getItem(1).getType() == Material.COAL
                        && event.getInventory().getItem(3).getType() == Material.COAL
                        && (!event.getInventory().getItem(1).getItemMeta().getDisplayName().equals(ChatColor.RESET + "Improved Gunpowder")
                                || !event.getInventory().getItem(3).getItemMeta().getDisplayName().equals(ChatColor.RESET + "Improved Gunpowder")
                                || !event.getInventory().getItem(7).getItemMeta().getDisplayName().equals(ChatColor.RESET + "Improved Gunpowder")
                                || !event.getInventory().getItem(9).getItemMeta().getDisplayName().equals(ChatColor.RESET + "Improved Gunpowder"))) {
                    event.getInventory().setResult(new ItemStack(Material.AIR));
                }
                // Bow lvl 2
                if ((event.getInventory().getItem(1).getType() == Material.STRING
                        && event.getInventory().getItem(4).getType() == Material.STRING
                        && event.getInventory().getItem(7).getType() == Material.STRING 
                        && event.getInventory().getItem(6).getType() == Material.DIAMOND)
                        && (!event.getInventory().getItem(3).getItemMeta().getDisplayName().equals(ChatColor.RESET + "Obby killer")
                                || !event.getInventory().getItem(9).getItemMeta().getDisplayName().equals(ChatColor.RESET + "Obby killer")
                                || !event.getInventory().getItem(5).getItemMeta().getDisplayName().equals(ChatColor.RESET + "Water drainer Mk. I"))) {
                    event.getInventory().setResult(new ItemStack(Material.AIR));
                }
                // Bow lvl 1
                if ((event.getInventory().getItem(1).getType() == Material.STRING
                        && event.getInventory().getItem(4).getType() == Material.STRING
                        && event.getInventory().getItem(7).getType() == Material.STRING 
                        && event.getInventory().getItem(6).getType() == Material.DIAMOND)
                        && (!event.getInventory().getItem(3).getItemMeta().getDisplayName().equals(ChatColor.RESET + "Improved Gunpowder")
                            || !event.getInventory().getItem(9).getItemMeta().getDisplayName().equals(ChatColor.RESET + "Improved Gunpowder"))) {
                    event.getInventory().setResult(new ItemStack(Material.AIR));
                }
            } catch (Exception e) {

            }
        }
    }

    // removes floating liquid
    private void removeFloatingLiquid(Block block) {
        Location loc = block.getLocation();
        int radius = 8;
        // removal of flowing water, radius of 4
        for (int x = -radius; x <= radius; x++)
            for (int y = -radius; y <= radius; y++)
                for (int z = -radius; z <= radius; z++) {
                    Location targetLoc = new Location(loc.getWorld(), loc.getX() + x, loc.getY()
                            + y, loc.getZ() + z);
                    if (loc.distance(targetLoc) <= 3.0D
                            && ((targetLoc.getBlock().getType() == Material.STATIONARY_WATER
                                    || targetLoc.getBlock().getType() == Material.STATIONARY_LAVA
                                    || targetLoc.getBlock().getType() == Material.WATER || targetLoc
                                    .getBlock().getType() == Material.LAVA) && targetLoc.getBlock()
                                    .getData() > 0)) {
                        targetLoc.getBlock().setTypeId(0);
                    }
                }
    }

    // removes stationary liquid
    private void removeStationaryLiquid(Block block) {
        Location loc = block.getLocation();
        int radius = 6;
        // removal of source water, radius of 3
        for (int x = -radius; x <= radius; x++)
            for (int y = -radius; y <= radius; y++)
                for (int z = -radius; z <= radius; z++) {
                    Location targetLoc = new Location(loc.getWorld(), loc.getX() + x, loc.getY()
                            + y, loc.getZ() + z);
                    if (loc.distance(targetLoc) <= 3.0D
                            && ((targetLoc.getBlock().getType() == Material.STATIONARY_WATER
                                    || targetLoc.getBlock().getType() == Material.STATIONARY_LAVA
                                    || targetLoc.getBlock().getType() == Material.WATER || targetLoc
                                    .getBlock().getType() == Material.LAVA) && targetLoc.getBlock()
                                    .getData() == 0)) {
                        targetLoc.getBlock().setTypeId(0);
                    }
                }
    }

    // explodes and destroyes obby
    public void explode(Block block) {
        Location loc = block.getLocation();
        int radius = 1;
        // 1F=0.25 TNT explosion; 4F=normal TNT strength
        loc.getWorld().createExplosion(loc, 1.5F);
        // removal of obby, radius of 3
        for (int x = -radius; x <= radius; x++)
            for (int y = -radius; y <= radius; y++)
                for (int z = -radius; z <= radius; z++) {
                    Location targetLoc = new Location(loc.getWorld(), loc.getX() + x, loc.getY()
                            + y, loc.getZ() + z);
                    if (loc.distance(targetLoc) <= 3.0D
                            && targetLoc.getBlock().getType() == Material.OBSIDIAN) {
                        if (!PreciousStones.API().isPStone(targetLoc))
                            targetLoc.getBlock().setTypeId(0);
                    }
                }
    }

}