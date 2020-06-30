package ru.dfsystems.spring.tutorial.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dfsystems.spring.tutorial.dao.InstrumentDaoImpl;
import ru.dfsystems.spring.tutorial.dao.ListDao.InstrumentListDao;
import ru.dfsystems.spring.tutorial.dao.InstrumentDaoImpl;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.dto.instrument.InstrumentParams;
import ru.dfsystems.spring.tutorial.dto.instrument.InstrumentDto;
import ru.dfsystems.spring.tutorial.dto.instrument.InstrumentListDto;
import ru.dfsystems.spring.tutorial.dto.instrument.InstrumentParams;
import ru.dfsystems.spring.tutorial.generated.tables.daos.InstrumentToRoomDao;
import ru.dfsystems.spring.tutorial.generated.tables.daos.LessonToInstrumentsDao;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Instrument;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Instrument;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.InstrumentToRoom;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.LessonToInstruments;
import ru.dfsystems.spring.tutorial.mapping.MappingService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class InstrumentService {
    private InstrumentListDao instrumentListDao;
    private InstrumentDaoImpl instrumentDao;
    private InstrumentToRoomDao instrumentToRoomDao;
    private LessonToInstrumentsDao lessonToInstrumentsDao;
    private MappingService mappingService;

    public Page<InstrumentListDto> getInstrumentsByParams(PageParams<InstrumentParams> pageParams) {
        Page<Instrument> page = instrumentListDao.list(pageParams);
        List<InstrumentListDto> list = mappingService.mapList(page.getList(), InstrumentListDto.class);
        return new Page<>(list, page.getTotalCount());
    }

    @Transactional
    public void create(InstrumentDto instrumentDto) {
        instrumentDao.create(mappingService.map(instrumentDto, Instrument.class));
    }

    public InstrumentDto get(Integer idd) {
        return mappingService.map(instrumentDao.getActiveByIdd(idd), InstrumentDto.class);
    }

    @Transactional
    public void delete(Integer idd) {
        Instrument instrument = instrumentDao.getActiveByIdd(idd);
        instrument.setDeleteDate(LocalDateTime.now());
        instrumentDao.update(instrument);
    }

    @Transactional
    public void putRoom(Integer idd, Integer roomIdd) {
        InstrumentToRoom link = new InstrumentToRoom();
        link.setInstrumentIdd(idd);
        link.setInstrumentIdd(roomIdd);
        instrumentToRoomDao.insert(link);
    }

    @Transactional
    public void putLesson(Integer idd, Integer lessonIdd) {
        LessonToInstruments link = new LessonToInstruments();
        link.setInstrumentIdd(idd);
        link.setInstrumentIdd(lessonIdd);
        lessonToInstrumentsDao.insert(link);
    }

    @Transactional
    public InstrumentDto update(Integer idd, InstrumentDto instrumentDto) {
        Instrument instrument = instrumentDao.getActiveByIdd(idd);
        if (instrument == null){
            throw new RuntimeException("");
        }
        instrument.setDeleteDate(LocalDateTime.now());
        instrumentDao.update(instrument);

        Instrument newInstrument = mappingService.map(instrumentDto, Instrument.class);
        newInstrument.setIdd(instrument.getIdd());
        instrumentDao.create(newInstrument);
        return mappingService.map(newInstrument, InstrumentDto.class);
    }



}
