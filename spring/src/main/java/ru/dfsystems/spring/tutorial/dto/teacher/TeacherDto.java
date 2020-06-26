package ru.dfsystems.spring.tutorial.dto.teacher;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.dfsystems.spring.tutorial.dto.BaseDto;

import java.time.LocalDateTime;

public class TeacherDto extends BaseDto<TeacherHistoryDto> {

    private String firstName;
    private String middleName;
    private String lastName;
    private String status;
    private Integer courseIdd;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm ")
    private LocalDateTime deleteDate;
}
