package ru.hogwarts.magic_school.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;



@EqualsAndHashCode
@ToString
@Entity
public class Avatar {
    @Id
    @GeneratedValue
    private Long id;


    private String filePath;
    private long fileSize;
    private String mediaType;

    @Lob
    private byte[] data;

    @OneToOne
    private Student student;
    public Avatar(Long id, String filePath, long fileSize, String mediaType, byte[] data, Student student) {
        this.id = id;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.mediaType = mediaType;
        this.data = data;
        this.student = student;
    }

    public Avatar() {

    }

    public void setId(Long id) {
        this.id = id;
    }
}
