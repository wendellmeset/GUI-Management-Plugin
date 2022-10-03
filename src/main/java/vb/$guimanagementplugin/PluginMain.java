package vb.$guimanagementplugin;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.event.*;
import org.bukkit.plugin.java.*;

public class PluginMain extends JavaPlugin implements Listener {

	private static PluginMain instance;

	@Override
	public void onEnable() {
		instance = this;
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(GUIManager.getInstance(), this);
		GUIManager.getInstance().register("timegui", guiPlayer -> {
			try {
				org.bukkit.inventory.Inventory guiInventory = Bukkit.createInventory(new GUIIdentifier("timegui"),
						((int) (27d)), "Set Time");
				guiInventory.setItem(((int) (11d)), PluginMain
						.getNamedItem(((org.bukkit.Material) org.bukkit.Material.DAYLIGHT_DETECTOR), "Day Time"));
				guiInventory.setItem(((int) (13d)),
						PluginMain.getNamedItem(((org.bukkit.Material) org.bukkit.Material.BLACK_WOOL), "Night Time"));
				guiInventory.setItem(((int) (15d)), PluginMain
						.getNamedItem(((org.bukkit.Material) org.bukkit.Material.BLACK_CONCRETE), "MidNight Time"));
				return guiInventory;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}, false);
		GUIManager.getInstance().register("gamemodegui", guiPlayer -> {
			try {
				org.bukkit.inventory.Inventory guiInventory = Bukkit.createInventory(new GUIIdentifier("gamemodegui"),
						((int) (27d)), "Change Gamemode!!");
				guiInventory.setItem(((int) (10d)),
						PluginMain.getNamedItem(((org.bukkit.Material) org.bukkit.Material.GRASS_BLOCK), "Survival"));
				guiInventory.setItem(((int) (12d)),
						PluginMain.getNamedItem(((org.bukkit.Material) org.bukkit.Material.BEDROCK), "Creative"));
				guiInventory.setItem(((int) (14d)),
						PluginMain.getNamedItem(((org.bukkit.Material) org.bukkit.Material.BARRIER), "Adventure"));
				guiInventory.setItem(((int) (16d)),
						PluginMain.getNamedItem(((org.bukkit.Material) org.bukkit.Material.FEATHER), "Spectator"));
				return guiInventory;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}, false);
	}

	@Override
	public void onDisable() {
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] commandArgs) {
		if (command.getName().equalsIgnoreCase("gui")) {
			try {
				if (!((commandArgs.length > ((int) (0d)) ? commandArgs[((int) (0d))] : null) == null)) {
					if (PluginMain.checkEquals((commandArgs.length > ((int) (0d)) ? commandArgs[((int) (0d))] : null),
							"time")) {
						GUIManager.getInstance().open("timegui", ((org.bukkit.entity.Player) (Object) commandSender));
					} else if (PluginMain.checkEquals(
							(commandArgs.length > ((int) (0d)) ? commandArgs[((int) (0d))] : null), "gamemode")) {
						GUIManager.getInstance().open("gamemodegui",
								((org.bukkit.entity.Player) (Object) commandSender));
					} else if (PluginMain.checkEquals(
							(commandArgs.length > ((int) (0d)) ? commandArgs[((int) (0d))] : null), "help")) {
						commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&',
								ChatColor.translateAlternateColorCodes('&', "&cgamemode, time")));
					} else {
						commandSender.sendMessage(
								ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&',
										"&cInvalid GUI Name!! That GUI does not currently exist!! Use /gui help for list of gui names!!")));
					}
				} else {
					commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&',
							ChatColor.translateAlternateColorCodes('&', "&cIncorrect syntax! Use /gui gui_name")));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		return true;
	}

	public static void procedure(String procedure, List procedureArgs) throws Exception {
	}

	public static Object function(String function, List functionArgs) throws Exception {
		return null;
	}

	public static List createList(Object obj) {
		if (obj instanceof List) {
			return (List) obj;
		}
		List list = new ArrayList<>();
		if (obj.getClass().isArray()) {
			int length = java.lang.reflect.Array.getLength(obj);
			for (int i = 0; i < length; i++) {
				list.add(java.lang.reflect.Array.get(obj, i));
			}
		} else if (obj instanceof Collection<?>) {
			list.addAll((Collection<?>) obj);
		} else if (obj instanceof Iterator) {
			((Iterator<?>) obj).forEachRemaining(list::add);
		} else {
			list.add(obj);
		}
		return list;
	}

	public static void createResourceFile(String path) {
		Path file = getInstance().getDataFolder().toPath().resolve(path);
		if (Files.notExists(file)) {
			try (InputStream inputStream = PluginMain.class.getResourceAsStream("/" + path)) {
				Files.createDirectories(file.getParent());
				Files.copy(inputStream, file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static PluginMain getInstance() {
		return instance;
	}

	@EventHandler
	public void onGUIClick(GUIClickEvent event) throws Exception {
		if (event.getID().equalsIgnoreCase("timegui")) {
			if (PluginMain.checkEquals(((java.lang.Object) (Object) event.getSlot()),
					((java.lang.Object) (Object) (11d)))) {
				((org.bukkit.World) ((org.bukkit.entity.Entity) (Object) ((org.bukkit.entity.Player) event
						.getWhoClicked())).getWorld()).setTime(((long) (1000d)));
				((org.bukkit.command.CommandSender) (Object) ((org.bukkit.entity.Player) event.getWhoClicked()))
						.sendMessage(ChatColor.translateAlternateColorCodes('&',
								ChatColor.translateAlternateColorCodes('&', "&9[Time] &cSet time to day!")));
			} else if (PluginMain.checkEquals(((java.lang.Object) (Object) event.getSlot()),
					((java.lang.Object) (Object) (13d)))) {
				((org.bukkit.World) ((org.bukkit.entity.Entity) (Object) ((org.bukkit.entity.Player) event
						.getWhoClicked())).getWorld()).setTime(((long) (13000d)));
				((org.bukkit.command.CommandSender) (Object) ((org.bukkit.entity.Player) event.getWhoClicked()))
						.sendMessage(ChatColor.translateAlternateColorCodes('&',
								ChatColor.translateAlternateColorCodes('&', "&9[Time] &cSet time to night!")));
			} else if (PluginMain.checkEquals(((java.lang.Object) (Object) event.getSlot()),
					((java.lang.Object) (Object) (15d)))) {
				((org.bukkit.World) ((org.bukkit.entity.Entity) (Object) ((org.bukkit.entity.Player) event
						.getWhoClicked())).getWorld()).setTime(((long) (18000d)));
				((org.bukkit.command.CommandSender) (Object) ((org.bukkit.entity.Player) event.getWhoClicked()))
						.sendMessage(ChatColor.translateAlternateColorCodes('&',
								ChatColor.translateAlternateColorCodes('&', "&9[Time] &cSet time to midnight!")));
			}
			return;
		}
		if (event.getID().equalsIgnoreCase("gamemodegui")) {
			if (PluginMain.checkEquals(((java.lang.Object) (Object) event.getSlot()),
					((java.lang.Object) (Object) (10d)))) {
				((org.bukkit.entity.HumanEntity) (Object) ((org.bukkit.entity.Player) event.getWhoClicked()))
						.setGameMode(((org.bukkit.GameMode) org.bukkit.GameMode.SURVIVAL));
				((org.bukkit.command.CommandSender) (Object) ((org.bukkit.entity.Player) event.getWhoClicked()))
						.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatColor
								.translateAlternateColorCodes('&', "&9[Gamemode] &cSet gamemode to survival!")));
			}
			if (PluginMain.checkEquals(((java.lang.Object) (Object) event.getSlot()),
					((java.lang.Object) (Object) (12d)))) {
				((org.bukkit.entity.HumanEntity) (Object) ((org.bukkit.entity.Player) event.getWhoClicked()))
						.setGameMode(((org.bukkit.GameMode) org.bukkit.GameMode.CREATIVE));
				((org.bukkit.command.CommandSender) (Object) ((org.bukkit.entity.Player) event.getWhoClicked()))
						.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatColor
								.translateAlternateColorCodes('&', "&9[Gamemode] &cSet gamemode to creative!!")));
			}
			if (PluginMain.checkEquals(((java.lang.Object) (Object) event.getSlot()),
					((java.lang.Object) (Object) (14d)))) {
				((org.bukkit.entity.HumanEntity) (Object) ((org.bukkit.entity.Player) event.getWhoClicked()))
						.setGameMode(((org.bukkit.GameMode) org.bukkit.GameMode.ADVENTURE));
				((org.bukkit.command.CommandSender) (Object) ((org.bukkit.entity.Player) event.getWhoClicked()))
						.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatColor
								.translateAlternateColorCodes('&', "&9[Gamemode] &cSet gamemode to adventure!!!")));
			}
			if (PluginMain.checkEquals(((java.lang.Object) (Object) event.getSlot()),
					((java.lang.Object) (Object) (16d)))) {
				((org.bukkit.entity.HumanEntity) (Object) ((org.bukkit.entity.Player) event.getWhoClicked()))
						.setGameMode(((org.bukkit.GameMode) org.bukkit.GameMode.SPECTATOR));
				((org.bukkit.command.CommandSender) (Object) ((org.bukkit.entity.Player) event.getWhoClicked()))
						.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatColor
								.translateAlternateColorCodes('&', "&9[Gamemode] &cSet gamemode to spectator!!!!")));
			}
			return;
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void event1(org.bukkit.event.server.TabCompleteEvent event) throws Exception {
		if ((((boolean) ((java.lang.String) event.getBuffer()).startsWith("/gui "))
				|| ((boolean) ((java.lang.String) event.getBuffer()).startsWith("/accessgui ")))) {
			event.setCompletions(new ArrayList(Arrays.asList("help", "gamemode", "time")));
		}
	}

	public static org.bukkit.inventory.ItemStack getNamedItem(Material material, String name) {
		org.bukkit.inventory.ItemStack item = new org.bukkit.inventory.ItemStack(material);
		org.bukkit.inventory.meta.ItemMeta itemMeta = item.getItemMeta();
		if (itemMeta != null) {
			itemMeta.setDisplayName(name);
			item.setItemMeta(itemMeta);
		}
		return item;
	}

	public static boolean checkEquals(Object o1, Object o2) {
		if (o1 == null || o2 == null) {
			return false;
		}
		return o1 instanceof Number && o2 instanceof Number
				? ((Number) o1).doubleValue() == ((Number) o2).doubleValue()
				: o1.equals(o2);
	}
}
