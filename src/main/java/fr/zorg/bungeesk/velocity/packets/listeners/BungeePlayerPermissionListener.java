package fr.zorg.bungeesk.velocity.packets.listeners;

import com.velocitypowered.api.proxy.Player;
import fr.zorg.bungeesk.common.entities.BungeePlayer;
import fr.zorg.bungeesk.common.packets.BungeePlayerPermissionPacket;
import fr.zorg.bungeesk.common.packets.BungeeSKPacket;
import fr.zorg.bungeesk.velocity.api.BungeeSKListener;
import fr.zorg.bungeesk.velocity.packets.SocketServer;
import fr.zorg.bungeesk.velocity.utils.VelocityUtils;

import java.util.UUID;

public class BungeePlayerPermissionListener extends BungeeSKListener {

    @Override
    public Object onFutureRequest(UUID uuid, SocketServer socketServer, BungeeSKPacket packet) {
        if (packet instanceof BungeePlayerPermissionPacket) {
            final BungeePlayerPermissionPacket bungeePlayerPermissionPacket = (BungeePlayerPermissionPacket) packet;
            final BungeePlayer bungeePlayer = bungeePlayerPermissionPacket.getBungeePlayer();
            final Player player = VelocityUtils.getPlayer(bungeePlayer);
            if (player == null)
                return false;

            return player.hasPermission(bungeePlayerPermissionPacket.getPermission());
        }
        return null;
    }

}