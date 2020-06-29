package ru.dfsystems.spring.tutorial.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dfsystems.spring.tutorial.dao.InstrumentDaoImpl;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.dto.instrument.InstrumentDto;
import ru.dfsystems.spring.tutorial.dto.instrument.InstrumentParams;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Instrument;
import ru.dfsystems.spring.tutorial.mapping.MappingService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class InstrumentService {
    private InstrumentDaoImpl instrumentDao;
    private MappingService mappingService;

    public Page<BaseListDto> getInstrumentsByParams(PageParams<InstrumentParams> pageParams) {
        Page<Instrument> page = instrumentDao.getInstrumentsByParams(pageParams);
        List<BaseListDto> instrumentList = mappingService.mapList(page.getList(),BaseListDto.class);
        return new Page<>(instrumentList,page.getTotalCount());
    }


    @Transactional
    public void create(InstrumentDto instrumentDto) {
        instrumentDao.create(mappingService.map(instrumentDto, Instrument.class));
    }

    public InstrumentDto get(Integer idd) {
        return mappingService.map(instrumentDao.getActiveByIdd(idd), InstrumentDto.class);
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

    @Transactional
    public void delete(Integer idd) {
        Instrument instrument = instrumentDao.getActiveByIdd(idd);
        instrument.setDeleteDate(LocalDateTime.now());
        instrumentDao.update(instrument);
    }
}
