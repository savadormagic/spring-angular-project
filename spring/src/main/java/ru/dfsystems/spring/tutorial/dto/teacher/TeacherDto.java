package ru.dfsystems.spring.tutorial.dto.teacher;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import ru.dfsystems.spring.tutorial.dto.BaseDto;

import java.time.LocalDateTime;
@Getter
@Setter
public class TeacherDto extends BaseDto<TeacherHistoryDto> {

    private String firstName;
    private String middleName;
    private String lastName;
    private String status;
    private Integer courseIdd;
}
