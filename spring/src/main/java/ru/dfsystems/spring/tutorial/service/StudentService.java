package ru.dfsystems.spring.tutorial.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dfsystems.spring.tutorial.dao.StudentDaoImpl;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.dto.student.StudentDto;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Student;
import ru.dfsystems.spring.tutorial.dto.student.StudentParams;
import ru.dfsystems.spring.tutorial.mapping.MappingService;

import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor

public class StudentService {
    private StudentDaoImpl studentDao;
    private MappingService mappingService;

    public Page<BaseListDto> getStudentList(PageParams<StudentParams> pageParams) {
        Page<Student> page = studentDao.getStudentList(pageParams);
        List<BaseListDto> studentList = mappingService.mapList(page.getList(),BaseListDto.class);
        return new Page<>(studentList,page.getTotalCount());
    }

    @Transactional
    public void create(StudentParams studentParams) {
        studentDao.create(mappingService.map(studentParams, Student.class));
    }

    public StudentDto get(Integer idd) {
        return mappingService.map(studentDao.getActiveByIdd(idd), StudentDto.class);
    }

    @Transactional
    public StudentDto update(Integer idd, StudentParams studentParams) {
        Student student = studentDao.getActiveByIdd(idd);
        if (student == null){
            throw new RuntimeException("");
        }
        student.setDeleteDate(LocalDateTime.now());
        studentDao.update(student);

        Student newStudent = mappingService.map(studentParams, Student.class);
        newStudent.setIdd(student.getIdd());
        studentDao.create(newStudent);
        return mappingService.map(newStudent, StudentDto.class);
    }

    @Transactional
    public void delete(Integer idd) {
        Student student = studentDao.getActiveByIdd(idd);
        student.setDeleteDate(LocalDateTime.now());
        studentDao.update(student);
    }
}
