package ru.dfsystems.spring.tutorial.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dfsystems.spring.tutorial.dao.CourseDaoImpl;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.dto.course.CourseDto;
import ru.dfsystems.spring.tutorial.dto.course.CourseListDto;
import ru.dfsystems.spring.tutorial.dto.room.RoomDto;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Course;
import ru.dfsystems.spring.tutorial.mapping.MappingService;

@Service
@AllArgsConstructor
public class CourseService {
    private MappingService mappingService;
    private CourseDaoImpl courseDao;

    @Transactional
    public void create(CourseDto courseDto) {
        courseDao.create(mappingService.map(courseDto, Course.class));
    }

    public RoomDto get(Integer idd) {
        return null;
    }

    public RoomDto update(Integer idd, RoomDto roomDto) {
        return null;
    }

    public void delete(Integer idd) {

    }

    public void putInstrument(Integer idd, Integer instrumentIdd) {

    }

//    public Page<CourseListDto> getRoomsByParams(PageParams<CourseParams> pageParams) {
//        return null;
//    }
}
