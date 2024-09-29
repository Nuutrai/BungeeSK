package fr.zorg.bungeesk.velocity.packets.listeners;

import fr.zorg.bungeesk.common.packets.BungeeSKPacket;
import fr.zorg.bungeesk.common.packets.CompletableFutureResponsePacket;
import fr.zorg.bungeesk.velocity.api.BungeeSKListener;
import fr.zorg.bungeesk.velocity.packets.SocketServer;
import fr.zorg.bungeesk.velocity.utils.FutureUtils;

public class CompletableFutureResponseListener extends BungeeSKListener {

    @Override
    public void onReceive(SocketServer socketServer, BungeeSKPacket packet) {
        if (packet instanceof CompletableFutureResponsePacket) {
            final CompletableFutureResponsePacket completableFutureResponsePacket = (CompletableFutureResponsePacket) packet;
            FutureUtils.completeFuture(completableFutureResponsePacket.getUuid(), completableFutureResponsePacket.getResponse());
        }
    }
}
