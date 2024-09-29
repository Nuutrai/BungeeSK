package fr.zorg.bungeesk.velocity.packets.listeners;

import fr.zorg.bungeesk.common.entities.BungeeServer;
import fr.zorg.bungeesk.common.entities.EmptyFutureResponse;
import fr.zorg.bungeesk.common.packets.BungeeSKPacket;
import fr.zorg.bungeesk.common.packets.GetBungeeServerOnlineStatusPacket;
import fr.zorg.bungeesk.velocity.api.BungeeSKListener;
import fr.zorg.bungeesk.velocity.packets.PacketServer;
import fr.zorg.bungeesk.velocity.packets.SocketServer;
import fr.zorg.bungeesk.velocity.utils.VelocityUtils;

import java.util.UUID;

public class GetBungeeServerOnlineStatusListener extends BungeeSKListener {

    @Override
    public Object onFutureRequest(UUID uuid, SocketServer socketServer, BungeeSKPacket packet) {
        if (packet instanceof GetBungeeServerOnlineStatusPacket) {
            final GetBungeeServerOnlineStatusPacket getBungeeServerOnlineStatusPacket = (GetBungeeServerOnlineStatusPacket) packet;
            final BungeeServer bungeeServer = getBungeeServerOnlineStatusPacket.getServer();
            final SocketServer server = VelocityUtils.getSocketFromBungeeServer(bungeeServer);

            if (server == null)
                return new EmptyFutureResponse();

            return PacketServer.getClientSockets().contains(server);
        }
        return null;
    }

}