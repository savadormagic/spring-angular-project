package ru.dfsystems.spring.origin.dto.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import ru.dfsystems.spring.origin.dto.BaseDto;
import ru.dfsystems.spring.origin.dto.instrument.InstrumentListDto;
import ru.dfsystems.spring.origin.dto.room.RoomHistoryDto;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class StudentDto extends BaseDto<StudentHistoryDto> {
    private String firstName;
    private String middleName;
    private String lastName;
    private String passport;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime birthDateStart;
}
