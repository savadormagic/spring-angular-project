package ru.dfsystems.spring.tutorial.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.dto.room.RoomDto;
import ru.dfsystems.spring.tutorial.dto.room.RoomListDto;
import ru.dfsystems.spring.tutorial.dto.room.RoomParams;
import ru.dfsystems.spring.tutorial.service.RoomService;

@RestController
@RequestMapping(value = "/room", produces = "application/json; charset=UTF-8")
@AllArgsConstructor
@Api(tags =  {"Аудитории"}, description = "API для аудиторий")
public class RoomController {
    private RoomService roomService;

    @PostMapping("/list")
    @ApiOperation(value = "Получить список аудиторий")
    public Page<RoomListDto> getList(@RequestBody PageParams<RoomParams> pageParams) {
        return roomService.getRoomsByParams(pageParams);
    }

    @PostMapping
    @ApiOperation(value = "Создать аудиторию")
    public void create(@RequestBody RoomDto roomDto) {
        roomService.create(roomDto);
    }

    @GetMapping("/{idd}")
    @ApiOperation(value = "Получить аудиторию по idd")
    public RoomDto get(@PathVariable("idd") Integer idd) {
        return roomService.get(idd);
    }

    @PatchMapping("/{idd}")
    @ApiOperation(value = "Обновить аудиторию")
    public RoomDto update(@PathVariable("idd") Integer idd, @RequestBody RoomDto roomDto) {
        return roomService.update(idd, roomDto);
    }

    @DeleteMapping("/{idd}")
    @ApiOperation(value = "Удалить аудиторию")
    public void delete(@PathVariable("idd") Integer idd) {
        roomService.delete(idd);
    }

    @PutMapping("/{idd}/instrument")
    public void putInstrument(@PathVariable("idd") Integer idd, @RequestBody Integer instrumentIdd) {
        roomService.putInstrument(idd, instrumentIdd);
    }
}
