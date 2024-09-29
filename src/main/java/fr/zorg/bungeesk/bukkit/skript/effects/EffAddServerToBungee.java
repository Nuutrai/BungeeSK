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
import fr.zorg.bungeesk.common.entities.BungeeServerBuilder;
import fr.zorg.bungeesk.common.packets.AddServerToBungeePacket;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

@Name("Add Server to Proxy")
@Description("Adds a server into the bungeecord proxy.")
@Examples("put {_builder} into bungeecord")
@Since("1.1.0")
public class EffAddServerToBungee extends Effect {

    static {
        Skript.registerEffect(EffAddServerToBungee.class,
                "put [dynamic server] %serverbuilder% into bungeecord");
    }

    private Expression<BungeeServerBuilder> bungeeServerBuilder;

    @Override
    public boolean init(Expression<?>[] exprs, int patter, @NotNull Kleenean kleenean, SkriptParser.@NotNull ParseResult parseResult) {
        this.bungeeServerBuilder = (Expression<BungeeServerBuilder>) exprs[0];
        return true;
    }

    @Override
    protected void execute(@NotNull Event e) {
        final BungeeServerBuilder builder = bungeeServerBuilder.getSingle(e);
        if (builder == null || builder.getIp() == null || builder.getPort() == 0 || builder.getName() == null || builder.getMotd() == null)
            return;

        final AddServerToBungeePacket packet = new AddServerToBungeePacket(builder);
        PacketClient.sendPacket(packet);
    }

    @Override
    public @NotNull String toString(Event e, boolean debug) {
        return "put server " + this.bungeeServerBuilder.toString(e, debug) + " into bungeecord";
    }
}