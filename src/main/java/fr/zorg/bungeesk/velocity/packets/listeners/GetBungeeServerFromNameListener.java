package fr.zorg.bungeesk.velocity.packets.listeners;

import fr.zorg.bungeesk.common.entities.EmptyFutureResponse;
import fr.zorg.bungeesk.common.packets.BungeeSKPacket;
import fr.zorg.bungeesk.common.packets.GetBungeeServerFromNamePacket;
import fr.zorg.bungeesk.velocity.api.BungeeSKListener;
import fr.zorg.bungeesk.velocity.packets.SocketServer;
import fr.zorg.bungeesk.velocity.utils.VelocityUtils;

import java.util.UUID;

public class GetBungeeServerFromNameListener extends BungeeSKListener {

    @Override
    public Object onFutureRequest(UUID uuid, SocketServer socketServer, BungeeSKPacket packet) {
        if (packet instanceof GetBungeeServerFromNamePacket) {
            final GetBungeeServerFromNamePacket getBungeeServerFromNamePacket = (GetBungeeServerFromNamePacket) packet;
            final String name = getBungeeServerFromNamePacket.getName();
            return VelocityUtils.getServerFromName(name) == null ? new EmptyFutureResponse() : VelocityUtils.getServerFromName(name);
        }
        return null;
    }

}