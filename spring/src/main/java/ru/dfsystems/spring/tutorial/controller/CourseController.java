package ru.dfsystems.spring.tutorial.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dfsystems.spring.tutorial.dto.course.CourseDto;
import ru.dfsystems.spring.tutorial.dto.course.CourseParams;
import ru.dfsystems.spring.tutorial.service.CourseService;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;

@RestController
@RequestMapping(value = "/course", produces = "application/json; charset=UTF-8")
@AllArgsConstructor
@Api(tags =  {"Курсы"}, description = "API для курсов")
public class CourseController {
    private CourseService courseService;

    @PostMapping("/list")
    @ApiOperation(value = "Получить список курсов")
    public Page<BaseListDto> getList(PageParams<CourseParams> pageParams) {
        return courseService.getCourseList(pageParams);
    }


    @PostMapping
    @ApiOperation(value = "Создать курс")
    public void create(@RequestBody CourseDto teacherDto) {
        courseService.create(teacherDto);
    }

    @GetMapping("/{idd}")
    @ApiOperation(value = "Получить курс по idd")
    public CourseDto get(@PathVariable("idd") Integer idd) {
        return courseService.get(idd);
    }

    @PatchMapping("/{idd}")
    @ApiOperation(value = "Обновить курс")
    public CourseDto update(@PathVariable("idd") Integer idd, @RequestBody CourseDto teacherDto) {
        return courseService.update(idd, teacherDto);
    }

    @DeleteMapping("/{idd}")
    @ApiOperation(value = "Удалить курс")
    public void delete(@PathVariable("idd") Integer idd) {
        courseService.delete(idd);
    }

}
