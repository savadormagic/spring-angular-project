package ru.dfsystems.spring.tutorial.dto.instrument;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.dfsystems.spring.tutorial.dto.room.RoomListDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstrumentParams {

    private String name;
    private String number;
    private String orderBy;
    private String orderDir;

    private List<RoomListDto> rooms;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDateStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDateEnd;
}
