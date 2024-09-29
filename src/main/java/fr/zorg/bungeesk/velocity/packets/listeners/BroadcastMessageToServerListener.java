package fr.zorg.bungeesk.velocity.packets.listeners;

import fr.zorg.bungeesk.common.packets.BroadcastMessagePacket;
import fr.zorg.bungeesk.common.packets.BroadcastMessageToServerPacket;
import fr.zorg.bungeesk.common.packets.BungeeSKPacket;
import fr.zorg.bungeesk.velocity.api.BungeeSKListener;
import fr.zorg.bungeesk.velocity.packets.SocketServer;
import fr.zorg.bungeesk.velocity.utils.VelocityUtils;

public class BroadcastMessageToServerListener extends BungeeSKListener {

    @Override
    public void onReceive(SocketServer socketServer, BungeeSKPacket packet) {
        if (packet instanceof BroadcastMessageToServerPacket) {
            final BroadcastMessageToServerPacket broadcastMessageToServerPacket = (BroadcastMessageToServerPacket) packet;
            final SocketServer server = VelocityUtils.getSocketFromBungeeServer(broadcastMessageToServerPacket.getBungeeServer());
            if (server != null)
                server.sendPacket(new BroadcastMessagePacket(broadcastMessageToServerPacket.getMessage()));
        }
    }
}