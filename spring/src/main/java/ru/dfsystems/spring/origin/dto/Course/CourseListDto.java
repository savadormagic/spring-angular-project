package ru.dfsystems.spring.origin.dto.Course;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import ru.dfsystems.spring.origin.dto.BaseListDto;

import java.time.LocalDateTime;


@Getter
@Setter
public class CourseListDto extends BaseListDto {
    private int maxCountOfStudents;
    private String name;
    private String description;
    private String status;
    private int teacherId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDateStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDateEnd;
}
