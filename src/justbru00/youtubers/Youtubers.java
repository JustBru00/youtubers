package justbru00.youtubers;

import java.util.logging.Logger;






import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Youtubers extends JavaPlugin implements Listener {

	public final Logger logger = Logger.getLogger("Minecraft");
	
	// Youtubers plugin for EpicRealm

	public String msgJustBru00 = ChatColor.WHITE
			+ "JustBru00 Owner of server.\nClick Link below for his youtube channel:"
			+ ChatColor.BLUE
			+ "\nhttps://www.youtube.com/channel/UCP19TT7v3DF8kbKbMr2bkTA";
	
	public String msgFireShadow196 = ChatColor.WHITE
			+ "FireShadow196\nClick Link below for his youtube channel:"
			+ ChatColor.BLUE
			+ "\nhttps://www.youtube.com/user/FireShadow196";
	public String msggavin12787 = ChatColor.WHITE
			+ "gavin12787\nClick Link below for his youtube channel:"
			+ ChatColor.BLUE
			+ "\nnull";
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String commandLabel, String[] args) {

		Player player = (Player) sender;
		if (commandLabel.equalsIgnoreCase("youtubers")) {
			openGUI(player);
		}
		return false;
	}

	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Has Been Disabled.");
	}

	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version: "
				+ pdfFile.getVersion() + " Has Been Enabled.");

		getServer().getPluginManager().registerEvents(this, this);
	}

	public Inventory inv;
	
	// Skull Maker
	public ItemStack skullCreate(String owner, String displayName){
		ItemStack is = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta sm = (SkullMeta) is.getItemMeta();
		sm.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
		sm.setOwner(owner);
		is.setItemMeta(sm);		
		return is;		
	}

	public void openGUI(Player p) {
		inv = Bukkit.createInventory(null, 18, ChatColor.DARK_RED + "You"
				+ ChatColor.WHITE + "Tubers");

		// Close Button
		// -------------------------------------------------------------
		ItemStack closebutton = new ItemStack(Material.BARRIER);
		ItemMeta closebuttonim = closebutton.getItemMeta();
		closebuttonim.setDisplayName(ChatColor.DARK_RED + "Close GUI");
		closebutton.setItemMeta(closebuttonim);
		// -------------------------------------------------------------	
		// Set Slots
		// -------------------------------------------------------------
		inv.setItem(0, skullCreate("JustBru00", ChatColor.BLUE + "JustBru00" + ChatColor.GOLD + " Click for info."));
		inv.setItem(1, skullCreate("FireShadow196", ChatColor.BLUE + "FireShadow196" + ChatColor.GOLD + " Click for info."));		
		inv.setItem(2, skullCreate("gavin12787", ChatColor.BLUE + "gavin12787 &6Click for info."));
		inv.setItem(17, closebutton);
		

		// -----------------------------------------------------------------------

		p.openInventory(inv);

	}

	@EventHandler
	public void InventoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		
		if (e.getInventory()
				.getTitle()
				.contains(
						ChatColor.DARK_RED + "You" + ChatColor.WHITE + "Tubers")) {
			e.setCancelled(true);

			if (e.getCurrentItem() == null) {
				return;
			}			
			if (e.getCurrentItem().getType() == Material.SKULL_ITEM) {	
				if (e.getSlot() == 0){

					p.closeInventory();
					p.sendMessage(msgJustBru00);
				}
				if (e.getSlot() == 1) {
					p.closeInventory();
					p.sendMessage(msgFireShadow196);
				}
				if (e.getSlot() == 2) {
					p.closeInventory();
					p.sendMessage(msggavin12787);
				}
			} else if (e.getCurrentItem().getType() == Material.BARRIER) {

				p.sendMessage("Closed GUI");
				p.closeInventory();

			}
		}

	}
}
