package ru.dfsystems.spring.tutorial.dto.course;

import lombok.Getter;
import lombok.Setter;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;
import ru.dfsystems.spring.tutorial.dto.teacher.TeacherDto;

import java.time.LocalDateTime;

@Getter
@Setter
public class CourseListDto extends BaseListDto {
    private String name;
    private Integer maxCountStudent;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;

    private TeacherDto teacher;
}
