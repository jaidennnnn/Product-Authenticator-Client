package gg.mineral.discord.packet.clientbound;

import java.io.IOException;

import gg.dragonfruit.netty.buffer.ByteBuf;
import gg.dragonfruit.network.Connection;
import gg.dragonfruit.network.packet.Packet;
import gg.mineral.discord.Authenticator;

public class RequestExpiredPacket extends Packet {

    @Override
    public void received(Connection connection) {
        Authenticator.expired();
    }

    @Override
    public void deserialize(ByteBuf is) throws IOException {
    }

    @Override
    public void serialize(ByteBuf os) throws IOException {
    }

}
