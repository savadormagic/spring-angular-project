package ru.dfsystems.spring.tutorial.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.dto.teacher.TeacherDto;
import ru.dfsystems.spring.tutorial.dto.teacher.TeacherListDto;
import ru.dfsystems.spring.tutorial.dto.teacher.TeacherParams;
import ru.dfsystems.spring.tutorial.dto.teacher.TeacherParams;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Teacher;
import ru.dfsystems.spring.tutorial.service.TeacherService;
import ru.dfsystems.spring.tutorial.service.TeacherService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/teacher", produces = "application/json; charset=UTF-8")
@AllArgsConstructor
@Api(value = "/teacher", description = "Оперции с учителями")
public class TeacherController {
    private ModelMapper mapper = new ModelMapper();
    private TeacherService teacherService;

    @PostMapping("/list")
    @ApiOperation(value = "Выводит список учителей")
    public Page<TeacherListDto> getList(@RequestBody PageParams<TeacherParams> pageParams) {
        return teacherService.getTeachersByParams(pageParams);
    }

    @PostMapping
    @ApiOperation(value = "Создает учителя")
    public void create(@RequestBody TeacherDto teacherDto) {
        teacherService.create(teacherDto);
    }

    @GetMapping("/{idd}")
    @ApiOperation(value = "Возвращает учителя")
    public TeacherDto get(@PathVariable("idd") Integer idd) {
        return teacherService.get(idd);
    }

    @PatchMapping("/{idd}")
    @ApiOperation(value = "Обновляет учителя")
    public TeacherDto update(@PathVariable("idd") Integer idd, @RequestBody TeacherDto teacherDto) {
        return teacherService.update(idd, teacherDto);
    }

    @DeleteMapping("/{idd}")
    @ApiOperation(value = "Удаляет учителя")
    public void delete(@PathVariable("idd") Integer idd) {
        teacherService.delete(idd);
    }
}
