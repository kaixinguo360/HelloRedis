package com.my.hello;

import lombok.extern.log4j.Log4j2;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Hello Redis!
 */
@Log4j2
public class App  {

    public static void main( String[] args )  {

        log.info("Hello Redis!");

        ResourceBundle resource = ResourceBundle.getBundle("my-config");
        log.debug(() -> "Current config file:\n" + resource.keySet().stream()
                .map(key -> key + " = " + resource.getString(key))
                .collect(Collectors.joining("\n")));

        JedisPool jedisPool = new JedisPool(
                resource.getString("redis.host"),
                Integer.parseInt(resource.getString("redis.port"))
        );
        log.info("JedisPool Created");

        try (Jedis jedis = jedisPool.getResource()) {
            System.out.print("Ping... ");
            System.out.flush();
            System.out.println(jedis.ping("OK"));
        }
    }
}
