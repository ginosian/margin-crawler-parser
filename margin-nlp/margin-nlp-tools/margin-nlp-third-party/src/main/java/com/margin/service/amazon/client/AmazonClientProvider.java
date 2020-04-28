package com.margin.service.amazon.client;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.AmazonComprehendClientBuilder;
import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:third.party/amazon-nlp.properties")
public class AmazonClientProvider {
    @Value("${access.key}")
    private String awsAccessKey;

    @Value("${secret.access.key}")
    private String awsSecretAccessKey;

    @Value("${region}")
    private String region;

    public AmazonTranslate getTranslatorClient() {
        return AmazonTranslateClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(awsAccessKey, awsSecretAccessKey)))
                .withRegion(region)
                .build();
    }

    public AmazonComprehend getComprehendClient() {
        return AmazonComprehendClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(awsAccessKey, awsSecretAccessKey)))
                .withRegion(region)
                .build();
    }

}
