package org.orgname.app;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Service {
    private final static Logger LOGGER = Logger.getLogger(Service.class.getName());


    public  void run(String msg , Date time ) {
        try (JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost")) {
            //get a jedis connection jedis connection pool
            try (Jedis jedis = pool.getResource()) {

                double d = (double) time.getTime();
                Map<String, Double> mem = new HashMap<>();
                mem.put(msg, d);
                jedis.zadd("echotime", mem);
                LOGGER.info("wrote msg to redis");
            }

        }

    }
}
