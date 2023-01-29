package study.project.codeexample.web.image.application;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.project.codeexample.exam.image.S3Component;

import java.io.InputStream;

@RequiredArgsConstructor
@Component
public class AwsS3UploadService implements UploadService {

    private final AmazonS3 amazonS3;
    private final S3Component component;

    @Override
    public void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String fileName) {
        amazonS3.putObject(getPutObjectRequest(inputStream, objectMetadata, fileName)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    private PutObjectRequest getPutObjectRequest(InputStream inputStream, ObjectMetadata objectMetadata, String fileName) {
        return new PutObjectRequest(component.getBucket(), fileName, inputStream, objectMetadata);
    }

    @Override
    public void deleteFile(String fileName) {
        amazonS3.deleteObject(component.getBucket(), fileName);
    }

    @Override
    public String getFileUrl(String fileName) {
        return amazonS3.getUrl(component.getBucket(), fileName).toString();
    }

}
