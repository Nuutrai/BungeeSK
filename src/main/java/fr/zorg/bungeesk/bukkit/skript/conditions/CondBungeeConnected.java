package fr.zorg.bungeesk.bukkit.skript.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import fr.zorg.bungeesk.bukkit.sockets.ClientSettings;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Is bungeecord connected")
@Description("Checks if the server and the bungeecord are linked")
@Since("1.0.0")
@Examples("command /isconnected:\n" +
        "\ttrigger:\n" +
        "\t\tif {connection} is not connected:\n" +
        "\t\t\tsend \"Not working !\"\n" +
        "\t\tif {connection} is connected:\n" +
        "\t\t\tsend \"Working as well !\"")
public class CondBungeeConnected extends Condition {

    static {
        PropertyCondition.register(CondBungeeConnected.class, "connected", "bungeeconn");
    }

    private Expression<ClientSettings> bungee;

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        bungee = (Expression<ClientSettings>) exprs[0];
        setNegated(matchedPattern == 1);
        return true;
    }

    @Override
    public boolean check(Event e) {
        boolean isConn = bungee.getSingle(e).isConnected();
        if (isNegated()) {
            return !isConn;
        }
        return isConn;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "state of bungee connection";
    }
}
