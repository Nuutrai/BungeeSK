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
import fr.zorg.bungeesk.bukkit.utils.ClientBuilder;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Start New Proxy Connection")
@Description("Start a new connection to bungeecord")
@Since("1.0.0")
@Examples("on load:\n" +
        "\tcreate new bungee connection:\n" +
        "\t\tset address of connection to \"127.0.0.1\"\n" +
        "\t\tset port of connection to 20000\n" +
        "\t\tset password of connection to \"abcd\"\n" +
        "\tstart new connection with connection")
public class EffStartConnection extends Effect {

    static {
        Skript.registerEffect(EffStartConnection.class, "start new connection with %bungeeconn%");
    }

    private Expression<ClientBuilder> clientBuilderExpression;

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        this.clientBuilderExpression = (Expression<ClientBuilder>) exprs[0];
        return true;
    }

    @Override
    protected void execute(@NotNull Event e) {
        PacketClient.start(clientBuilderExpression.getSingle(e));
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "start connection with bungee connection";
    }

}