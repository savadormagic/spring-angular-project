package ru.dfsystems.spring.origin.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dfsystems.spring.origin.dto.BaseListDto;
import ru.dfsystems.spring.origin.dto.Page;
import ru.dfsystems.spring.origin.dto.PageParams;
import ru.dfsystems.spring.origin.dto.user.UserParams;
import ru.dfsystems.spring.origin.generated.tables.pojos.Users;
import ru.dfsystems.spring.origin.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users", produces = "application/json; charset=UTF-8")
@AllArgsConstructor
public class UserController {
    private UserService userService;


    @PostMapping("/list")
    public Page<BaseListDto> getList(PageParams<UserParams> pageParams) {
        Page<Users> page = userService.getUsersByParams(pageParams);
        List<BaseListDto> list = mapper(page.getList());
        return new Page<>(list, page.getTotalCount());
    }

    private List<BaseListDto> mapper(List<Users> allStudents) {
        ModelMapper mapper = new ModelMapper();
        return allStudents.stream()
                .map(r -> mapper.map(r, BaseListDto.class))
                .collect(Collectors.toList());
    }
}