package lk.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;

/**
 * @author likeLove
 * @time 2020-08-18  13:46
 */
@Configuration
public class RedisConfig {
    private Duration timeToLive = Duration.ofDays(1);
    Logger log = LoggerFactory.getLogger(getClass());
    @Bean
    @SuppressWarnings ("all")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        //json序列化设置
        Jackson2JsonRedisSerializer j2r = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        j2r.setObjectMapper(om);
        //string序列化
        StringRedisSerializer srs = new StringRedisSerializer();
        template.setKeySerializer(srs);
        template.setHashKeySerializer(srs);
        template.setValueSerializer(j2r);
        template.setHashValueSerializer(j2r);
        template.afterPropertiesSet();
        log.info("redisTemplate已经加载");
        return template;
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory factory) {
        RedisCacheConfiguration config =
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(this.timeToLive).serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer())).serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer())).disableCachingNullValues();
        RedisCacheManager redisCacheManager =
                RedisCacheManager.builder(factory).cacheDefaults(config).transactionAware().build();
        log.info("redisCache已经加载");
        return redisCacheManager;
    }

    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }
    private RedisSerializer<Object> valueSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }
}
