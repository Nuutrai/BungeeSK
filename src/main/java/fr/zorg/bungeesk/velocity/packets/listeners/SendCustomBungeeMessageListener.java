package fr.zorg.bungeesk.velocity.packets.listeners;

import fr.zorg.bungeesk.common.entities.BungeeServer;
import fr.zorg.bungeesk.common.packets.BungeeSKPacket;
import fr.zorg.bungeesk.common.packets.CustomBungeeMessagePacket;
import fr.zorg.bungeesk.common.packets.SendCustomBungeeMessagePacket;
import fr.zorg.bungeesk.velocity.api.BungeeSKListener;
import fr.zorg.bungeesk.velocity.packets.SocketServer;
import fr.zorg.bungeesk.velocity.utils.VelocityUtils;

public class SendCustomBungeeMessageListener extends BungeeSKListener {

    @Override
    public void onReceive(SocketServer socketServer, BungeeSKPacket packet) {
        if (packet instanceof SendCustomBungeeMessagePacket) {
            final SendCustomBungeeMessagePacket sendCustomBungeeMessagePacket = (SendCustomBungeeMessagePacket) packet;
            for (BungeeServer server : sendCustomBungeeMessagePacket.getServers()) {
                final SocketServer socketServer1 = VelocityUtils.getSocketFromBungeeServer(server);
                if (socketServer1 != null)
                    socketServer1.sendPacket(new CustomBungeeMessagePacket(VelocityUtils.getServerFromSocket(socketServer), sendCustomBungeeMessagePacket.getMessage()));
            }
        }
    }

}