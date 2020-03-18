package com.github.lionani07.aws;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@SpringBootApplication
public class AwsApplication
{

    static Path local = Paths.get(System.getenv("HOME"));

    static String bucket = "bucketlio";

    static AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();

    public static void main(String[] args) throws IOException
    {
        UploadS3Service s3Service = new UploadS3Service();
        s3Service.downLoadObject(bucket, "lio1.jpeg", local);
    }

}
