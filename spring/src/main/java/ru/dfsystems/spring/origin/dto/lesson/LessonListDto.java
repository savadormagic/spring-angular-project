package ru.dfsystems.spring.origin.dto.lesson;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import ru.dfsystems.spring.origin.dto.BaseListDto;

import java.time.LocalDateTime;


@Getter
@Setter
public class LessonListDto extends BaseListDto {
    String name;
    String description;
    private int courseId;
    private int roomId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lessonDateStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lessonDateEnd;
}
