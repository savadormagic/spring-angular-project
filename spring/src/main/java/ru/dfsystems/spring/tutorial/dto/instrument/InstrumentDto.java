package ru.dfsystems.spring.tutorial.dto.instrument;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import ru.dfsystems.spring.tutorial.dto.BaseDto;
import ru.dfsystems.spring.tutorial.dto.room.RoomListDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstrumentDto extends BaseDto<InstrumentHistoryDto> {
    private String name;
    private String number;
    private List<RoomListDto> rooms;
}
