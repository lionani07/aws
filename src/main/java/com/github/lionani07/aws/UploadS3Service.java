package com.github.lionani07.aws;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class UploadS3Service
{

    final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();

    public void uploadObject(String bucketName, File file)
    {
        s3.putObject(bucketName, file.getName(), file);
    }

    public void downLoadObject(String bucket, String key, Path pathToSave) throws IOException
    {
        S3ObjectInputStream inputStream = s3.getObject(bucket, key).getObjectContent();
        Files.copy(inputStream, pathToSave.resolve(key));
    }

    public void createBucket(String bucket)
    {
        s3.createBucket(bucket);
    }

    public List<Bucket> getBuckets()
    {
        return s3.listBuckets();
    }

    public List<S3ObjectSummary> listObjects(String bucket)
    {
        return s3.listObjects(bucket).getObjectSummaries();
    }

    public void createFolder(String bucket, String folder)
    {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(0);
        InputStream inputStream = new ByteArrayInputStream(new byte[0]);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, folder, inputStream, metadata);
        s3.putObject(putObjectRequest);
    }
}
