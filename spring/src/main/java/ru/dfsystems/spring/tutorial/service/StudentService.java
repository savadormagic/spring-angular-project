package ru.dfsystems.spring.tutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dfsystems.spring.tutorial.dao.course.CourseDaoImpl;
import ru.dfsystems.spring.tutorial.dao.student.StudentDaoImpl;
import ru.dfsystems.spring.tutorial.dao.student.StudentListDao;
import ru.dfsystems.spring.tutorial.dto.course.CourseListDto;

import ru.dfsystems.spring.tutorial.dto.student.StudentDto;
import ru.dfsystems.spring.tutorial.dto.student.StudentHistoryDto;
import ru.dfsystems.spring.tutorial.dto.student.StudentListDto;
import ru.dfsystems.spring.tutorial.dto.student.StudentParams;
import ru.dfsystems.spring.tutorial.generated.tables.daos.StudentToCourseDao;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Student;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.StudentToCourse;
import ru.dfsystems.spring.tutorial.mapping.MappingService;

import java.util.List;

@Service
public class StudentService  extends BaseService<StudentHistoryDto, StudentListDto, StudentDto, StudentParams, Student> {
    private final CourseDaoImpl courseDao;
    private final StudentToCourseDao studentToCourseDao;
    private final MappingService mappingService;

    @Autowired
    public StudentService(MappingService mappingService, StudentListDao studentListDao, StudentDaoImpl studentDao, CourseDaoImpl courseDao, StudentToCourseDao studentToCourseDao) {
        super(mappingService, studentListDao, studentDao, StudentListDto.class, StudentDto.class, Student.class);
        this.courseDao = courseDao;
        this.mappingService = mappingService;
        this.studentToCourseDao = studentToCourseDao;
    }

    public List<CourseListDto> getCourses(Integer studentIdd) {
        return mappingService.mapList(courseDao.getCoursesByStudentIdd(studentIdd), CourseListDto.class);
    }

    @Transactional
    public void enrollInCourse(Integer idd, Integer courseIdd) {
        var link = new StudentToCourse();
        link.setStudentIdd(idd);
        link.setCourseIdd(courseIdd);
        studentToCourseDao.insert(link);
    }
}
