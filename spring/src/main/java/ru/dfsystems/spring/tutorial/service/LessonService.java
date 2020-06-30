package ru.dfsystems.spring.tutorial.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dfsystems.spring.tutorial.dao.LessonDaoImpl;
import ru.dfsystems.spring.tutorial.dao.ListDao.LessonListDao;
import ru.dfsystems.spring.tutorial.dao.LessonDaoImpl;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.dto.lesson.LessonParams;
import ru.dfsystems.spring.tutorial.dto.lesson.LessonDto;
import ru.dfsystems.spring.tutorial.dto.lesson.LessonListDto;
import ru.dfsystems.spring.tutorial.dto.lesson.LessonParams;
import ru.dfsystems.spring.tutorial.generated.tables.daos.LessonToInstrumentsDao;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Lesson;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Lesson;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.LessonToInstruments;
import ru.dfsystems.spring.tutorial.mapping.MappingService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class LessonService {
    private LessonListDao lessonListDao;
    private LessonDaoImpl lessonDao;
    private LessonToInstrumentsDao lessonToInstrumentsDao;
    private MappingService mappingService;

    public Page<LessonListDto> getLessonsByParams(PageParams<LessonParams> pageParams) {
        Page<Lesson> page = lessonListDao.list(pageParams);
        List<LessonListDto> list = mappingService.mapList(page.getList(), LessonListDto.class);
        return new Page<>(list, page.getTotalCount());
    }

    @Transactional
    public void create(LessonDto lessonDto) {
        lessonDao.create(mappingService.map(lessonDto, Lesson.class));
    }

    public LessonDto get(Integer idd) {
        return mappingService.map(lessonDao.getActiveByIdd(idd), LessonDto.class);
    }

    @Transactional
    public void delete(Integer idd) {
        Lesson lesson = lessonDao.getActiveByIdd(idd);
        lesson.setDeleteDate(LocalDateTime.now());
        lessonDao.update(lesson);
    }

    @Transactional
    public void putInstrument(Integer idd, Integer instrumentIdd) {
        LessonToInstruments link = new LessonToInstruments();
        link.setLessonIdd(idd);
        link.setInstrumentIdd(instrumentIdd);
        lessonToInstrumentsDao.insert(link);
    }

    @Transactional
    public LessonDto update(Integer idd, LessonDto lessonDto) {
        Lesson lesson = lessonDao.getActiveByIdd(idd);
        if (lesson == null){
            throw new RuntimeException("");
        }
        lesson.setDeleteDate(LocalDateTime.now());
        lessonDao.update(lesson);

        Lesson newLesson = mappingService.map(lessonDto, Lesson.class);
        newLesson.setIdd(lesson.getIdd());
        lessonDao.create(newLesson);
        return mappingService.map(newLesson, LessonDto.class);
    }

}
