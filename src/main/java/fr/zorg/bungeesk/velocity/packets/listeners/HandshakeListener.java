package fr.zorg.bungeesk.velocity.packets.listeners;

import fr.zorg.bungeesk.common.packets.AuthRequestPacket;
import fr.zorg.bungeesk.common.packets.BungeeSKPacket;
import fr.zorg.bungeesk.common.packets.HandshakePacket;
import fr.zorg.bungeesk.common.utils.EncryptionUtils;
import fr.zorg.bungeesk.velocity.api.BungeeSKListener;
import fr.zorg.bungeesk.velocity.packets.SocketServer;
import fr.zorg.bungeesk.velocity.utils.BungeeConfig;
import fr.zorg.bungeesk.velocity.utils.Debug;

import java.util.UUID;

public class HandshakeListener extends BungeeSKListener {

    @Override
    public void onReceive(SocketServer socketServer, BungeeSKPacket packet) {
        if (packet instanceof HandshakePacket) {
            socketServer.setMinecraftPort(((HandshakePacket) packet).getMinecraftPort());
            final UUID uuid = socketServer.initChallenge();
            try {
                final byte[] encryptedUUID = EncryptionUtils.encryptUUID(uuid, ((String) BungeeConfig.PASSWORD.get()).toCharArray());
                socketServer.sendPacket(new AuthRequestPacket(encryptedUUID));
            } catch (Exception ex) {
                Debug.log("Error while encrypting UUID");
                socketServer.disconnect();
            }
        }
    }

}