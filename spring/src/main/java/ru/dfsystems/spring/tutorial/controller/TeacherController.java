package ru.dfsystems.spring.tutorial.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dfsystems.spring.tutorial.dto.teacher.TeacherParams;
import ru.dfsystems.spring.tutorial.service.TeacherService;
import ru.dfsystems.spring.tutorial.dto.teacher.TeacherDto;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;

@RestController
@RequestMapping(value = "/teacher", produces = "application/json; charset=UTF-8")
@AllArgsConstructor
@Api(tags =  {"Преподаватели"}, description = "API для преподавателей")
public class TeacherController {
    private TeacherService teacherService;

    @PostMapping("/list")
    @ApiOperation(value = "Получить список преподавателей")
    public Page<BaseListDto> getList(PageParams<TeacherParams> pageParams) {
        return teacherService.getTeacherList(pageParams);
    }

    @PostMapping
    @ApiOperation(value = "Создать преподавателя")
    public void create(@RequestBody TeacherParams teacherParams) {
        teacherService.create(teacherParams);
    }

    @GetMapping("/{idd}")
    @ApiOperation(value = "Получить преподавателя по idd")
    public TeacherDto get(@PathVariable("idd") Integer idd) {
        return teacherService.get(idd);
    }

    @PatchMapping("/{idd}")
    @ApiOperation(value = "Обновить преподавателя")
    public TeacherDto update(@PathVariable("idd") Integer idd, @RequestBody TeacherParams teacherParams) {
        return teacherService.update(idd, teacherParams);
    }
    
    @DeleteMapping("/{idd}")
    @ApiOperation(value = "Удалить преподавателя")
    public void delete(@PathVariable("idd") Integer idd) {
        teacherService.delete(idd);
    }

}
