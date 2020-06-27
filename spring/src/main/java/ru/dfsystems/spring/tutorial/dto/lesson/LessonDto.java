package ru.dfsystems.spring.tutorial.dto.lesson;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LessonDto  {

    private Integer       id;
    private String        name;
    private String        description;
    private Integer       courseIdd;
    private Integer       roomIdd;
    private LocalDateTime lessonDateStart;
    private LocalDateTime lessonDateEnd;
    private String        extraInstruments;

}
