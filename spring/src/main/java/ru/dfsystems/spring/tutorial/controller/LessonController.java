package ru.dfsystems.spring.tutorial.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.dto.lesson.LessonDto;
import ru.dfsystems.spring.tutorial.dto.lesson.LessonListDto;
import ru.dfsystems.spring.tutorial.dto.lesson.LessonParams;
import ru.dfsystems.spring.tutorial.dto.lesson.LessonParams;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Lesson;
import ru.dfsystems.spring.tutorial.service.LessonService;
import ru.dfsystems.spring.tutorial.service.LessonService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/lesson", produces = "application/json; charset=UTF-8")
@AllArgsConstructor
@Api(value = "/lesson", description = "Оперции с уроками")
public class LessonController {
    private LessonService lessonService;

    @PostMapping("/list")
    @ApiOperation(value = "Возвращает список уроков")
    public Page<LessonListDto> getList(@RequestBody PageParams<LessonParams> pageParams) {
        return lessonService.getLessonsByParams(pageParams);
    }

    @PostMapping
    @ApiOperation(value = "Создает урок")
    public void create(@RequestBody LessonDto lessonDto) {
        lessonService.create(lessonDto);
    }

    @GetMapping("/{idd}")
    @ApiOperation(value = "Возвращает урок")
    public LessonDto get(@PathVariable("idd") Integer idd) {
        return lessonService.get(idd);
    }

    @PatchMapping("/{idd}")
    @ApiOperation(value = "Обновляет урок")
    public LessonDto update(@PathVariable("idd") Integer idd, @RequestBody LessonDto lessonDto) {
        return lessonService.update(idd, lessonDto);
    }

    @DeleteMapping("/{idd}")
    @ApiOperation(value = "Удяляет урок")
    public void delete(@PathVariable("idd") Integer idd) {
        lessonService.delete(idd);
    }

    @PutMapping("/{idd}/instrument")
    @ApiOperation(value = "Присваивает инструмент уроку")
    public void putLesson(@PathVariable("idd") Integer idd, @RequestBody Integer lessonIdd) {
        lessonService.putInstrument(idd, lessonIdd);
    }
}
