package ru.dfsystems.spring.tutorial.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dfsystems.spring.tutorial.dto.lesson.LessonDto;
import ru.dfsystems.spring.tutorial.dto.lesson.LessonParams;
import ru.dfsystems.spring.tutorial.service.LessonService;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;

@RestController
@RequestMapping(value = "/lesson", produces = "application/json; charset=UTF-8")
@AllArgsConstructor
@Api(tags =  {"Уроки"}, description = "API для уроков")
public class LessonController {
    private LessonService lessonService;

    @PostMapping("/list")
    public Page<BaseListDto> getList(PageParams<LessonParams> pageParams) {
        return lessonService.getLessonList(pageParams);
    }

    @PostMapping
    @ApiOperation(value = "Создать урок")
    public void create(@RequestBody LessonDto lessonDto) {
        lessonService.create(lessonDto);
    }

    @GetMapping("/{idd}")
    @ApiOperation(value = "Получить урок по idd")
    public LessonDto get(@PathVariable("idd") Integer idd) {
        return lessonService.get(idd);
    }

    @PatchMapping("/{idd}")
    @ApiOperation(value = "Обновить урок")
    public LessonDto update(@PathVariable("idd") Integer idd, @RequestBody LessonDto lessonDto) {
        return lessonService.update(idd, lessonDto);
    }

    @DeleteMapping("/{idd}")
    @ApiOperation(value = "Удалить урок")
    public void delete(@PathVariable("idd") Integer idd) {
        lessonService.delete(idd);
    }

}
