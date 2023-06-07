package taeheekim.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class RedisTestController {

    private final PersonRedisRepository repository;

    @GetMapping("/redis/create")
    public String create() {
        Person person = Person.builder()
                .name("taeheekim")
                .age(27)
                .createdDateTime(LocalDateTime.now())
                .build();

        repository.save(person);
        return "Created";
    }

    @GetMapping("/redis/find")
    public Person find() {
        return repository.findById("123").orElseThrow(() -> new NoSuchElementException("No such element"));
    }

    @GetMapping("/redis/count")
    public long count() {
        return repository.count();
    }

    @GetMapping("/redis/delete")
    public String delete() {
        repository.deleteById("123");
        return "Deleted";
    }
}
