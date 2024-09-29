package fr.zorg.bungeesk.velocity.packets.listeners;

import fr.zorg.bungeesk.common.packets.AuthResponsePacket;
import fr.zorg.bungeesk.common.packets.BungeeSKPacket;
import fr.zorg.bungeesk.velocity.api.BungeeSKListener;
import fr.zorg.bungeesk.velocity.packets.SocketServer;

import java.util.UUID;

public class AuthResponseListener extends BungeeSKListener {

    @Override
    public void onReceive(SocketServer socketServer, BungeeSKPacket packet) {
        if (packet instanceof AuthResponsePacket) {
            final AuthResponsePacket authResponsePacket = (AuthResponsePacket) packet;
            final UUID uuid = authResponsePacket.getDecryptedUuid();
            socketServer.completeChallenge(uuid);
        }
    }

}