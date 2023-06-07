package taeheekim.redis;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Getter
@Builder
@RedisHash(value = "people")
public class Person {

    @Id
    private String id;
    private String name;
    private Integer age;
    private LocalDateTime createdDateTime;
}
