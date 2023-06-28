package com.player.data.gameaddict.config.cloud;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.player.data.gameaddict.config.AwsCloudConfigValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsCloudConfig {

    private final AwsCloudConfigValue awsCloudConfigValue;

    public AwsCloudConfig (AwsCloudConfigValue awsCloudConfigValue) {
        this.awsCloudConfigValue = awsCloudConfigValue;
    }
    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials =
                new BasicAWSCredentials(awsCloudConfigValue.getAccessKey(), awsCloudConfigValue.getSecretKey());
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(awsCloudConfigValue.getRegionStatic())
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
