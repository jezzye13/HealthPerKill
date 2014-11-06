package tk.wisdomunit.hpk;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class HealthPerKill extends JavaPlugin implements Listener {
	
	private static HealthPerKill instance;
	public static HealthPerKill getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		instance = this;
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable() {
		instance = null;
	}
	
	@EventHandler
	public void onKill(PlayerDeathEvent e) {
		if(e.getEntity().getKiller() instanceof Player) {
			Player p = e.getEntity().getKiller();
			
			p.setHealth(20.0);
			p.playSound(p.getLocation(), Sound.IRONGOLEM_DEATH, 1, 10);
		}
	}

}
