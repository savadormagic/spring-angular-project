package ru.dfsystems.spring.tutorial.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dfsystems.spring.tutorial.dao.CourseDaoImpl;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.dto.course.CourseDto;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Course;
import ru.dfsystems.spring.tutorial.dto.course.CourseParams;
import ru.dfsystems.spring.tutorial.mapping.MappingService;

import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
public class CourseService {
    private CourseDaoImpl courseDao;
    private MappingService mappingService;

    public Page<BaseListDto> getCourseList(PageParams<CourseParams> pageParams) {
        Page<Course> page = courseDao.getCourseList(pageParams);
        List<BaseListDto> courseList = mappingService.mapList(page.getList(),BaseListDto.class);
        return new Page<>(courseList,page.getTotalCount());
    }

    @Transactional
    public void create(CourseDto courseDto) {
        courseDao.create(mappingService.map(courseDto, Course.class));
    }

    public CourseDto get(Integer idd) {
        return mappingService.map(courseDao.getActiveByIdd(idd), CourseDto.class);
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

    @Transactional
    public void delete(Integer idd) {
        Course course = courseDao.getActiveByIdd(idd);
        course.setDeleteDate(LocalDateTime.now());
        courseDao.update(course);
    }
}
