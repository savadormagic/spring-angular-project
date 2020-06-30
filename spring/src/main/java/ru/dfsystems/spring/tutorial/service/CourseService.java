package ru.dfsystems.spring.tutorial.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dfsystems.spring.tutorial.dao.CourseDaoImpl;
import ru.dfsystems.spring.tutorial.dao.ListDao.CourseListDao;
import ru.dfsystems.spring.tutorial.dao.CourseDaoImpl;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.dto.course.CourseParams;
import ru.dfsystems.spring.tutorial.dto.course.CourseDto;
import ru.dfsystems.spring.tutorial.dto.course.CourseListDto;
import ru.dfsystems.spring.tutorial.dto.course.CourseParams;
import ru.dfsystems.spring.tutorial.generated.tables.daos.LessonToCourseDao;
import ru.dfsystems.spring.tutorial.generated.tables.daos.StudentToCourseDao;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Course;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Course;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.LessonToCourse;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.StudentToCourse;
import ru.dfsystems.spring.tutorial.mapping.MappingService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {
    private CourseListDao courseListDao;
    private CourseDaoImpl courseDao;
    private StudentToCourseDao studentToCourseDao;
    private LessonToCourseDao lessonToCourseDao;
    private MappingService mappingService;

    public Page<CourseListDto> getCoursesByParams(PageParams<CourseParams> pageParams) {
        Page<Course> page = courseListDao.list(pageParams);
        List<CourseListDto> list = mappingService.mapList(page.getList(), CourseListDto.class);
        return new Page<>(list, page.getTotalCount());
    }

    @Transactional
    public void create(CourseDto courseDto) {
        courseDao.create(mappingService.map(courseDto, Course.class));
    }

    public CourseDto get(Integer idd) {
        return mappingService.map(courseDao.getActiveByIdd(idd), CourseDto.class);
    }

    @Transactional
    public void delete(Integer idd) {
        Course course = courseDao.getActiveByIdd(idd);
        course.setDeleteDate(LocalDateTime.now());
        courseDao.update(course);
    }

    @Transactional
    public void putStudent(Integer idd, Integer studentIdd) {
        StudentToCourse link = new StudentToCourse();
        link.setCourseIdd(idd);
        link.setStudentIdd(studentIdd);
        studentToCourseDao.insert(link);
    }

    @Transactional
    public void putLesson(Integer idd, Integer lessonIdd) {
        LessonToCourse link = new LessonToCourse();
        link.setCourseIdd(idd);
        link.setLessonIdd(lessonIdd);
        lessonToCourseDao.insert(link);
    }

    @Transactional
    public CourseDto update(Integer idd, CourseDto courseDto) {
        Course course = courseDao.getActiveByIdd(idd);
        if (course == null){
            throw new RuntimeException("");
        }
        course.setDeleteDate(LocalDateTime.now());
        courseDao.update(course);

        Course newCourse = mappingService.map(courseDto, Course.class);
        newCourse.setIdd(course.getIdd());
        courseDao.create(newCourse);
        return mappingService.map(newCourse, CourseDto.class);
    }

}
