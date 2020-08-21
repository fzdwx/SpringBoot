package lk.component;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author likeLove
 * @time 2020-08-18  16:02
 */
@Component
public class RedisUtil {
    @Resource
    RedisTemplate redisTemplate;   //key-value是对象的

    //判断是否存在key
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    //从redis中获取值
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    //向redis插入值
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
