package taeheekim.localstacksnssqs.dto;

import lombok.*;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Message {

    private MessageData data;

    @ToString
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private static class MessageData {

        private String name;
        private String nickname;
        private Integer age;
    }
}
