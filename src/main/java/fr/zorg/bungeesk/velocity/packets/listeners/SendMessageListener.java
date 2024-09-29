package fr.zorg.bungeesk.velocity.packets.listeners;

import com.velocitypowered.api.proxy.Player;
import fr.zorg.bungeesk.common.entities.BungeePlayer;
import fr.zorg.bungeesk.common.packets.BungeeSKPacket;
import fr.zorg.bungeesk.common.packets.SendMessagePacket;
import fr.zorg.bungeesk.velocity.api.BungeeSKListener;
import fr.zorg.bungeesk.velocity.packets.SocketServer;
import fr.zorg.bungeesk.velocity.utils.VelocityUtils;

public class SendMessageListener extends BungeeSKListener {

    @Override
    public void onReceive(SocketServer socketServer, BungeeSKPacket packet) {
        if (packet instanceof SendMessagePacket) {
            final SendMessagePacket sendMessagePacket = (SendMessagePacket) packet;
            final BungeePlayer bungeePlayer = sendMessagePacket.getBungeePlayer();
            final String message = sendMessagePacket.getMessage();
            final Player player = VelocityUtils.getPlayer(bungeePlayer);
            if (player == null)
                return;

            player.sendMessage(VelocityUtils.getTextComponent(message));
        }
    }

}