package fr.zorg.bungeesk.velocity.packets.listeners;

import com.velocitypowered.api.proxy.Player;
import fr.zorg.bungeesk.common.entities.BungeePlayer;
import fr.zorg.bungeesk.common.packets.BungeeSKPacket;
import fr.zorg.bungeesk.common.packets.MakeBungeePlayerCommandPacket;
import fr.zorg.bungeesk.velocity.api.BungeeSKListener;
import fr.zorg.bungeesk.velocity.packets.SocketServer;
import fr.zorg.bungeesk.velocity.utils.VelocityUtils;

public class MakeBungeePlayerCommandListener extends BungeeSKListener {

    @Override
    public void onReceive(SocketServer socketServer, BungeeSKPacket packet) {
        if (packet instanceof MakeBungeePlayerCommandPacket) {
            final MakeBungeePlayerCommandPacket makeBungeePlayerCommandPacket = (MakeBungeePlayerCommandPacket) packet;
            final BungeePlayer bungeePlayer = makeBungeePlayerCommandPacket.getBungeePlayer();
            String command = makeBungeePlayerCommandPacket.getCommand();

            if (!command.startsWith("/"))
                command = "/" + command;

            final Player player = VelocityUtils.getPlayer(bungeePlayer);
            if (player != null)
                player.spoofChatInput(command);
        }
    }
}