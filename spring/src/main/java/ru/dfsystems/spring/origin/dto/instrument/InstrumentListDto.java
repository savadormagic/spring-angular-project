package ru.dfsystems.spring.origin.dto.instrument;

import lombok.Getter;
import lombok.Setter;
import ru.dfsystems.spring.origin.dto.BaseListDto;

@Getter
@Setter
public class InstrumentListDto extends BaseListDto {
    private String name;
    private String number;
}
