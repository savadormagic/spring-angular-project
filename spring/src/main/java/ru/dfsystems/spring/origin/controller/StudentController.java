package ru.dfsystems.spring.origin.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dfsystems.spring.origin.dto.BaseListDto;
import ru.dfsystems.spring.origin.dto.Page;
import ru.dfsystems.spring.origin.dto.PageParams;
import ru.dfsystems.spring.origin.dto.student.StudentParams;
import ru.dfsystems.spring.origin.generated.tables.pojos.Student;
import ru.dfsystems.spring.origin.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/student", produces = "application/json; charset=UTF-8")
@AllArgsConstructor
public class StudentController {
    private StudentService studentService;


    @PostMapping("/student")
    public Page<BaseListDto> getList(PageParams<StudentParams> pageParams){
        Page<Student> page = studentService.getStudentByParams(pageParams);
        List<BaseListDto> list = mapper(page.getList());
        return new Page<>(list, page.getTotalCount());
    }

    private List<BaseListDto> mapper(List<Student> allStudents) {
        ModelMapper mapper = new ModelMapper();
        return allStudents.stream()
                .map(r -> mapper.map(r, BaseListDto.class))
                .collect(Collectors.toList());
    }
}
