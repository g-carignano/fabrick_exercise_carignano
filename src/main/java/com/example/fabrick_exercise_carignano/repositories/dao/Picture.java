package com.example.fabrick_exercise_carignano.repositories.dao;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "T_PICTURE")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pic_seq")
    @SequenceGenerator(name = "pic_seq", sequenceName = "PIC_ID_SEQ", allocationSize = 1)
    @Column(name = "IDPICTURE", unique = true, nullable = false)
    private BigDecimal idPicture;

    @Column(name = "FILENAME")
    private String fileName;

    @Lob
    @Column(name = "DATA", columnDefinition = "BLOB")
    private byte[] data;

    public BigDecimal getIdPicture() {
        return idPicture;
    }

    public void setIdPicture(BigDecimal idPicture) {
        this.idPicture = idPicture;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
