package com.book.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.book.entity.Role;
import com.book.entity.UserInfo;
import com.book.entity.UserRole;

@Configuration
public class RedisConfiguration {
    
    @Bean
    RedisTemplate<String, UserInfo> userInfoCache(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, UserInfo> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(UserInfo.class));
        redisTemplate.setHashKeySerializer(redisTemplate.getKeySerializer());
        redisTemplate.setHashValueSerializer(redisTemplate.getValueSerializer());
        return redisTemplate;
    }
    
    @Bean
    RedisTemplate<String, UserRole> userRoleCache(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, UserRole> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(UserRole.class));
        redisTemplate.setHashKeySerializer(redisTemplate.getKeySerializer());
        redisTemplate.setHashValueSerializer(redisTemplate.getValueSerializer());
        return redisTemplate;
    }
    
    @Bean
    RedisTemplate<String, Role> roleCache(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Role> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Role.class));
        redisTemplate.setHashKeySerializer(redisTemplate.getKeySerializer());
        redisTemplate.setHashValueSerializer(redisTemplate.getValueSerializer());
        return redisTemplate;
    }
    
//    @Bean
//    RedisTemplate<String, Movie> movieCache(RedisConnectionFactory connectionFactory) {
//        RedisTemplate<String, Movie> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(connectionFactory);
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Movie.class));
//        redisTemplate.setHashKeySerializer(redisTemplate.getKeySerializer());
//        redisTemplate.setHashValueSerializer(redisTemplate.getValueSerializer());
//        return redisTemplate;
//    }
}
