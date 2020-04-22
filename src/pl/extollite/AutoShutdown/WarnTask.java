package pl.extollite.AutoShutdown;

import cn.nukkit.utils.TextFormat;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class WarnTask extends TimerTask {
    protected final AutoShutdown plugin;
    protected long seconds;

    public WarnTask(AutoShutdown plugin, long seconds) {
        this.plugin = plugin;
        this.seconds = seconds;
    }

    public void run() {
        plugin.getServer().getScheduler().scheduleTask(plugin, () -> {
            if (TimeUnit.SECONDS.toMinutes(seconds) > 0L) {
                if (TimeUnit.SECONDS.toMinutes(seconds) == 1L) {
                    if (seconds - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(seconds)) == 0L)
                        plugin.getServer().broadcastMessage(TextFormat.RED + plugin.shutdownMessage + " in einer Minute...");
                    else {
                        plugin.getServer().broadcastMessage(TextFormat.RED + plugin.shutdownMessage + " in einer Minute"+
                                Long.valueOf(seconds - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(seconds))) +" Sekunden...");
                    }

                } else if (seconds - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(seconds)) == 0L) {
                    plugin.getServer().broadcastMessage(TextFormat.RED + plugin.shutdownMessage + " in "+
                            Long.valueOf(TimeUnit.SECONDS.toMinutes(seconds)) +" Minuten...");
                } else {
                    plugin.getServer().broadcastMessage(TextFormat.RED + plugin.shutdownMessage + " in "+
                            Long.valueOf(TimeUnit.SECONDS.toMinutes(seconds)) +" Minuten "+
                            Long.valueOf(seconds - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(seconds))) +" Sekunden...");
                }

            } else if (TimeUnit.SECONDS.toSeconds(seconds) == 1L)
                plugin.getServer().broadcastMessage(TextFormat.RED + plugin.shutdownMessage + " Neustart!");
            else
                plugin.getServer().broadcastMessage(TextFormat.RED + plugin.shutdownMessage + " in "+ Long.valueOf(seconds) +" Sekunden...");
        }, false);
    }
}
