package ru.dfsystems.spring.origin.dto.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import ru.dfsystems.spring.origin.dto.BaseListDto;

import java.time.LocalDateTime;


@Getter
@Setter
public class StudentListDto extends BaseListDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private String passport;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime birthDateStart;
}
