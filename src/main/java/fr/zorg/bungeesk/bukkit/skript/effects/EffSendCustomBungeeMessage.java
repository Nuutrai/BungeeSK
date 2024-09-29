package fr.zorg.bungeesk.bukkit.skript.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import fr.zorg.bungeesk.bukkit.packets.PacketClient;
import fr.zorg.bungeesk.common.entities.BungeeServer;
import fr.zorg.bungeesk.common.packets.SendCustomBungeeMessagePacket;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

@Name("Send Message To BungeeCord Server")
@Description("Sends a custom bungee message in string form to one or more servers")
@Examples("send custom message \"This is an example\" to bungee server named \"lobby2\"")
@Since("1.1.0")
public class EffSendCustomBungeeMessage extends Effect {

    static {
        Skript.registerEffect(EffSendCustomBungeeMessage.class,
                "send (custom|bungee[cord]) message %string% to %bungeeservers%");
    }

    private Expression<String> message;
    private Expression<BungeeServer> servers;

    @Override
    public boolean init(Expression<?>[] exprs, int pattern, @NotNull Kleenean kleenean, SkriptParser.@NotNull ParseResult parseResult) {
        this.message = (Expression<String>) exprs[0];
        this.servers = (Expression<BungeeServer>) exprs[1];
        return true;
    }

    @Override
    protected void execute(@NotNull Event e) {
        final SendCustomBungeeMessagePacket packet = new SendCustomBungeeMessagePacket(new ArrayList<>(Arrays.asList(this.servers.getArray(e))), this.message.getSingle(e));
        PacketClient.sendPacket(packet);
    }

    @Override
    public @NotNull String toString(Event e, boolean debug) {
        return "send custom message "+message.toString(e, debug)+" to "+servers.toString(e, debug);
    }

}