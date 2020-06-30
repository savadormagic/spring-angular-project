package ru.dfsystems.spring.origin.dto.instrument;

import lombok.Getter;
import lombok.Setter;
import ru.dfsystems.spring.origin.dto.BaseDto;
import ru.dfsystems.spring.origin.dto.room.RoomListDto;

import java.util.List;

@Getter
@Setter
public class InstrumentDto extends BaseDto<InstrumentHistoryDto> {
    private String name;
    private String number;
    private List<RoomListDto> rooms;
}
