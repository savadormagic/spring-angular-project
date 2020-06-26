package ru.dfsystems.spring.tutorial.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dfsystems.spring.tutorial.dao.CourseDaoImpl;

@Service
@AllArgsConstructor
public class CourseService {
    private CourseDaoImpl courseDao;

}
