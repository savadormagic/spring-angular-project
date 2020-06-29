package ru.dfsystems.spring.tutorial.dto.lesson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LessonParams {
    private Integer idd;
    private String name;
}
