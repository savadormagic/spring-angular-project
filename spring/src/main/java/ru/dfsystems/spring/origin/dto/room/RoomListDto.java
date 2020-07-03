package ru.dfsystems.spring.origin.dto.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.dfsystems.spring.origin.dto.BaseListDto;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomListDto extends BaseListDto {
    private String number;
    private String block;
}
