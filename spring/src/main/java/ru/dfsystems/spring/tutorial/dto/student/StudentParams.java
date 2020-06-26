package ru.dfsystems.spring.tutorial.dto.student;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class StudentParams {

    private Integer idd;
    private String firstName;
    private String middleName;
    private String lastName;
    private String passport;
    private String status;

    private String orderBy;
    private String orderDir;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime birthDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deleteDate;
}
