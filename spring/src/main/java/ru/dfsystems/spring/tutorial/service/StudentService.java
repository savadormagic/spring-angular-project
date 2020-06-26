package ru.dfsystems.spring.tutorial.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dfsystems.spring.tutorial.dao.StudentDaoImpl;

@Service
@AllArgsConstructor
public class StudentService {
    private StudentDaoImpl studentDao;
}
