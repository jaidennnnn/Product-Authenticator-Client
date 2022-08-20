package gg.mineral.discord.packet.clientbound;

import gg.dragonfruit.network.Connection;
import gg.dragonfruit.network.packet.Packet;
import gg.mineral.discord.Authenticator;

public class InvalidKeyPacket extends Packet {

    @Override
    public void received(Connection connection) {
        Authenticator.failed();
    }

}
