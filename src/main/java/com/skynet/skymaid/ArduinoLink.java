package com.skynet.skymaid;

import static org.ardulink.core.convenience.Links.setChoiceValues;
import java.util.concurrent.TimeUnit;

import org.ardulink.core.Pin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ardulink.core.Connection;
import org.ardulink.core.ConnectionBasedLink;
import org.ardulink.core.Link;
import org.ardulink.core.linkmanager.LinkManager;
import org.ardulink.util.URIs;

public class ArduinoLink {

    private String connString = "ardulink://serial";

    private Link link;

    private static final Logger logger = LoggerFactory.getLogger(ArduinoLink.class);

    public static void main(String[] args) throws Exception {
        new ArduinoLink().work();
    }


    private void work() throws Exception {
        this.link = createLink();

        try {
            logger.info("Wait a while for Arduino boot");
            TimeUnit.SECONDS.sleep(3);
            logger.info("Ok, now it should be ready...");

            if (link instanceof ConnectionBasedLink) {
                ((ConnectionBasedLink) link).getConnection().addListener(
                        rawDataListener());
            }
            link.sendCustomMessage("9", "0");
            TimeUnit.SECONDS.sleep(3);
            link.sendCustomMessage("9", "90");
            TimeUnit.SECONDS.sleep(3);
            link.sendCustomMessage("9", "180");
            TimeUnit.SECONDS.sleep(3);
            link.sendCustomMessage("9", "90");
            TimeUnit.SECONDS.sleep(3);
            link.sendCustomMessage("9", "0");
            TimeUnit.SECONDS.sleep(3);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    private Connection.Listener rawDataListener() {
        return new Connection.ListenerAdapter() {
            @Override
            public void received(byte[] bytes) {
                logger.info("Message from Arduino: %s", new String(bytes));
            }
        };
    }

    private Link createLink() {
        return setChoiceValues(LinkManager.getInstance().getConfigurer(URIs.newURI(connString))).newLink();
    }

}
