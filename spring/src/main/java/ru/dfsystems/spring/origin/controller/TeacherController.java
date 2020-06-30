package ru.dfsystems.spring.origin.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dfsystems.spring.origin.dto.BaseListDto;
import ru.dfsystems.spring.origin.dto.Page;
import ru.dfsystems.spring.origin.dto.PageParams;
import ru.dfsystems.spring.origin.dto.teacher.TeacherParams;
import ru.dfsystems.spring.origin.generated.tables.pojos.Teacher;
import ru.dfsystems.spring.origin.service.TeacherService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/student", produces = "application/json; charset=UTF-8")
@AllArgsConstructor
public class TeacherController {
    private TeacherService teacherService;
    private ModelMapper mapper = new ModelMapper();

    @PostMapping("/list")
    public Page<BaseListDto> getList(PageParams<TeacherParams> pageParams) {
        Page<Teacher> page = teacherService.getTeachersByParams(pageParams);
        List<BaseListDto> list = mapper(page.getList());
        return new Page<>(list, page.getTotalCount());
    }

    private List<BaseListDto> mapper(List<Teacher> allTeachers) {
        return allTeachers.stream()
                .map(r -> mapper.map(r, BaseListDto.class))
                .collect(Collectors.toList());
    }
}