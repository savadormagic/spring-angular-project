package ru.dfsystems.spring.tutorial.dto.lesson;

import lombok.Getter;
import lombok.Setter;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;

@Getter
@Setter
public class LessonDto extends BaseListDto {
    private String name;
    private String description;
}