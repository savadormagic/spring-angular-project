package ru.dfsystems.spring.tutorial.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.dto.course.CourseDto;
import ru.dfsystems.spring.tutorial.dto.course.CourseListDto;
import ru.dfsystems.spring.tutorial.dto.room.RoomDto;
import ru.dfsystems.spring.tutorial.dto.room.RoomListDto;
import ru.dfsystems.spring.tutorial.dto.room.RoomParams;
import ru.dfsystems.spring.tutorial.service.CourseService;

@RestController
@RequestMapping(value = "/course", produces = "application/json; charset=UTF-8")
@AllArgsConstructor
public class CourseController {
    private CourseService courseService;
//
//    @PostMapping("/list")
//    public Page<CourseListDto> getList(@RequestBody PageParams<CourseParams> pageParams) {
//        return courseService.getRoomsByParams(pageParams);
//    }

    @PostMapping
    public void create(@RequestBody CourseDto courseDto) {
        courseService.create(courseDto);
    }

    @GetMapping("/{idd}")
    public RoomDto get(@PathVariable("idd") Integer idd) {
        return courseService.get(idd);
    }

    @PatchMapping("/{idd}")
    public RoomDto update(@PathVariable("idd") Integer idd, @RequestBody RoomDto roomDto) {
        return courseService.update(idd, roomDto);
    }

    @DeleteMapping("/{idd}")
    public void delete(@PathVariable("idd") Integer idd) {
        courseService.delete(idd);
    }

    @PutMapping("/{idd}/instrument")
    public void putInstrument(@PathVariable("idd") Integer idd, @RequestBody Integer instrumentIdd) {
        courseService.putInstrument(idd, instrumentIdd);
    }
}
