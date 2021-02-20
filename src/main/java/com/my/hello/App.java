package com.my.hello;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Hello Redis!
 */
public class App  {

    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main( String[] args )  {

        logger.info("Hello Redis!");

        ResourceBundle resource = ResourceBundle.getBundle("my-config");
        logger.debug(() -> "Current config file:\n" + resource.keySet().stream()
                .map(key -> key + " = " + resource.getString(key))
                .collect(Collectors.joining("\n")));

        Jedis jedis = new Jedis(
                resource.getString("redis.host"),
                Integer.parseInt(resource.getString("redis.port"))
        );

        System.out.print("Ping... ");
        System.out.flush();
        System.out.println(jedis.ping("OK"));
    }
}
