package ru.dfsystems.spring.tutorial.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class UserParams {

    private Integer idd;
    private String firstName;
    private String middleName;
    private String lastName;
    private String status;
    private int courseId;
    private String login;
    private String password;

    private String orderBy;
    private String orderDir;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deleteDate;
}
