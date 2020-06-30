package ru.dfsystems.spring.origin.dto.room;

import lombok.Getter;
import lombok.Setter;
import ru.dfsystems.spring.origin.dto.BaseHistoryDto;

@Getter
@Setter
public class RoomHistoryDto extends BaseHistoryDto {
    private String number;
    private String block;
}
