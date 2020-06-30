package ru.dfsystems.spring.origin.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import ru.dfsystems.spring.origin.dto.BaseDto;

import java.time.LocalDateTime;


@Getter
@Setter
public class UserDto extends BaseDto<UserHistoryDto> {
    private String firstName;
    private String middleName;
    private String lastName;
    private String passport;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime birthDateStart;
}
