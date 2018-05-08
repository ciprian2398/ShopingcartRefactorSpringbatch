package springbatchexporter.productexporter;

import org.joda.time.DateTime;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import java.util.List;

public class ProductJobListener implements JobExecutionListener {

    private DateTime startTime, stopTime;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = new DateTime();
        System.out.println("Product Job starts at :" + startTime);
    }


    @Override
    public void afterJob(JobExecution jobExecution) {
        stopTime = new DateTime();
        System.out.println("Product Job stops at :" + stopTime);
        System.out.println("Total time take in millis :" + getTimeInMillis(startTime, stopTime));

        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            System.out.println("Product job completed successfully");
        } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            System.out.println("Product job failed with following exceptions ");
            List<Throwable> exceptionList = jobExecution.getAllFailureExceptions();
            for (Throwable th : exceptionList) {
                System.err.println("exception :" + th.getLocalizedMessage());
            }
        }
    }

    private long getTimeInMillis(DateTime start, DateTime stop) {
        return stop.getMillis() - start.getMillis();
    }

}
