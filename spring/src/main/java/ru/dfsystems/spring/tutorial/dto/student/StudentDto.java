package ru.dfsystems.spring.tutorial.dto.student;

import lombok.Getter;
import lombok.Setter;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;

@Getter
@Setter
public class StudentDto extends BaseListDto {
    private String firstName;
    private String lastName;
    private String middleName;
}
