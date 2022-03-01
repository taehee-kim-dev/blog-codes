package study.fromapplication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.fromapplication.dto.PublishSnsTopicRequest;
import study.fromapplication.service.PublishSnsTopicService;

@RequiredArgsConstructor
@RestController
public class PublishSnsTopicController {

    private final PublishSnsTopicService publishSnsTopicService;

    @PostMapping("/api/v1/sns/publish-topic")
    public String publish(@RequestBody PublishSnsTopicRequest publishSnsTopicRequest) {
        publishSnsTopicService.publish(publishSnsTopicRequest);
        return "SNS Topic publish 성공!!";
    }
}
