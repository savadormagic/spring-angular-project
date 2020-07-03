package ru.dfsystems.spring.origin.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dfsystems.spring.origin.dto.BaseListDto;
import ru.dfsystems.spring.origin.dto.Page;
import ru.dfsystems.spring.origin.dto.PageParams;
import ru.dfsystems.spring.origin.dto.room.RoomDto;
import ru.dfsystems.spring.origin.dto.room.RoomHistoryDto;
import ru.dfsystems.spring.origin.dto.room.RoomListDto;
import ru.dfsystems.spring.origin.dto.room.RoomParams;
import ru.dfsystems.spring.origin.service.RoomService;

import java.util.List;

@RestController
@RequestMapping(value = "/room", produces = "application/json; charset=UTF-8")
@AllArgsConstructor
public class RoomController {
    private RoomService roomService;

    @PostMapping("/list")
    public Page<RoomListDto> getList(@RequestBody PageParams<RoomParams> pageParams) {
        return roomService.getRoomsByParams(pageParams);
    }

    @PostMapping
    public void create(@RequestBody RoomDto roomDto){
        roomService.create(roomDto);
    }

    @GetMapping("/{idd}")
    public RoomDto get(@PathVariable("idd") Integer idd){
        return roomService.get(idd);
    }

    @GetMapping("/{idd}/history")
    public List<RoomHistoryDto> getHistory(@PathVariable("idd") Integer idd){
        return roomService.getHistory(idd);
    }

    @DeleteMapping("/{idd}")
    public void delete(@PathVariable("idd") Integer idd){
        roomService.delete(idd);
    }

    @PutMapping("/{idd}/instrument")
    public void putInstrument(@PathVariable("idd") Integer idd, @RequestBody Integer instrumentIdd){
        roomService.putInstrument(idd, instrumentIdd);
    }
}