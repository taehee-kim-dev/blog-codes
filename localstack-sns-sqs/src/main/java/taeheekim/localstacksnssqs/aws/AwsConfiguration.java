package taeheekim.localstacksnssqs.aws;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sns.AmazonSNSAsync;
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.cloud.aws.context.annotation.ConditionalOnMissingAwsCloudEnvironment;
import org.springframework.cloud.aws.core.env.ResourceIdResolver;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;

@Configuration
public class AwsConfiguration {

    @Configuration
    @ConditionalOnMissingAwsCloudEnvironment
    public static class LocalConfiguration {

        private static final AwsClientBuilder.EndpointConfiguration DEFAULT_ENDPOINT_CONFIGURATION = new AwsClientBuilder.EndpointConfiguration(
                "http://localhost:4566",
                "ap-northeast-2"
        );

        @Primary
        @Bean
        public AmazonSNSAsync amazonSNSAsync(AWSCredentialsProvider awsCredentialsProvider) {
            return AmazonSNSAsyncClientBuilder.standard()
                    .withEndpointConfiguration(DEFAULT_ENDPOINT_CONFIGURATION)
                    .withCredentials(awsCredentialsProvider)
                    .build();
        }

        @Primary
        @Bean
        public AmazonSQSAsync amazonSQSAsync(AWSCredentialsProvider awsCredentialsProvider) {
            return AmazonSQSAsyncClientBuilder.standard()
                    .withCredentials(awsCredentialsProvider)
                    .withEndpointConfiguration(DEFAULT_ENDPOINT_CONFIGURATION)
                    .build();
        }
    }

    @Bean
    public NotificationMessagingTemplate notificationMessagingTemplate(AmazonSNSAsync amazonSNSAsync) {
        return new NotificationMessagingTemplate(amazonSNSAsync);
    }

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync, MessageConverter messageConverter) {
        return new QueueMessagingTemplate(amazonSQSAsync, (ResourceIdResolver) null, messageConverter);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new MappingJackson2MessageConverter();
    }
}
