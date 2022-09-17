package gg.mineral.discord.packet.clientbound;

import java.io.IOException;

import gg.dragonfruit.network.Connection;
import gg.dragonfruit.network.packet.Packet;
import gg.mineral.discord.Authenticator;
import io.netty.buffer.ByteBuf;

public class InvalidKeyPacket extends Packet {

    @Override
    public void received(Connection connection) {
        Authenticator.failed();
    }

    @Override
    public void deserialize(ByteBuf is) throws IOException {
    }

    @Override
    public void serialize(ByteBuf os) throws IOException {
    }

}
