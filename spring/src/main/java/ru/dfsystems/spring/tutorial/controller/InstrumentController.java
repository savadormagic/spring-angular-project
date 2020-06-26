package ru.dfsystems.spring.tutorial.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import origin.tools.Transformer;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.dto.instrument.InstrumentParams;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Instrument;
import ru.dfsystems.spring.tutorial.service.InstrumentService;

import java.util.List;

@RestController
@RequestMapping(value = "/instrument", produces = "application/json; charset=UTF-8")
@AllArgsConstructor
public class InstrumentController {
    private InstrumentService instrumentService;

    @PostMapping("/list")
    public Page<BaseListDto> getList(PageParams<InstrumentParams> pageParams) {
        Page<Instrument> page = instrumentService.getInstrumentsByParams(pageParams);
        List<BaseListDto> list = Transformer.pojoToOrigin(page.getList(), BaseListDto.class);
        return new Page<>(list, page.getTotalCount());
    }

}
