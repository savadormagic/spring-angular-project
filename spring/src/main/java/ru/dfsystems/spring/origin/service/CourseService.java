package ru.dfsystems.spring.origin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dfsystems.spring.origin.dao.CourseDaoImpl;
import ru.dfsystems.spring.origin.dto.Course.CourseParams;
import ru.dfsystems.spring.origin.dto.Page;
import ru.dfsystems.spring.origin.dto.PageParams;
import ru.dfsystems.spring.origin.generated.tables.pojos.Course;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {
    private CourseDaoImpl courseDao;

    public List<Course> getAllCourse(){
        courseDao.fetchOneById(152);
        courseDao.getActiveByIdd(152);
        return courseDao.findAll();
    }

    public Page<Course> getCoursesByParams(PageParams<CourseParams> pageParams) {
        return courseDao.getCoursesByParams(pageParams);
    }

}
