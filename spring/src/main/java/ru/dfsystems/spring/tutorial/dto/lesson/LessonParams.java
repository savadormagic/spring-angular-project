package ru.dfsystems.spring.tutorial.dto.lesson;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class LessonParams {

    private Integer idd;
    private String name;
    private String description;
    private Integer courseId;
    private Integer roomId;

    private String orderBy;
    private String orderDir;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createLessonDateStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createLessonDateEnd;
}
