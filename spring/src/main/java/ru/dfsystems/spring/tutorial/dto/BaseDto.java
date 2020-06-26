package ru.dfsystems.spring.tutorial.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseDto<HistoryDto extends BaseHistoryDto> implements Serializable {
    private List<HistoryDto> history;
}
