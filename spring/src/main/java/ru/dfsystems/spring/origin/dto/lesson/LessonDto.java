package ru.dfsystems.spring.origin.dto.lesson;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import ru.dfsystems.spring.origin.dto.BaseDto;
import ru.dfsystems.spring.origin.dto.instrument.InstrumentListDto;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class LessonDto extends BaseDto<LessonHistoryDto> {
    String name;
    String description;
    private Integer courseId;
    private Integer roomId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lessonDateStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lessonDateEnd;
}
