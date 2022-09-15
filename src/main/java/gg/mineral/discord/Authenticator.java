package gg.mineral.discord;

import java.io.IOException;
import java.net.InetSocketAddress;

import gg.dragonfruit.network.ClientHandler;
import gg.dragonfruit.network.NetworkLibrary;
import gg.dragonfruit.network.collection.GlueList;
import gg.dragonfruit.network.packet.Packet;
import gg.mineral.discord.packet.bidirectional.CheckKeyPacket;
import gg.mineral.discord.packet.clientbound.InvalidKeyPacket;
import gg.mineral.discord.packet.clientbound.RequestExpiredPacket;
import gg.mineral.discord.util.CC;
import gg.mineral.discord.util.ConsoleUtil;

public class Authenticator {

    static String productKey = null;
    static String productName = null;
    static Runnable whenAccepted = null;

    public static void checkKey(String productKey, String ipAddress, int port, String productName,
            final Runnable whenAccepted)
            throws InterruptedException, IOException {
        Authenticator.whenAccepted = whenAccepted;
        Authenticator.productKey = productKey;

        if (productKey == null) {
            ConsoleUtil.send(CC.YELLOW,
                    "Please give a product key.");
            Thread.sleep(3000);
            System.exit(0);
        }

        GlueList<Class<? extends Packet>> packetClasses = new GlueList<>();
        packetClasses.add(CheckKeyPacket.class);
        packetClasses.add(InvalidKeyPacket.class);
        packetClasses.add(RequestExpiredPacket.class);

        NetworkLibrary
                .startClient(new InetSocketAddress(ipAddress, port), packetClasses);
        ClientHandler.getServerConnection().sendPacket(
                new gg.mineral.discord.packet.bidirectional.CheckKeyPacket(
                        productKey,
                        Authenticator.productName = productName));
        ConsoleUtil.send(CC.CYAN,
                "Please check your private messages on discord.");

    }

    public static void checkProductKey(String receivedProductKey, String receivedProductName) {
        if (receivedProductKey.equalsIgnoreCase(productKey) && receivedProductName.equalsIgnoreCase(productName)) {
            productKey = null;
            productName = null;
            whenAccepted.run();
        }
    }

    public static void failed() {
        ConsoleUtil.send(CC.YELLOW, "The product key is invalid.");
        try {
            Thread.sleep(3000);
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void expired() {
        ConsoleUtil.send(CC.YELLOW, "Your request has expired. Please try again.");

        try {
            Thread.sleep(3000);
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
