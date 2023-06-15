package com.player.data.gameaddict.service;


import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.player.data.gameaddict.config.AwsCloudConfigValue;
import com.player.data.gameaddict.config.cloud.AwsCloudConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class AwsS3Service {

    private final AmazonS3 amazonS3;
    private final AwsCloudConfigValue awsCloudConfigValue;
    public AwsS3Service(AmazonS3 amazonS3, AwsCloudConfigValue awsCloudConfigValue) {
        this.amazonS3 = amazonS3;
        this.awsCloudConfigValue = awsCloudConfigValue;
    }

    public String upload(MultipartFile file) {
        String fileName = System.currentTimeMillis()+"_"+file.getOriginalFilename();
        try {
            log.info("start upload file to s3 with fileName = {}", fileName);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            amazonS3.putObject(awsCloudConfigValue.getBucketName(), fileName, file.getInputStream(), metadata);
            log.info("finish upload file to s3 with fileName = {}", fileName);
            return fileName;
        } catch (IOException ioe) {
            log.info("upload file to s3 failed with fileName = {}, ex = {}", fileName, ioe.getMessage());
            return null;
        } catch (AmazonServiceException serviceException) {
            log.info("upload file to s3 failed with fileName = {}, ex= {}", fileName, serviceException.getMessage());
            return null;
        }
    }
}
