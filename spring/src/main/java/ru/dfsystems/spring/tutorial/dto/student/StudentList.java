package ru.dfsystems.spring.tutorial.dto.student;

import lombok.Getter;
import lombok.Setter;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;

import java.util.Date;

@Getter
@Setter
public class StudentList extends BaseListDto {
    private String firstName;
    private String lastName;
    private String middleName;
    private String passport;
    private Date birthDate;
    private String status;
}
