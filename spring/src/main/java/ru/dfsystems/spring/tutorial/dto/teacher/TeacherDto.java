package ru.dfsystems.spring.tutorial.dto.teacher;

import lombok.Getter;
import lombok.Setter;
import ru.dfsystems.spring.tutorial.dto.BaseDto;
import ru.dfsystems.spring.tutorial.dto.BaseHistoryDto;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;

@Getter
@Setter
public class TeacherDto extends BaseDto<BaseHistoryDto> {
    private String firstName;
    private String lastName;
    private String middleName;
}
