package ru.dfsystems.spring.tutorial.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dfsystems.spring.tutorial.dao.ListDao.StudentListDao;
import ru.dfsystems.spring.tutorial.dao.StudentDaoImpl;
import ru.dfsystems.spring.tutorial.dao.StudentDaoImpl;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.dto.student.StudentDto;
import ru.dfsystems.spring.tutorial.dto.student.StudentListDto;
import ru.dfsystems.spring.tutorial.dto.student.StudentParams;
import ru.dfsystems.spring.tutorial.dto.student.StudentParams;
import ru.dfsystems.spring.tutorial.generated.tables.daos.StudentToCourseDao;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Student;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Student;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.StudentToCourse;
import ru.dfsystems.spring.tutorial.mapping.MappingService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private StudentListDao studentListDao;
    private StudentDaoImpl studentDao;
    private StudentToCourseDao studentToCourseDao;
    private MappingService mappingService;

    public Page<StudentListDto> getStudentsByParams(PageParams<StudentParams> pageParams) {
        Page<Student> page = studentListDao.list(pageParams);
        List<StudentListDto> list = mappingService.mapList(page.getList(), StudentListDto.class);
        return new Page<>(list, page.getTotalCount());
    }

    @Transactional
    public void create(StudentDto studentDto) {
        studentDao.create(mappingService.map(studentDto, Student.class));
    }

    public StudentDto get(Integer idd) {
        return mappingService.map(studentDao.getActiveByIdd(idd), StudentDto.class);
    }

    @Transactional
    public void delete(Integer idd) {
        Student student = studentDao.getActiveByIdd(idd);
        student.setDeleteDate(LocalDateTime.now());
        studentDao.update(student);
    }

    @Transactional
    public void putCourse(Integer idd, Integer courseIdd) {
        StudentToCourse link = new StudentToCourse();
        link.setStudentIdd(idd);
        link.setCourseIdd(courseIdd);
        studentToCourseDao.insert(link);
    }

    @Transactional
    public StudentDto update(Integer idd, StudentDto studentDto) {
        Student student = studentDao.getActiveByIdd(idd);
        if (student == null){
            throw new RuntimeException("");
        }
        student.setDeleteDate(LocalDateTime.now());
        studentDao.update(student);

        Student newStudent = mappingService.map(studentDto, Student.class);
        newStudent.setIdd(student.getIdd());
        studentDao.create(newStudent);
        return mappingService.map(newStudent, StudentDto.class);
    }
}
