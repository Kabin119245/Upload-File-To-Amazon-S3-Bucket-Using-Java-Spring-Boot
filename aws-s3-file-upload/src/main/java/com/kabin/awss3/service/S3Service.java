package com.kabin.awss3.service;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class S3Service {

    private String bucketName = "imagesbucket0743";
    Regions regions = Regions.US_EAST_1;

    public void uploadToS3(InputStream inputStream, String filename) throws IOException , AmazonServiceException, SdkClientException {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(regions).build();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image.jpeg");
        metadata.setContentLength(inputStream.available());


        PutObjectRequest request = new PutObjectRequest(bucketName, filename, inputStream, metadata);
        s3Client.putObject(request);

    }


}
