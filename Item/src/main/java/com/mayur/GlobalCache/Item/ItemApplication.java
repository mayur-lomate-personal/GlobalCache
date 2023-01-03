package com.mayur.GlobalCache.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.r2dbc.ConnectionFactoryBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;

@SpringBootApplication
@EnableCaching
public class ItemApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ItemApplication.class, args);
        RedisConnection conn = context.getBean(RedisConnectionFactory.class).getConnection();
        try {
            //RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost", 6379);
            //conn = connectionFactory.getConnection();
            conn.setConfig("maxmemory-policy", "allkeys-lfu");
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
