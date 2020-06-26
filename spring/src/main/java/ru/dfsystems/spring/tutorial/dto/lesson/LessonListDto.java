package ru.dfsystems.spring.tutorial.dto.lesson;

import lombok.Getter;
import lombok.Setter;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;
import ru.dfsystems.spring.tutorial.dto.course.CourseDto;
import ru.dfsystems.spring.tutorial.dto.room.RoomDto;

import java.time.LocalDateTime;

@Getter
@Setter
public class LessonListDto extends BaseListDto {
    private String name;
    private LocalDateTime lessonDateStart;
    private LocalDateTime lessonDateEnd;
    private String status;

    private CourseDto course;
    private RoomDto room;

}
