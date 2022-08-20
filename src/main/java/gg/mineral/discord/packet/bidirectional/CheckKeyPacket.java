package gg.mineral.discord.packet.bidirectional;

import gg.dragonfruit.network.Connection;
import gg.dragonfruit.network.encryption.EndToEndEncryption;
import gg.dragonfruit.network.packet.DHEncryptedPacket;
import gg.mineral.discord.Authenticator;

public class CheckKeyPacket extends DHEncryptedPacket {

    String key, productName;

    public CheckKeyPacket(String key, String productName) {
        this.key = key;
        this.productName = productName;
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

}
