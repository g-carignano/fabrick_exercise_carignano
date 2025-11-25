package com.example.fabrick_exercise_carignano.repositories.dao;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "T_FILE")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_seq")
    @SequenceGenerator(name = "file_seq", sequenceName = "FILE_ID_SEQ", allocationSize = 1)
    @Column(name = "IDFILE", unique = true, nullable = false)
    private BigDecimal idFile;

    @Column(name = "FILENAME")
    private String fileName;

    @Lob
    @Column(name = "DATA", columnDefinition = "CLOB")
    private String data;

    @Column(name = "FORMAT")
    private String format;

    public BigDecimal getIdFile() {
        return idFile;
    }

    public void setIdFile(BigDecimal idFile) {
        this.idFile = idFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
