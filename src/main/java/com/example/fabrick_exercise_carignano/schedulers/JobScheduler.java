package com.example.fabrick_exercise_carignano.schedulers;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobScheduler {

    private final JobLauncher jobLauncher;
    private final Job transactionJob;

    public JobScheduler(JobLauncher jobLauncher, Job transactionJob) {
        this.jobLauncher = jobLauncher;
        this.transactionJob = transactionJob;
    }

    // Cron expression: second, minute, hour, day of month, month, day of week
    @Scheduled(cron = "0 56 12 * * ?")
    public void runJob() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis()) // to make JobParameters unique
                .toJobParameters();
        jobLauncher.run(transactionJob, params);
    }
}
