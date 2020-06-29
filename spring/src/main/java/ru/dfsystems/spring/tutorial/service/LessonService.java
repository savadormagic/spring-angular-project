package ru.dfsystems.spring.tutorial.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dfsystems.spring.tutorial.dao.LessonDaoImpl;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.dto.lesson.LessonDto;
import ru.dfsystems.spring.tutorial.dto.teacher.TeacherDto;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Lesson;
import ru.dfsystems.spring.tutorial.dto.lesson.LessonParams;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Teacher;
import ru.dfsystems.spring.tutorial.mapping.MappingService;

import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor

public class LessonService {
    private LessonDaoImpl lessonDao;
    private MappingService mappingService;

    public Page<BaseListDto> getLessonList(PageParams<LessonParams> pageParams) {
        Page<Lesson> page = lessonDao.getLessonList(pageParams);
        List<BaseListDto> lessonList = mappingService.mapList(page.getList(),BaseListDto.class);
        return new Page<>(lessonList,page.getTotalCount());
    }

    @Transactional
    public void create(LessonDto lessonDto) {
        lessonDao.insert(mappingService.map(lessonDto, Lesson.class));
    }

    public LessonDto get(Integer idd) {
        return mappingService.map(lessonDao.fetchOneById(idd), LessonDto.class);
    }

    @Transactional
    public LessonDto update(Integer idd, LessonDto lessonDto) {
        lessonDao.update(mappingService.map(lessonDto, Lesson.class));
        return lessonDto;
    }

    @Transactional
    public void delete(Integer idd) {
        lessonDao.deleteById(idd);
    }
}
