package example.d17;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean // 빈 등록
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        // 템플릿 객체 생성 : redis 형식을 MAP 타입으로 사용하기 위한 설정
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 생성한 객체를 팩토리(redis 저장소)에 등록
        template.setConnectionFactory(connectionFactory);
        // key 값을 String 타입으로 직렬화 (redis 에 저장된 데이터를 자바 타입으로 변환하는 과정, 역직렬화는 복원)
        template.setKeySerializer(new StringRedisSerializer());
        // value 값을 JSON/DTO 타입으로 직렬화
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
}
