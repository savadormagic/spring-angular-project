package ru.dfsystems.spring.tutorial.dto.teacher;

import lombok.Getter;
import lombok.Setter;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;

import java.util.Date;

@Getter
@Setter
public class TeacherList extends BaseListDto {
    private String firstName;
    private String lastName;
    private String middleName;
    private String passport;
    private Date birthDate;
}
