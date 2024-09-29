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
import fr.zorg.bungeesk.common.packets.DeleteServerFromBungeePacket;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

@Name("Delete Server from Proxy")
@Description("Delete a dynamic server from Bungeecord.")
@Examples("delete server named \"lobby2\" from bungeecord")
@Since("1.1.1")
public class EffDeleteServerFromBungee extends Effect {

    static {
        Skript.registerEffect(EffDeleteServerFromBungee.class,
                "delete server named %string% from [the] bungeecord");
    }

    private Expression<String> name;

    @Override
    public boolean init(Expression<?>[] exprs, int pattern, @NotNull Kleenean kleenean, SkriptParser.@NotNull ParseResult parseResult) {
        this.name = (Expression<String>) exprs[0];
        return true;
    }

    @Override
    protected void execute(@NotNull Event e) {
        PacketClient.sendPacket(new DeleteServerFromBungeePacket(this.name.getSingle(e)));
    }

    @Override
    public @NotNull String toString(Event e, boolean debug) {
        return "delete server named " + this.name.getSingle(e) + " from bungeecord";
    }

}