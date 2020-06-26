package ru.dfsystems.spring.tutorial.dto.room;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import ru.dfsystems.spring.tutorial.dto.BaseDto;
import ru.dfsystems.spring.tutorial.dto.instrument.InstrumentListDto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomDto extends BaseDto<RoomHistoryDto> {

    private String number;
    private String block;

    private List<InstrumentListDto> instruments;
}
