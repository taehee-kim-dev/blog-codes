package study.toapplication.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import io.awspring.cloud.core.region.RegionProvider;
import io.awspring.cloud.core.region.StaticRegionProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsSqsConfig {

    @Bean
    public AmazonSQSAsync amazonSQS(AWSCredentialsProvider awsCredentialsProvider, RegionProvider regionProvider, ClientConfiguration clientConfiguration) {
        return AmazonSQSAsyncClientBuilder.standard().withCredentials(awsCredentialsProvider)
            .withClientConfiguration(clientConfiguration).withRegion(regionProvider.getRegion().getName()).build();
    }

    @Bean
    public AWSCredentialsProvider awsCredentialsProvider() {
        return new DefaultAWSCredentialsProviderChain();
    }

    @Bean
    public RegionProvider regionProvider() {
        return new StaticRegionProvider("ap-northeast-2");
    }

    @Bean
    public ClientConfiguration clientConfiguration() {
        return new ClientConfiguration();
    }

}
