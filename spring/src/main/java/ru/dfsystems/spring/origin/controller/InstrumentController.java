package ru.dfsystems.spring.origin.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dfsystems.spring.origin.dto.BaseListDto;
import ru.dfsystems.spring.origin.dto.Page;
import ru.dfsystems.spring.origin.dto.PageParams;
import ru.dfsystems.spring.origin.dto.instrument.InstrumentParams;
import ru.dfsystems.spring.origin.generated.tables.pojos.Instrument;
import ru.dfsystems.spring.origin.service.InstrumentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/instrument", produces = "application/json; charset=UTF-8")
@AllArgsConstructor
public class InstrumentController {
    private InstrumentService instrumentService;


    @PostMapping("/instrument")
    public Page<BaseListDto> getList(PageParams<InstrumentParams> pageParams){
        Page<Instrument> page = instrumentService.getInstrumentByParams(pageParams);
        List<BaseListDto> list = mapper(page.getList());
        return new Page<>(list, page.getTotalCount());
    }

    private List<BaseListDto> mapper(List<Instrument> allInstruments) {
        ModelMapper mapper = new ModelMapper();
        return allInstruments.stream()
                .map(r -> mapper.map(r, BaseListDto.class))
                .collect(Collectors.toList());
    }
}