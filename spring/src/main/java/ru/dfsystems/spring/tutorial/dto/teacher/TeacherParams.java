package ru.dfsystems.spring.tutorial.dto.teacher;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class TeacherParams {

    private Integer idd;
    private String firstName;
    private String middleName;
    private String lastName;
    private String passport;
    private Integer courseIdd;
    private String status;

    private String orderBy;
    private String orderDir;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deleteDate;
}
