package fr.zorg.bungeesk.velocity.packets.listeners;

import fr.zorg.bungeesk.common.packets.BungeeSKPacket;
import fr.zorg.bungeesk.common.packets.CompletableFuturePacket;
import fr.zorg.bungeesk.velocity.api.BungeeSKListener;
import fr.zorg.bungeesk.velocity.packets.SocketServer;
import fr.zorg.bungeesk.velocity.utils.FutureUtils;

public class CompletableFutureListener extends BungeeSKListener {

    @Override
    public void onReceive(SocketServer socketServer, BungeeSKPacket packet) {
        if (packet instanceof CompletableFuturePacket) {
            final CompletableFuturePacket completableFuturePacket = (CompletableFuturePacket) packet;
            FutureUtils.completeFuture(socketServer, completableFuturePacket.getUuid(), completableFuturePacket.getPacket());
        }
    }

}