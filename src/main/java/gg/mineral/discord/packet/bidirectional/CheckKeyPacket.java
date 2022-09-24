package gg.mineral.discord.packet.bidirectional;

import java.io.IOException;

import gg.dragonfruit.network.Connection;
import gg.dragonfruit.network.encryption.EndToEndEncryption;
import gg.dragonfruit.network.packet.DHEncryptedPacket;
import gg.dragonfruit.network.util.PacketUtil;
import gg.mineral.discord.Authenticator;
import io.netty.buffer.ByteBuf;

public class CheckKeyPacket extends DHEncryptedPacket {

    String key, productName;

    public CheckKeyPacket(String key, String productName) {
        this.key = key;
        this.productName = productName;
    }

    public CheckKeyPacket() {

    }

    @Override
    public void decrypt(EndToEndEncryption endToEndEncryption) {
        this.key = endToEndEncryption.decrypt(key);
    }

    @Override
    public void encrypt(EndToEndEncryption endToEndEncryption) {
        this.key = endToEndEncryption.encrypt(key);
    }

    @Override
    public void received(Connection connection) {
        Authenticator.checkProductKey(key, productName);
    }

    @Override
    public void deserialize(ByteBuf is) throws IOException {
        this.key = PacketUtil.readString(is);
        this.productName = PacketUtil.readString(is);
    }

    @Override
    public void serialize(ByteBuf os) throws IOException {
        PacketUtil.writeString(this.key, os);
        PacketUtil.writeString(this.productName, os);
    }

}
