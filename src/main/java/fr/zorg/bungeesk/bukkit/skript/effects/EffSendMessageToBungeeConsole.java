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
import fr.zorg.bungeesk.common.packets.SendMessageToConsolePacket;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

@Name("Send Message to Proxy Console")
@Description("Send a message to the proxy console")
@Examples("send \"abc\" to bungee console")
@Since("1.0.0")
public class EffSendMessageToBungeeConsole extends Effect {

    static {
        Skript.registerEffect(EffSendMessageToBungeeConsole.class,
                "send %string% to bungee console");
    }

    private Expression<String> message;

    @Override
    public boolean init(Expression<?>[] exprs, int pattern, @NotNull Kleenean kleenean, SkriptParser.@NotNull ParseResult parseResult) {
        this.message = (Expression<String>) exprs[0];
        return true;
    }

    @Override
    protected void execute(@NotNull Event e) {
        PacketClient.sendPacket(new SendMessageToConsolePacket(this.message.getSingle(e)));
    }

    @Override
    public @NotNull String toString(Event event, boolean b) {
        return "send " + this.message.toString(event, b) + " to bungee console";
    }

}