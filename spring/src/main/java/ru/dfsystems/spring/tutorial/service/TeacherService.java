package ru.dfsystems.spring.tutorial.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dfsystems.spring.tutorial.dao.TeacherDaoImpl;

@Service
@AllArgsConstructor
public class TeacherService {
    private TeacherDaoImpl teacherDao;
}
