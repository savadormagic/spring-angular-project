package ru.dfsystems.spring.tutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dfsystems.spring.tutorial.dao.instrument.InstrumentDaoImpl;
import ru.dfsystems.spring.tutorial.dao.lesson.LessonDaoImpl;
import ru.dfsystems.spring.tutorial.dao.lesson.LessonListDao;
import ru.dfsystems.spring.tutorial.dto.instrument.InstrumentListDto;
import ru.dfsystems.spring.tutorial.dto.lesson.LessonDto;
import ru.dfsystems.spring.tutorial.dto.lesson.LessonHistoryDto;
import ru.dfsystems.spring.tutorial.dto.lesson.LessonListDto;
import ru.dfsystems.spring.tutorial.dto.lesson.LessonParams;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.LessonToInstrument;
import ru.dfsystems.spring.tutorial.generated.tables.daos.LessonToInstrumentDao;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Lesson;
import ru.dfsystems.spring.tutorial.mapping.MappingService;

import java.util.List;

@Service
public class LessonService extends BaseService<LessonHistoryDto, LessonListDto, LessonDto, LessonParams, Lesson> {
    private final InstrumentDaoImpl instrumentDao;
    private final MappingService mappingService;
    private final LessonToInstrumentDao lessonToInstrumentDao;

    @Autowired
    public LessonService(MappingService mappingService, LessonListDao lessonListDao, LessonDaoImpl lessonDao, InstrumentDaoImpl instrumentDao, LessonToInstrumentDao lessonToInstrumentDao) {
        super(mappingService, lessonListDao, lessonDao, LessonListDto.class, LessonDto.class, Lesson.class);
        this.instrumentDao = instrumentDao;
        this.mappingService = mappingService;
        this.lessonToInstrumentDao = lessonToInstrumentDao;
    }

    public List<InstrumentListDto> getInstruments(Integer lessonIdd) {
        return mappingService.mapList(instrumentDao.getInstrumentsByLessonIdd(lessonIdd), InstrumentListDto.class);
    }

    @Transactional
    public void putInstrument(Integer idd, Integer instrumentIdd) {
        var link = new LessonToInstrument();
        link.setLessonIdd(idd);
        link.setInstrumentIdd(instrumentIdd);
        lessonToInstrumentDao.insert(link);
    }
}
