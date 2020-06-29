package ru.dfsystems.spring.tutorial.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.dto.instrument.InstrumentDto;
import ru.dfsystems.spring.tutorial.dto.instrument.InstrumentParams;
import ru.dfsystems.spring.tutorial.service.InstrumentService;

@RestController
@RequestMapping(value = "/instrument", produces = "application/json; charset=UTF-8")
@AllArgsConstructor
@Api(tags =  {"Оборудование"}, description = "API для оборудования")
public class InstrumentController {
    private InstrumentService instrumentService;

    @PostMapping("/list")
    @ApiOperation(value = "Получить список оборудования")
    public Page<BaseListDto> getList(PageParams<InstrumentParams> pageParams) {
        return instrumentService.getInstrumentsByParams(pageParams);
    }


    @PostMapping
    @ApiOperation(value = "Создать оборудование")
    public void create(@RequestBody InstrumentDto teacherDto) {
        instrumentService.create(teacherDto);
    }

    @GetMapping("/{idd}")
    @ApiOperation(value = "Получить оборудование по idd")
    public InstrumentDto get(@PathVariable("idd") Integer idd) {
        return instrumentService.get(idd);
    }

    @PatchMapping("/{idd}")
    @ApiOperation(value = "Обновить оборудование")
    public InstrumentDto update(@PathVariable("idd") Integer idd, @RequestBody InstrumentDto teacherDto) {
        return instrumentService.update(idd, teacherDto);
    }

    @DeleteMapping("/{idd}")
    @ApiOperation(value = "Удалить оборудование")
    public void delete(@PathVariable("idd") Integer idd) {
        instrumentService.delete(idd);
    }
}
