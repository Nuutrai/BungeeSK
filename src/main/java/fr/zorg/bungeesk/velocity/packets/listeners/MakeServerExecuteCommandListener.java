package fr.zorg.bungeesk.velocity.packets.listeners;

import fr.zorg.bungeesk.common.entities.BungeeServer;
import fr.zorg.bungeesk.common.packets.BungeeSKPacket;
import fr.zorg.bungeesk.common.packets.ExecuteCommandPacket;
import fr.zorg.bungeesk.common.packets.MakeServerExecuteCommandPacket;
import fr.zorg.bungeesk.velocity.BungeeSK;
import fr.zorg.bungeesk.velocity.api.BungeeSKListener;
import fr.zorg.bungeesk.velocity.packets.PacketServer;
import fr.zorg.bungeesk.velocity.packets.SocketServer;
import fr.zorg.bungeesk.velocity.utils.VelocityUtils;

public class MakeServerExecuteCommandListener extends BungeeSKListener {

    @Override
    public void onReceive(SocketServer socketServer, BungeeSKPacket packet) {
        if (packet instanceof MakeServerExecuteCommandPacket) {
            final MakeServerExecuteCommandPacket makeServerExecuteCommandPacket = (MakeServerExecuteCommandPacket) packet;
            final String command = makeServerExecuteCommandPacket.getCommand();
            final Object toSend = makeServerExecuteCommandPacket.getToSend();
            if (toSend instanceof String) {
                if (((String) toSend).equalsIgnoreCase("all")) {
                    PacketServer.broadcastPacket(new ExecuteCommandPacket(command));
                    return;
                }
                BungeeSK.getServer().getCommandManager().executeAsync(BungeeSK.getServer().getConsoleCommandSource(), command);
                return;
            }
            if (toSend instanceof BungeeServer) {
                final BungeeServer bungeeServer = (BungeeServer) toSend;
                final SocketServer server = VelocityUtils.getSocketFromBungeeServer(bungeeServer);
                if (server != null)
                    server.sendPacket(new ExecuteCommandPacket(command));
            }
        }
    }

}