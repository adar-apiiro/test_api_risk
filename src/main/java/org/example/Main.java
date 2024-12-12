package org.example;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.CreateBucketResponse;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // AWS SDK example integrated into the Main class

        // Create an S3 client using the Builder pattern
        S3Client s3 = S3Client.builder()
                .region(software.amazon.awssdk.regions.Region.US_EAST_1) // Specify the region
                .build();

        String bucketName = "my-example-bucket-" + System.currentTimeMillis();

        try {
            // Create a bucket
            CreateBucketRequest createBucketRequest = CreateBucketRequest.builder()
                    .bucket(bucketName)
                    .build();

            CreateBucketResponse createBucketResponse = s3.createBucket(createBucketRequest);
            System.out.println("Bucket created: " + createBucketResponse.location());

            // List all buckets
            ListBucketsResponse listBucketsResponse = s3.listBuckets();
            listBucketsResponse.buckets().forEach(bucket ->
                    System.out.println("Bucket: " + bucket.name())
            );

        } catch (S3Exception e) {
            System.err.println("AWS Error: " + e.awsErrorDetails().errorMessage());
        } finally {
            // Close the S3 client
            s3.close();
        }

        // Original loop from your code
        System.out.printf("Hello and welcome!");
        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }
    }
}
