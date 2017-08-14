package com.skynet.skymaid;

import com.skynet.skymaid.mqtt.Connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class GetAlive {
    private static final Logger logger = LogManager.getLogger(GetAlive.class);

    public static void main(String[] args) throws MqttException {
        log();
        Connection connection = new Connection();
        MqttClient client = connection.getConnection();
        logger.info("got connection to SkyNet");
        client.disconnect();

    }

    private static void log(){
        logger.info("\n" +
                "   _____ _          __  __       _     _ \n" +
                "  / ____| |        |  \\/  |     (_)   | |\n" +
                " | (___ | | ___   _| \\  / | __ _ _  __| |\n" +
                "  \\___ \\| |/ / | | | |\\/| |/ _` | |/ _` |\n" +
                "  ____) |   <| |_| | |  | | (_| | | (_| |\n" +
                " |_____/|_|\\_\\\\__, |_|  |_|\\__,_|_|\\__,_|\n" +
                "               __/ |                     \n" +
                "              |___/            ");
        logger.info("Getting alive...");
    }
}
