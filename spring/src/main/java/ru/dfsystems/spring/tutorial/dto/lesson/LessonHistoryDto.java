package ru.dfsystems.spring.tutorial.dto.lesson;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.dfsystems.spring.tutorial.dto.BaseHistoryDto;

import java.time.LocalDateTime;

public class LessonHistoryDto extends BaseHistoryDto {

    private String name;
    private String description;
    private Integer courseId;
    private Integer roomId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createLessonDateStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createLessonDateEnd;
}
