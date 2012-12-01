package com.mop.BaseRaider;

import java.util.List;
import java.util.UUID;
import java.util.Vector;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.material.DirectionalContainer;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.BlockIterator;

import net.sacredlabyrinth.Phaed.PreciousStones.PreciousStones;

public class BaseRaider extends JavaPlugin implements Listener {
    Logger log = Logger.getLogger("Minecraft");
    Recipes recipes = new Recipes();
    List<UUID> lvlOneArrows = new Vector<>();
    List<UUID> lvlTwoArrows = new Vector<>();

    private int fireballflying = 0;
    private int strength = 0;

    public void onEnable() {
	getServer().getPluginManager().registerEvents(this, this);
	recipes.addRecipes();
	this.log.info("[BaseRaider] System Enabled.");
    }

    public void onDisable() {
	this.log.info("[BaseRaider] System Disabled.");
    }

    /*
     * @EventHandler(priority = EventPriority.NORMAL) public void
     * onFireChargePlace(PlayerInteractEvent event) { if ((event.getAction() ==
     * Action.RIGHT_CLICK_BLOCK) && (event.getMaterial() == Material.SULPHUR) &&
     * !(event.getClickedBlock().getType() == Material.CHEST ||
     * event.getClickedBlock().getType() == Material.FURNACE ||
     * event.getClickedBlock().getType() == Material.WORKBENCH ||
     * event.getClickedBlock().getType() == Material.DISPENSER ||
     * event.getClickedBlock().getType() == Material.ANVIL || event
     * .getClickedBlock().getType() == Material.BREWING_STAND) &&
     * event.getItem().getEnchantmentLevel(Enchantment.DURABILITY) > 0) {
     * event.getPlayer
     * ().getItemInHand().setAmount(event.getPlayer().getItemInHand
     * ().getAmount() - 1); locations .add(new
     * FCHitData(event.getClickedBlock().getLocation(),
     * event.getItem().getEnchantmentLevel( Enchantment.DURABILITY) - 1));
     * explode(); } }
     */

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
	if ((event.getAction() == Action.RIGHT_CLICK_BLOCK)
		&& event.getMaterial() == Material.COMPASS
		&& event.getPlayer().hasPermission(
			"baseraider.playertracker.cantrack")
		&& !(event.getClickedBlock().getType() == Material.CHEST
			|| event.getClickedBlock().getType() == Material.FURNACE
			|| event.getClickedBlock().getType() == Material.WORKBENCH
			|| event.getClickedBlock().getType() == Material.DISPENSER
			|| event.getClickedBlock().getType() == Material.ANVIL || event
			.getClickedBlock().getType() == Material.BREWING_STAND)) {
	    Location playerLoc = event.getPlayer().getLocation();
	    Location targetLoc = null;
	    double finaldistance = Double.MAX_VALUE, distance = 0;
	    for (Player p : Bukkit.getOnlinePlayers())
		if (!p.isOp()
			&& p.hasPermission("baseraider.playertracker.trackable")
			&& p != event.getPlayer()) {
		    distance = playerLoc.distance(p.getLocation());
		    if (distance < finaldistance) {
			finaldistance = distance;
			targetLoc = p.getLocation();
		    }
		}
	    if (targetLoc != null) {
		event.getPlayer().sendMessage(
			ChatColor.BLUE + "The next player is " + ChatColor.GOLD
				+ (int) finaldistance + ChatColor.BLUE
				+ " blocks away from you.");
		event.getPlayer().setCompassTarget(targetLoc);
	    } else
		event.getPlayer().sendMessage(
			ChatColor.BLUE + "There is no player near you!");

	}
    }

    @EventHandler
    public void onFireBallIgnites(BlockIgniteEvent event) {
	if (event.getPlayer()==null&&fireballflying > 0) {
	    fireballflying--;
	    explode(event.getBlock());
	}
    }

    @EventHandler
    public void onDispenceFireball(BlockDispenseEvent event) {
	if (event.getItem().getType() == Material.FIREBALL
		&& event.getItem().getEnchantmentLevel(Enchantment.DURABILITY) == 1) {
	    strength = event.getItem().getEnchantmentLevel(
		    Enchantment.DURABILITY);
	    fireballflying++;
	}
    }

    @EventHandler
    public void shootArrow(EntityShootBowEvent event) {
	if (event.getBow().getEnchantmentLevel(Enchantment.WATER_WORKER) == 1) {
	    if (event.getBow() != null) {
		Player player = (Player) event.getEntity();
		
		if (player.getItemInHand().getType() == Material.BOW) {
			if (!player.hasPermission("baseraider.bow.cankeepbow1")){
				player.getInventory().remove(player.getItemInHand());
			}
		    lvlOneArrows.add(event.getProjectile().getUniqueId());
		}
	    }
	} else if (event.getBow().getEnchantmentLevel(Enchantment.WATER_WORKER) == 2) {
	    if (event.getBow() != null) {
		Player player = (Player) event.getEntity();
		if (player.getItemInHand().getType() == Material.BOW) {
			if (!player.hasPermission("baseraider.bow.cankeepbow2")){
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
	    ShapedRecipe recipe = (ShapedRecipe) event.getRecipe();
	    try {
		// control recipe fc lvl 1
		if (event.getInventory().getItem(1).getType() == Material.TNT
			&& event.getInventory().getItem(3).getType() == Material.TNT
			&& (event.getInventory().getItem(2)
				.getEnchantmentLevel(Enchantment.DURABILITY) != 1
				|| event.getInventory()
					.getItem(4)
					.getEnchantmentLevel(
						Enchantment.DURABILITY) != 1
				|| event.getInventory()
					.getItem(6)
					.getEnchantmentLevel(
						Enchantment.DURABILITY) != 1 || event
				.getInventory().getItem(8)
				.getEnchantmentLevel(Enchantment.DURABILITY) != 1)) {
		    event.getInventory().setResult(new ItemStack(Material.AIR));
		}
		// Gunpowder lvl 2
		if (event.getInventory().getItem(4).getType() == Material.TNT
			&& event.getInventory().getItem(6).getType() == Material.TNT
			&& (event.getInventory().getItem(1)
				.getEnchantmentLevel(Enchantment.WATER_WORKER) != 1
				|| event.getInventory()
					.getItem(3)
					.getEnchantmentLevel(
						Enchantment.WATER_WORKER) != 1
				|| event.getInventory()
					.getItem(7)
					.getEnchantmentLevel(
						Enchantment.WATER_WORKER) != 1 || event
				.getInventory().getItem(9)
				.getEnchantmentLevel(Enchantment.WATER_WORKER) != 1)) {
		    event.getInventory().setResult(new ItemStack(Material.AIR));
		}
		// Bow lvl 2
		if ((event.getInventory().getItem(1).getType() == Material.STRING
			&& event.getInventory().getItem(4).getType() == Material.STRING
			&& event.getInventory().getItem(7).getType() == Material.STRING && event
			.getInventory().getItem(6).getType() == Material.DIAMOND)
			&& (event.getInventory().getItem(3)
				.getEnchantmentLevel(Enchantment.DURABILITY) != 1
				|| event.getInventory()
					.getItem(9)
					.getEnchantmentLevel(
						Enchantment.DURABILITY) != 1 || event
				.getInventory().getItem(5)
				.getEnchantmentLevel(Enchantment.WATER_WORKER) != 1)) {
		    event.getInventory().setResult(new ItemStack(Material.AIR));
		}
		// Bow lvl 1
		if ((event.getInventory().getItem(1).getType() == Material.STRING
			&& event.getInventory().getItem(4).getType() == Material.STRING
			&& event.getInventory().getItem(7).getType() == Material.STRING && event
			.getInventory().getItem(6).getType() == Material.DIAMOND)
			&& (event.getInventory().getItem(3)
				.getEnchantmentLevel(Enchantment.DURABILITY) != 1 || event
				.getInventory().getItem(9)
				.getEnchantmentLevel(Enchantment.DURABILITY) != 1)) {
		    event.getInventory().setResult(new ItemStack(Material.AIR));
		}
	    } catch (Exception e) {

	    }
	}
    }

    private void removeFloatingLiquid(Block block) {
	Location loc = block.getLocation();
	int hitstrength = 8;
	// removal of flowing water, radius of 4
	for (int x = -hitstrength; x <= hitstrength; x++)
	    for (int y = -hitstrength; y <= hitstrength; y++)
		for (int z = -hitstrength; z <= hitstrength; z++) {
		    Location targetLoc = new Location(loc.getWorld(),
			    loc.getX() + x, loc.getY() + y, loc.getZ() + z);
		    if (loc.distance(targetLoc) <= 3.0D
			    && ((targetLoc.getBlock().getType() == Material.STATIONARY_WATER
				    || targetLoc.getBlock().getType() == Material.STATIONARY_LAVA
				    || targetLoc.getBlock().getType() == Material.WATER || targetLoc
				    .getBlock().getType() == Material.LAVA) && targetLoc
				    .getBlock().getData() > 0)) {
			targetLoc.getBlock().setTypeId(0);
		    }
		}
    }

    private void removeStationaryLiquid(Block block) {
	Location loc = block.getLocation();
	int hitstrength = 6;
	// removal of source water, radius of 3
	for (int x = -hitstrength; x <= hitstrength; x++)
	    for (int y = -hitstrength; y <= hitstrength; y++)
		for (int z = -hitstrength; z <= hitstrength; z++) {
		    Location targetLoc = new Location(loc.getWorld(),
			    loc.getX() + x, loc.getY() + y, loc.getZ() + z);
		    if (loc.distance(targetLoc) <= 3.0D
			    && ((targetLoc.getBlock().getType() == Material.STATIONARY_WATER
				    || targetLoc.getBlock().getType() == Material.STATIONARY_LAVA
				    || targetLoc.getBlock().getType() == Material.WATER || targetLoc
				    .getBlock().getType() == Material.LAVA) && targetLoc
				    .getBlock().getData() == 0)) {
			targetLoc.getBlock().setTypeId(0);
		    }
		}
    }

    public void explode(Block block) {
	Location loc = block.getLocation();
	int hitstrength = 1;
	// 1F=0.25 TNT explosion; 4F=normal TNT strength
	loc.getWorld().createExplosion(loc, 1.5F);
	// removal of obby, radius of 3
	for (int x = -hitstrength; x <= hitstrength; x++)
	    for (int y = -hitstrength; y <= hitstrength; y++)
		for (int z = -hitstrength; z <= hitstrength; z++) {
		    Location targetLoc = new Location(loc.getWorld(),
			    loc.getX() + x, loc.getY() + y, loc.getZ() + z);
		    if (loc.distance(targetLoc) <= 3.0D
			    && targetLoc.getBlock().getType() == Material.OBSIDIAN) {
			if (!PreciousStones.API().isPStone(targetLoc))
			    targetLoc.getBlock().setTypeId(0);
		    }
		}
    }

}