package gg.mineral.discord.packet.bidirectional;

import java.io.IOException;

import gg.dragonfruit.network.Connection;
import gg.dragonfruit.network.encryption.EndToEndEncryption;
import gg.dragonfruit.network.packet.DHEncryptedPacket;
import gg.dragonfruit.network.util.DragonfruitInputStream;
import gg.dragonfruit.network.util.DragonfruitOutputStream;
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

    @Override
    public void deserialize(DragonfruitInputStream is) throws IOException {
        this.key = is.readStringUTF();
        this.productName = is.readStringUTF();
    }

    @Override
    public void serialize(DragonfruitOutputStream os) throws IOException {
        os.writeStringUTF(this.key);
        os.writeStringUTF(this.productName);
    }

}
