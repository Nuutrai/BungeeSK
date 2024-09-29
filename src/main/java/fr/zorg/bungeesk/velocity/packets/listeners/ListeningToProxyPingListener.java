package fr.zorg.bungeesk.velocity.packets.listeners;

import fr.zorg.bungeesk.common.packets.BungeeSKPacket;
import fr.zorg.bungeesk.common.packets.ListeningToProxyPingPacket;
import fr.zorg.bungeesk.velocity.api.BungeeSKListener;
import fr.zorg.bungeesk.velocity.packets.SocketServer;
import fr.zorg.bungeesk.velocity.utils.PingUtils;

public class ListeningToProxyPingListener extends BungeeSKListener {

    @Override
    public void onReceive(SocketServer socketServer, BungeeSKPacket packet) {
        if (packet instanceof ListeningToProxyPingPacket) {
            PingUtils.setPingListener(socketServer);
        }
    }

}