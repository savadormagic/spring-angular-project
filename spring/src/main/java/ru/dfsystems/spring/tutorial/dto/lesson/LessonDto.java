package ru.dfsystems.spring.tutorial.dto.lesson;


import lombok.Getter;
import lombok.Setter;

import ru.dfsystems.spring.tutorial.dto.instrument.InstrumentListDto;

import java.util.List;

@Getter
@Setter
public class LessonDto extends BaseLesson{

    private List<InstrumentListDto> instruments;
}
