package ru.dfsystems.spring.tutorial.dto.student;

import lombok.Getter;
import lombok.Setter;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;

import java.time.LocalDateTime;

@Getter
@Setter
public class StudentListDto extends BaseListDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private String passport;
    private LocalDateTime birthDate;
    private String status;
}
