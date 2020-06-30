package ru.dfsystems.spring.tutorial.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.dto.course.CourseParams;
import ru.dfsystems.spring.tutorial.dto.course.CourseDto;
import ru.dfsystems.spring.tutorial.dto.course.CourseListDto;
import ru.dfsystems.spring.tutorial.dto.course.CourseParams;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Course;
import ru.dfsystems.spring.tutorial.service.CourseService;
import ru.dfsystems.spring.tutorial.service.CourseService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/course", produces = "application/json; charset=UTF-8")
@AllArgsConstructor
@Api(value = "/course", description = "Оперции с курсами")
public class CourseController {
    private CourseService courseService;

    @PostMapping("/list")
    public Page<CourseListDto> getList(@RequestBody PageParams<CourseParams> pageParams) {
        return courseService.getCoursesByParams(pageParams);
    }

    @PostMapping
    public void create(@RequestBody CourseDto courseDto) {
        courseService.create(courseDto);
    }

    @GetMapping("/{idd}")
    public CourseDto get(@PathVariable("idd") Integer idd) {
        return courseService.get(idd);
    }

    @PatchMapping("/{idd}")
    public CourseDto update(@PathVariable("idd") Integer idd, @RequestBody CourseDto courseDto) {
        return courseService.update(idd, courseDto);
    }

    @DeleteMapping("/{idd}")
    public void delete(@PathVariable("idd") Integer idd) {
        courseService.delete(idd);
    }

    @PutMapping("/{idd}/lesson")
    public void putLesson(@PathVariable("idd") Integer idd, @RequestBody Integer lessonIdd) {
        courseService.putLesson(idd, lessonIdd);
    }

    @PutMapping("/{idd}/student")
    public void putStudent(@PathVariable("idd") Integer idd, @RequestBody Integer lessonIdd) {
        courseService.putStudent(idd, lessonIdd);
    }

}
