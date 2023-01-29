package study.project.codeexample.exam.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class S3Component {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String getBucket() {
        return bucket;
    }

    @Override
    public String toString() {
        return String.format("Bucket: %s", bucket);
    }
}
