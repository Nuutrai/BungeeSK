package fr.zorg.bungeesk.bukkit.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import fr.zorg.bungeesk.bukkit.skript.events.bukkit.*;
import fr.zorg.bungeesk.common.entities.BungeePlayer;
import fr.zorg.bungeesk.common.entities.BungeeServer;
import org.jetbrains.annotations.Nullable;

public class SkriptEvents {
    static {
        Skript.registerEvent("bungee command", SimpleEvent.class, BungeeCommandEvent.class,
                        "bungee command")
                .description("When a command is executed on the network, this could be a Spigot command or a Bungee command")
                .examples("on bungee command:", "\tset {_command} to event-string", "\tset {_player} to event-bungeeplayer")
                .since("2.0.0");
        EventValues.registerEventValue(BungeeCommandEvent.class, BungeePlayer.class, new Getter<>() {
            @Nullable
            @Override
            public BungeePlayer get(BungeeCommandEvent e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(BungeeCommandEvent.class, String.class, new Getter<>() {
            @Nullable
            @Override
            public String get(BungeeCommandEvent e) {
                return e.getCommand();
            }
        }, 0);

        Skript.registerEvent("bungee message receive", SimpleEvent.class, BungeeMessageReceiveEvent.class,
                        "bungee [custom] message receive")
                .description("When a bungee message is received")
                .examples("on bungee message receive:", "\tset {_server} to event-bungeeserver", "\tset {_message} to event-string")
                .since("1.1.0");
        EventValues.registerEventValue(BungeeMessageReceiveEvent.class, BungeeServer.class, new Getter<>() {
            @Nullable
            @Override
            public BungeeServer get(BungeeMessageReceiveEvent e) {
                return e.getFrom();
            }
        }, 0);
        EventValues.registerEventValue(BungeeMessageReceiveEvent.class, String.class, new Getter<>() {
            @Nullable
            @Override
            public String get(BungeeMessageReceiveEvent e) {
                return e.getMessage();
            }
        }, 0);

        Skript.registerEvent("proxy ping", SimpleEvent.class, BungeePingEvent.class,
                        "(proxy|bungee) ping")
                .description("When the proxy is being ping by a player. Need to inform the proxy " +
                        "that this server is listening to this event with the 'Listen to proxy ping' effect")
                .examples("on proxy ping:", "\tset max players to 10", "\tset connected players to 5", "\t#And so on...")
                .since("2.0.0");

        Skript.registerEvent("bungee player join", SimpleEvent.class, BungeePlayerJoinEvent.class,
                        "bungee [player] join")
                .description("When a bungee player joins the network")
                .examples("on bungee player join:", "\tset {_player} to event-bungeeplayer")
                .since("1.0.0");
        EventValues.registerEventValue(BungeePlayerJoinEvent.class, BungeePlayer.class, new Getter<>() {
            @Nullable
            @Override
            public BungeePlayer get(BungeePlayerJoinEvent e) {
                return e.getPlayer();
            }
        }, 0);

        Skript.registerEvent("bungee player leave", SimpleEvent.class, BungeePlayerLeaveEvent.class,
                        "bungee [player] (leave|quit)")
                .description("When a bungee player leaves the network")
                .examples("on bungee player leave:", "\tset {_player} to event-bungeeplayer",
                        "on bungee player quit:" +
                                "\tbroadcast \"The player was in the %past-server% server !\"")
                .since("1.0.0");
        EventValues.registerEventValue(BungeePlayerLeaveEvent.class, BungeePlayer.class, new Getter<>() {
            @Nullable
            @Override
            public BungeePlayer get(BungeePlayerLeaveEvent e) {
                return e.getPlayer();
            }
        }, 0);

        Skript.registerEvent("bungee server start", SimpleEvent.class, BungeeServerStartEvent.class,
                        "bungee server (start|connect)")
                .description("When a bungee server is started / connected to BungeeSK")
                .examples("on bungee server start:", "\tset {_server} to event-bungeeserver")
                .since("2.0.0");
        EventValues.registerEventValue(BungeeServerStartEvent.class, BungeeServer.class, new Getter<>() {
            @Nullable
            @Override
            public BungeeServer get(BungeeServerStartEvent e) {
                return e.getBungeeServer();
            }
        }, 0);

        Skript.registerEvent("bungee server stop", SimpleEvent.class, BungeeServerStopEvent.class,
                        "bungee server (stop|disconnect)")
                .description("When a bungee server is stop / disconnected from BungeeSK")
                .examples("on bungee server stop:", "\tset {_server} to event-bungeeserver")
                .since("2.0.0");
        EventValues.registerEventValue(BungeeServerStopEvent.class, BungeeServer.class, new Getter<>() {
            @Nullable
            @Override
            public BungeeServer get(BungeeServerStopEvent e) {
                return e.getBungeeServer();
            }
        }, 0);

        Skript.registerEvent("client connect", SimpleEvent.class, ClientConnectEvent.class,
                        "[bungee] client connect")
                .description("When the client connects to a server")
                .examples("on bungee client connect:", "\tbroadcast \"&aClient connected !\"")
                .since("1.0.0");

        Skript.registerEvent("client disconnect", SimpleEvent.class, ClientDisconnectEvent.class,
                        "[bungee] client disconnect")
                .description("When the client disconnects from the server")
                .examples("on bungee client disconnect:", "\tbroadcast \"&cClient disconnected !\"")
                .since("1.0.0");

        Skript.registerEvent("bungee custom request", SimpleEvent.class, CustomRequestEvent.class,
                        "[bungee] custom request")
                .description("When a custom request is asked from another BungeeServer")
                .examples("broadcast custom request \"hello\" from bungee server named \"lobby\"",
                        "",
                        "#On the other server",
                        "on custom request:",
                        "\tif event-string is \"hello\":",
                        "\t\tset custom request response to \"hi !\"")
                .since("2.0.0");
        EventValues.registerEventValue(CustomRequestEvent.class, String.class, new Getter<>() {
            @Nullable
            @Override
            public String get(CustomRequestEvent e) {
                return e.getName();
            }
        }, 0);
        EventValues.registerEventValue(CustomRequestEvent.class, BungeeServer.class, new Getter<>() {
            @Nullable
            @Override
            public BungeeServer get(CustomRequestEvent e) {
                return e.getFrom();
            }
        }, 0);

        Skript.registerEvent("global script receive", SimpleEvent.class, GlobalScriptReceiveEvent.class,
                        "(bungee|global) script receive")
                .description("When a global script is received")
                .examples("on global script receive:", "\tset {_name} to event-string")
                .since("2.0.0");
        EventValues.registerEventValue(GlobalScriptReceiveEvent.class, String.class, new Getter<>() {
            @Nullable
            @Override
            public String get(GlobalScriptReceiveEvent e) {
                return e.getScriptName();
            }
        }, 0);

        Skript.registerEvent("server switch", SimpleEvent.class, ServerSwitchEvent.class,
                        "[bungee] server switch")
                .description("When the player switches between 2 servers")
                .examples("on server switch:", "\tbroadcast \"&6%event-bungeeplayer% &7switched from server &c%past-server% &7to &a%event-bungeeplayer's server% &7!\"")
                .since("1.0.0");
        EventValues.registerEventValue(ServerSwitchEvent.class, BungeePlayer.class, new Getter<>() {
            @Nullable
            @Override
            public BungeePlayer get(ServerSwitchEvent e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(ServerSwitchEvent.class, BungeeServer.class, new Getter<>() {
            @Nullable
            @Override
            public BungeeServer get(ServerSwitchEvent e) {
                return e.getServer();
            }
        }, 0);
    }
}
