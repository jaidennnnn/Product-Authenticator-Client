package gg.mineral.discord.packet.clientbound;

import java.io.IOException;

import gg.dragonfruit.network.Connection;
import gg.dragonfruit.network.packet.Packet;
import gg.dragonfruit.network.util.DragonfruitInputStream;
import gg.dragonfruit.network.util.DragonfruitOutputStream;
import gg.mineral.discord.Authenticator;

public class RequestExpiredPacket extends Packet {

    @Override
    public void received(Connection connection) {
        Authenticator.expired();
    }

    @Override
    public void deserialize(DragonfruitInputStream is) throws IOException {
    }

    @Override
    public void serialize(DragonfruitOutputStream os) throws IOException {
    }

}
