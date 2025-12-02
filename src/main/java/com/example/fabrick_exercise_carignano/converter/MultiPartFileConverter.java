package com.example.fabrick_exercise_carignano.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MultiPartFileConverter {

    private static final Logger log = LoggerFactory.getLogger(MultiPartFileConverter.class);

    public static MultipartFile convertFileToMultipartFile(File file) throws IOException {
        MultipartFile ret = new MultipartFile() {
            @Override
            public String getName() {
                return file.getName();
            }

            @Override
            public String getOriginalFilename() {
                return file.getName();
            }

            @Override
            public String getContentType() {
                return "text/csv";
            }

            @Override
            public boolean isEmpty() {
                return file.length() == 0;
            }

            @Override
            public long getSize() {
                return file.length();
            }

            @Override
            public byte[] getBytes() throws IOException {
                return java.nio.file.Files.readAllBytes(file.toPath());
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new FileInputStream(file);
            }

            @Override
            public void transferTo(File dest) throws IOException {
                java.nio.file.Files.copy(file.toPath(), dest.toPath());
            }
        };

        return ret;
    }
}
