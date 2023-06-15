package com.player.data.gameaddict.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class AwsCloudConfigValue {

    @Value("${cloud.aws.bucket-name}")
    private String bucketName;
    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;
    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;
    @Value("${cloud.aws.region.static}")
    private String regionStatic;
    @Value("${cloud.aws.base-url}")
    private String baseURL;
}
