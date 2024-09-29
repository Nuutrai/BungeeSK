package fr.zorg.bungeesk.velocity.packets.listeners;

import fr.zorg.bungeesk.common.packets.BroadcastToNetworkPacket;
import fr.zorg.bungeesk.common.packets.BungeeSKPacket;
import fr.zorg.bungeesk.velocity.BungeeSK;
import fr.zorg.bungeesk.velocity.api.BungeeSKListener;
import fr.zorg.bungeesk.velocity.packets.SocketServer;
import fr.zorg.bungeesk.velocity.utils.VelocityUtils;

public class BroadcastToNetworkListener extends BungeeSKListener {

    @Override
    public void onReceive(SocketServer socketServer, BungeeSKPacket packet) {
        if (packet instanceof BroadcastToNetworkPacket) {
            final String message = ((BroadcastToNetworkPacket) packet).getMessage();
            BungeeSK.getServer().sendMessage(VelocityUtils.getTextComponent(message));
        }
    }

}