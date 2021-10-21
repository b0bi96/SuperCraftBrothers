//package pl.bobi.tasks;
//
//import org.bukkit.entity.Player;
//import org.bukkit.scheduler.BukkitRunnable;
//import pl.bobi.kits.specials.Reaper;
//
//public class ReaperTask extends BukkitRunnable {
//    @Override
//    public void run() {
//        if (Reaper.getInfPlayer().isEmpty()) return;
//
//        for (Player player : Reaper.getInfPlayer() ) {
//            double hp = player.getHealth();
//            player.setHealth(hp - 6.5);
//        }
//    }
//}
