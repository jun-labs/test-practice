package study.project.codeexample.exam.lock;

import java.util.concurrent.TimeUnit;

public class LockUtils {

    public static void sleep(long time) throws InterruptedException {
        TimeUnit.SECONDS.sleep(time);
    }
}
