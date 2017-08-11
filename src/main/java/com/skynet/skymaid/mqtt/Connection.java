package com.skynet.skymaid.mqtt;

import com.skynet.skymaid.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Connection {
    private static final Logger logger = LogManager.getLogger(Connection.class);
    private String broker = Properties.getProperty("mqtt_broker");

    public MqttClient getConnection() {
        String clientId = "skymaid-1";
        MqttClient sampleClient = null;
        try {
            sampleClient = new MqttClient(broker, clientId, new MemoryPersistence());
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            logger.info("paho-client connecting to broker: " + broker);
            sampleClient.connect(connOpts);
            logger.info("paho-client connected to broker");
        } catch (MqttException e) {
            throw new RuntimeException("Cannot connect to Mqtt broker", e);
        }
        return sampleClient;
    }

}
