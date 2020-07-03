package ru.dfsystems.spring.tutorial.dto.course;

import lombok.Getter;
import lombok.Setter;
import ru.dfsystems.spring.tutorial.dto.BaseDto;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;
import ru.dfsystems.spring.tutorial.dto.instrument.InstrumentHistoryDto;

@Getter
@Setter
public class CourseDto extends BaseDto<InstrumentHistoryDto> {
    private String name;
    private String description;
}
