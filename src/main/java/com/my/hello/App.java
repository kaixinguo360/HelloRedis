package com.my.hello;

import redis.clients.jedis.Jedis;

import java.util.ResourceBundle;

/**
 * Hello Redis!
 */
public class App  {
    public static void main( String[] args )  {
        System.out.println( "Hello Redis!" );
        ResourceBundle resource = ResourceBundle.getBundle("my-config");

        Jedis jedis = new Jedis(resource.getString("redis.host"), Integer.parseInt(resource.getString("redis.port")));

        System.out.print("Ping... ");
        System.out.flush();
        System.out.println(jedis.ping("OK"));
    }
}
