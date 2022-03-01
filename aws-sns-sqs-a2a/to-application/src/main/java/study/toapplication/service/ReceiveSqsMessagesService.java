package study.toapplication.service;

import java.util.List;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.SqsException;

@Service
public class ReceiveSqsMessagesService {

    public void receiveMessages(String sqsUrl) {
        SqsClient sqsClient = getSqsClient();
        System.out.println("\nReceive messages start!!");
        try {
            ReceiveMessageRequest receiveMessageRequest = getReceiveMessageRequestBy(sqsUrl);
            List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).messages();
            printMessages(messages);
        } catch (SqsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        System.out.println("\nReceive messages end");
        sqsClient.close();
    }

    private SqsClient getSqsClient() {
        return SqsClient.builder()
            .region(Region.AP_NORTHEAST_2)
            .build();
    }

    private ReceiveMessageRequest getReceiveMessageRequestBy(String sqsUrl) {
        return ReceiveMessageRequest.builder()
            .queueUrl(sqsUrl)
            .maxNumberOfMessages(5)
            .build();
    }

    private void printMessages(List<Message> messages) {
        System.out.println("\nPrint messages start!!");
        for (Message message : messages) {
            System.out.println(message);
        }
        System.out.println("\nPrint messages end");
    }
}
