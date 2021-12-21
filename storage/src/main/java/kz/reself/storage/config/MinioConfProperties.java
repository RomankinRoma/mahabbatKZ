package kz.reself.storage.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.minio")
public class MinioConfProperties {

    private String url;

    private String accessKey;

    private String secretKey;

    private String bucket;

    /**
     * From the official website   Construction method , I just climbed the official website  （ The dog's head lives ） *  This is   The class that the client operates on
     */
    @Bean
    public MinioClient minioClient() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        MinioClient minioClient = MinioClient.builder()
                .credentials(accessKey, secretKey)
                .endpoint(url)
                .build();
        BucketExistsArgs bucketExistsArgs = BucketExistsArgs.builder().bucket(getBucket()).build();
        if (!minioClient.bucketExists(bucketExistsArgs)) {
            MakeBucketArgs bucketArgs = MakeBucketArgs.builder().bucket(getBucket()).build();
            minioClient.makeBucket(bucketArgs);
        }
        return minioClient;
    }
}
