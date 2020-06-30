package ru.dfsystems.spring.origin.dto.room;

import lombok.Getter;
import lombok.Setter;
import ru.dfsystems.spring.origin.dto.BaseListDto;


@Getter
@Setter
public class RoomListDto extends BaseListDto {
    private String number;
    private String block;
}
