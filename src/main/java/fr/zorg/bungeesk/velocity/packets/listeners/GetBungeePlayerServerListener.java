package fr.zorg.bungeesk.velocity.packets.listeners;

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ServerConnection;
import fr.zorg.bungeesk.common.entities.EmptyFutureResponse;
import fr.zorg.bungeesk.common.packets.BungeeSKPacket;
import fr.zorg.bungeesk.common.packets.GetBungeePlayerServerPacket;
import fr.zorg.bungeesk.velocity.api.BungeeSKListener;
import fr.zorg.bungeesk.velocity.packets.SocketServer;
import fr.zorg.bungeesk.velocity.utils.VelocityUtils;

import java.util.Optional;
import java.util.UUID;

public class GetBungeePlayerServerListener extends BungeeSKListener {

    @Override
    public Object onFutureRequest(UUID uuid, SocketServer socketServer, BungeeSKPacket packet) {
        if (packet instanceof GetBungeePlayerServerPacket) {
            final GetBungeePlayerServerPacket getBungeePlayerServerPacket = (GetBungeePlayerServerPacket) packet;
            final Player player = VelocityUtils.getPlayer(getBungeePlayerServerPacket.getBungeePlayer());
            if (player == null)
                return new EmptyFutureResponse();

            final Optional<ServerConnection> serverConnectionOptional = player.getCurrentServer();

            if (serverConnectionOptional.isEmpty())
                return new EmptyFutureResponse();

            return VelocityUtils.getServerFromInfo(serverConnectionOptional.get().getServerInfo());
        }
        return null;
    }

}