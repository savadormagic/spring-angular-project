package ru.dfsystems.spring.origin.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dfsystems.spring.origin.dto.BaseListDto;
import ru.dfsystems.spring.origin.dto.Page;
import ru.dfsystems.spring.origin.dto.PageParams;
import ru.dfsystems.spring.origin.dto.room.RoomParams;
import ru.dfsystems.spring.origin.generated.tables.pojos.Room;
import ru.dfsystems.spring.origin.service.RoomService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/room", produces = "application/json; charset=UTF-8")
@AllArgsConstructor
public class RoomController {
    private RoomService roomService;
    private ModelMapper mapper = new ModelMapper();

    @PostMapping("/room")
    public Page<BaseListDto> getList(PageParams<RoomParams> pageParams) {
        Page<Room> page = roomService.getRoomsByParams(pageParams);
        List<BaseListDto> list = mapper(page.getList());
        return new Page<>(list, page.getTotalCount());
    }

    private List<BaseListDto> mapper(List<Room> allRooms) {
        return allRooms.stream()
                .map(r -> mapper.map(r, BaseListDto.class))
                .collect(Collectors.toList());
    }
}