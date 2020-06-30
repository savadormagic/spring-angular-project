package ru.dfsystems.spring.tutorial.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.dto.student.StudentDto;
import ru.dfsystems.spring.tutorial.dto.student.StudentListDto;
import ru.dfsystems.spring.tutorial.dto.student.StudentParams;
import ru.dfsystems.spring.tutorial.dto.student.StudentParams;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Student;
import ru.dfsystems.spring.tutorial.service.StudentService;
import ru.dfsystems.spring.tutorial.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/student", produces = "application/json; charset=UTF-8")
@AllArgsConstructor
@Api(value = "/student", description = "Оперции со студентами")
public class StudentController {
    private StudentService studentService;

    @PostMapping("/list")
    @ApiOperation(value = "Возвращает список студентов")
    public Page<StudentListDto> getList(@RequestBody PageParams<StudentParams> pageParams) {
        return studentService.getStudentsByParams(pageParams);
    }

    @PostMapping
    @ApiOperation(value = "Создает студента")
    public void create(@RequestBody StudentDto studentDto) {
        studentService.create(studentDto);
    }

    @GetMapping("/{idd}")
    @ApiOperation(value = "Возвращает студента")
    public StudentDto get(@PathVariable("idd") Integer idd) {
        return studentService.get(idd);
    }

    @PatchMapping("/{idd}")
    @ApiOperation(value = "Обновляет студента")
    public StudentDto update(@PathVariable("idd") Integer idd, @RequestBody StudentDto studentDto) {
        return studentService.update(idd, studentDto);
    }

    @DeleteMapping("/{idd}")
    @ApiOperation(value = "Удяляет студента")
    public void delete(@PathVariable("idd") Integer idd) {
        studentService.delete(idd);
    }

    @PutMapping("/{idd}/course")
    @ApiOperation(value = "Записывает студента на курс")
    public void putCourse(@PathVariable("idd") Integer idd, @RequestBody Integer courseIdd) {
        studentService.putCourse(idd, courseIdd);
    }
}
