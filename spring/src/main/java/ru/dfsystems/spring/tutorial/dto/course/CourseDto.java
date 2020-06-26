package ru.dfsystems.spring.tutorial.dto.course;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.dfsystems.spring.tutorial.dto.BaseDto;

import java.time.LocalDateTime;

public class CourseDto extends BaseDto<CourseHistoryDto> {

    private String name;
    private String description;
    private Integer teacherIdd;
    private Integer maxCountStudent;
    private String passport;
    private String status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDateStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDateEnd;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deleteDate;
}
