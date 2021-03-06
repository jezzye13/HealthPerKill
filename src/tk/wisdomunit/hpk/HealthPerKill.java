package tk.wisdomunit.hpk;

import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class HealthPerKill extends JavaPlugin implements Listener {

	public static final String PATH_NUMBER_HEALTH_DOUBLE = "set_health";
	public static final String PATH_SOUND_STRING = "set_sound";
	public static final String PERMISSION_USE = "hpk.use";

	private double number_health;
	private String sound;

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);

		saveDefaultConfig();

		try {
			number_health = getConfig().getDouble(PATH_NUMBER_HEALTH_DOUBLE);
			sound = getConfig().getString(PATH_SOUND_STRING);
		} catch (NullPointerException ex) {
			getLogger().warning("Can`t load config.yml! ;(");
			getLogger()
					.warning("Reinstall this plugin to fix (jar and folder)");
			getServer().getPluginManager().disablePlugin(this);
		}
	}

	@EventHandler
	public void onKill(PlayerDeathEvent e) {
		if (e.getEntity().getKiller() instanceof Player) {
			Player p = e.getEntity().getKiller();

			if (!p.getUniqueId().equals(e.getEntity().getUniqueId())) {
			
				if(p.hasPermission(PERMISSION_USE)) {
					
					double out_come = p.getHealth() + number_health;
					double maxHealth = p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
					if (out_come > maxHealth) {
						p.setHealth(maxHealth);
					} else {
						p.setHealth(out_come);
					}

					p.playSound(p.getLocation(), Sound.valueOf(sound), 1, 10);
					
				}
			}
		}
	}

}
