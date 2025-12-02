package com.example.fabrick_exercise_carignano.schedulers;

import com.example.fabrick_exercise_carignano.converter.MultiPartFileConverter;
import com.example.fabrick_exercise_carignano.localservice.image.FileService;
import com.example.fabrick_exercise_carignano.utils.DateUtils;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

@Component
public class JobCompletionListener extends JobExecutionListenerSupport {

    private final FileService fileService;

    public JobCompletionListener(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        LocalDate yesterday = DateUtils.getYesterdayLocalDate();
        File csvFile = new File(yesterday + "-transactions.csv");
        if (csvFile.exists()) {
            try {
                MultipartFile multipartFile = MultiPartFileConverter.convertFileToMultipartFile(csvFile);
                fileService.uploadFile(multipartFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}