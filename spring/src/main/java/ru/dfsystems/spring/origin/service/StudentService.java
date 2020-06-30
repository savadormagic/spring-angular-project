package ru.dfsystems.spring.origin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dfsystems.spring.origin.dao.StudentDaoImpl;
import ru.dfsystems.spring.origin.dto.Page;
import ru.dfsystems.spring.origin.dto.PageParams;
import ru.dfsystems.spring.origin.dto.student.StudentParams;
import ru.dfsystems.spring.origin.generated.tables.pojos.Student;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private StudentDaoImpl studentDao;

    public List<Student> getAllStudent(){
        studentDao.fetchOneById(152);
        studentDao.getActiveByIdd(152);
        return studentDao.findAll();
    }
    public Page<Student> getStudentByParams(PageParams<StudentParams> pageParams) {
        return studentDao.getStudentByParams(pageParams);
    }
}
