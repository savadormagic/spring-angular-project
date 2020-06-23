package ru.dfsystems.spring.tutorial.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dfsystems.spring.tutorial.generated.tables.daos.StudentDao;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Student;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private StudentDao studentDao;

    public List<Student> getAllStudents() {
        return studentDao.findAll();
    }

}
