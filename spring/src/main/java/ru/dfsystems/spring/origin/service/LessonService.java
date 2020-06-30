package ru.dfsystems.spring.origin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dfsystems.spring.origin.dao.LessonDaoImpl;
import ru.dfsystems.spring.origin.dto.Page;
import ru.dfsystems.spring.origin.dto.PageParams;
import ru.dfsystems.spring.origin.dto.lesson.LessonParams;
import ru.dfsystems.spring.origin.generated.tables.pojos.Lesson;

import java.util.List;

@Service
@AllArgsConstructor
public class LessonService {
    private LessonDaoImpl lessonDao;

    public List<Lesson> getAllLessons() {
        return lessonDao.findAll();
    }

    public Page<Lesson> getLessonsByParams(PageParams<LessonParams> pageParams) {
        return lessonDao.getLessonsByParams(pageParams);
    }
}