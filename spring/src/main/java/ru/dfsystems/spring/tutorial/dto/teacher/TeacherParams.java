package ru.dfsystems.spring.tutorial.dto.teacher;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeacherParams {
    private String firstName;
    private String lastName;
    private String middleName;

}
