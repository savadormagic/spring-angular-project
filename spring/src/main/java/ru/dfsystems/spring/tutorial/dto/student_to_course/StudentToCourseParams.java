package ru.dfsystems.spring.tutorial.dto.student_to_course;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class StudentToCourseParams {

    private Integer studentId;
    private Integer courseId;
    private Boolean success;

    private String orderBy;
    private String orderDir;
}
