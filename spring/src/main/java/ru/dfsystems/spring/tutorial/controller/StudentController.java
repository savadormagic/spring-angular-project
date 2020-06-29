package ru.dfsystems.spring.tutorial.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dfsystems.spring.tutorial.dto.student.StudentDto;
import ru.dfsystems.spring.tutorial.dto.student.StudentParams;
import ru.dfsystems.spring.tutorial.service.StudentService;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;

@RestController
@RequestMapping(value = "/student", produces = "application/json; charset=UTF-8")
@AllArgsConstructor
@Api(tags =  {"Студенты"}, description = "API для студентов")
public class StudentController {
    private StudentService studentService;

    @PostMapping("/list")
    @ApiOperation(value = "Получить список студентов")
    public Page<BaseListDto> getList(PageParams<StudentParams> pageParams) {
        return studentService.getStudentList(pageParams);
    }
    
    @PostMapping
    @ApiOperation(value = "Создать студента")
    public void create(@RequestBody StudentParams studentParams) {
        studentService.create(studentParams);
    }

    @GetMapping("/{idd}")
    @ApiOperation(value = "Получить студента по idd")
    public StudentDto get(@PathVariable("idd") Integer idd) {
        return studentService.get(idd);
    }

    @PatchMapping("/{idd}")
    @ApiOperation(value = "Обновить студента")
    public StudentDto update(@PathVariable("idd") Integer idd, @RequestBody StudentParams studentParams) {
        return studentService.update(idd, studentParams);
    }

    @DeleteMapping("/{idd}")
    @ApiOperation(value = "Удалить студента")
    public void delete(@PathVariable("idd") Integer idd) {
        studentService.delete(idd);
    }

}
