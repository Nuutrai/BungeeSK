package fr.zorg.bungeesk.velocity.packets.listeners;

import fr.zorg.bungeesk.common.entities.EmptyFutureResponse;
import fr.zorg.bungeesk.common.packets.BungeeSKPacket;
import fr.zorg.bungeesk.common.packets.GetRequestFromOtherServerPacket;
import fr.zorg.bungeesk.velocity.api.BungeeSKListener;
import fr.zorg.bungeesk.velocity.packets.SocketServer;
import fr.zorg.bungeesk.velocity.utils.FutureUtils;
import fr.zorg.bungeesk.velocity.utils.VelocityUtils;

import java.util.UUID;

public class GetRequestFromOtherServerListener extends BungeeSKListener {

    @Override
    public Object onFutureRequest(UUID uuid, SocketServer socketServer, BungeeSKPacket packet) {
        if (packet instanceof GetRequestFromOtherServerPacket) {
            final GetRequestFromOtherServerPacket getRequestFromOtherServerPacket = (GetRequestFromOtherServerPacket) packet;
            getRequestFromOtherServerPacket.setFrom(VelocityUtils.getServerFromSocket(socketServer));
            final SocketServer otherServer = VelocityUtils.getSocketFromBungeeServer(getRequestFromOtherServerPacket.getServer());
            final Object response = FutureUtils.generateFuture(otherServer, getRequestFromOtherServerPacket);

            return response == null ? new EmptyFutureResponse() : response;
        }
        return null;
    }

}