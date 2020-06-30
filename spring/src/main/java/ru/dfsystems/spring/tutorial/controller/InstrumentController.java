package ru.dfsystems.spring.tutorial.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.dto.instrument.InstrumentDto;
import ru.dfsystems.spring.tutorial.dto.instrument.InstrumentListDto;
import ru.dfsystems.spring.tutorial.dto.instrument.InstrumentParams;
import ru.dfsystems.spring.tutorial.dto.instrument.InstrumentParams;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Instrument;
import ru.dfsystems.spring.tutorial.service.InstrumentService;
import ru.dfsystems.spring.tutorial.service.InstrumentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/instrument", produces = "application/json; charset=UTF-8")
@AllArgsConstructor
@Api(value = "/instrument", description = "Оперции с инструментами")
public class InstrumentController {
    private InstrumentService instrumentService;

    @PostMapping("/list")
    @ApiOperation(value = "Возвращает список инструментов")
    public Page<InstrumentListDto> getList(@RequestBody PageParams<InstrumentParams> pageParams) {
        return instrumentService.getInstrumentsByParams(pageParams);
    }

    @PostMapping
    @ApiOperation(value = "Создает инструмент")
    public void create(@RequestBody InstrumentDto instrumentDto) {
        instrumentService.create(instrumentDto);
    }

    @GetMapping("/{idd}")
    @ApiOperation(value = "Возвращает инструмент")
    public InstrumentDto get(@PathVariable("idd") Integer idd) {
        return instrumentService.get(idd);
    }

    @PatchMapping("/{idd}")
    @ApiOperation(value = "Обновляет инструмент")
    public InstrumentDto update(@PathVariable("idd") Integer idd, @RequestBody InstrumentDto instrumentDto) {
        return instrumentService.update(idd, instrumentDto);
    }

    @DeleteMapping("/{idd}")
    @ApiOperation(value = "Удаляет инструмент")
    public void delete(@PathVariable("idd") Integer idd) {
        instrumentService.delete(idd);
    }

    @PutMapping("/{idd}/instrument")
    @ApiOperation(value = "Присваивает инструмент уроку")
    public void putLesson(@PathVariable("idd") Integer idd, @RequestBody Integer lessonIdd) {
        instrumentService.putLesson(idd, lessonIdd);
    }

    @PutMapping("/{idd}/room")
    @ApiOperation(value = "Присваивает инструмент комнате")
    public void putRoom(@PathVariable("idd") Integer idd, @RequestBody Integer roomIdd) {
        instrumentService.putRoom(idd, roomIdd);
    }

}
