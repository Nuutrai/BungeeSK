package fr.zorg.bungeesk.velocity.packets.listeners;

import fr.zorg.bungeesk.common.packets.BungeeSKPacket;
import fr.zorg.bungeesk.common.packets.SendMessageToConsolePacket;
import fr.zorg.bungeesk.velocity.BungeeSK;
import fr.zorg.bungeesk.velocity.api.BungeeSKListener;
import fr.zorg.bungeesk.velocity.packets.SocketServer;

public class SendMessageToConsoleListener extends BungeeSKListener {

    @Override
    public void onReceive(SocketServer socketServer, BungeeSKPacket packet) {
        if (packet instanceof SendMessageToConsolePacket) {
            final SendMessageToConsolePacket sendMessageToConsolePacket = (SendMessageToConsolePacket) packet;
            final String message = sendMessageToConsolePacket.getMessage();
            BungeeSK.getLogger().info(message);
        }
    }

}