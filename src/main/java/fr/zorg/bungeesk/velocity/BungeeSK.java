package fr.zorg.bungeesk.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import fr.zorg.bungeesk.common.AutoUpdater;
import fr.zorg.bungeesk.velocity.api.BungeeAPI;
import fr.zorg.bungeesk.velocity.commands.BungeeSKCommand;
import fr.zorg.bungeesk.velocity.packets.PacketServer;
import fr.zorg.bungeesk.velocity.storage.GlobalScripts;
import fr.zorg.bungeesk.velocity.storage.GlobalVariables;
import fr.zorg.bungeesk.velocity.utils.BungeeConfig;
import fr.zorg.bungeesk.velocity.utils.BungeeEventsListener;
import fr.zorg.bungeesk.velocity.utils.Metrics;
import org.slf4j.Logger;

import java.nio.file.Path;
import java.time.Duration;

public class BungeeSK {
    private static ProxyServer server;
    private static Logger logger;
    private static Path dataDirectory;
    private final Metrics.Factory metricsFactory;
    private static BungeeAPI api;
    private static BungeeSK instance;

    @Inject
    public BungeeSK(ProxyServer server, Logger logger, Metrics.Factory metricsFactory, @DataDirectory Path dataDirectory) {
        BungeeSK.server = server;
        BungeeSK.logger = logger;
        BungeeSK.dataDirectory = dataDirectory;
        this.metricsFactory = metricsFactory;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent e) {
        metricsFactory.make(this, 20201);

        BungeeSK.api = new BungeeAPI();
        BungeeSK.api.registerListeners("fr.zorg.bungeesk.velocity.packets.listeners");
        BungeeSK.instance = this;
        this.launchAutoUpdater();
        BungeeConfig.init();
        GlobalVariables.init();
        GlobalScripts.listenFileChanging();
        PacketServer.start();

        BungeeSK.getServer().getEventManager().register(this, new BungeeEventsListener());

        final CommandManager commandManager = BungeeSK.getServer().getCommandManager();
        BungeeSK.getServer().getCommandManager().register(commandManager.metaBuilder("bungeesk").plugin(this).build(), new BungeeSKCommand());
    }

    public static ProxyServer getServer() {
        return server;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static Path getDataDirectory() {
        return dataDirectory;
    }

    public static BungeeAPI getApi() {
        return api;
    }

    private void launchAutoUpdater() {
        BungeeSK.getServer().getScheduler().buildTask(this, () -> {
                    if (!AutoUpdater.isUpToDate(BungeeSK.getServer().getPluginManager().getPlugin("bungeesk").get().getDescription().getVersion().get())) {
                        BungeeSK.getLogger().atWarn().log("BungeeSK is not up to date ! Please download the latest version here: https//github.com/ZorgBtw/BungeeSK/releases/latest");
                    }
                })
                .repeat(Duration.ofDays(1))
                .schedule(); // Everyday
    }

    public static void runAsync(Runnable task) {
        BungeeSK.getServer().getScheduler().buildTask(BungeeSK.getInstance(), task).schedule();
    }

    public static BungeeSK getInstance() {
        return instance;
    }

}