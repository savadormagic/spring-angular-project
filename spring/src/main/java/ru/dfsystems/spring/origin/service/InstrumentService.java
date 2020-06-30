package ru.dfsystems.spring.origin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dfsystems.spring.origin.dao.InstrumentDaoImpl;
import ru.dfsystems.spring.origin.dto.Page;
import ru.dfsystems.spring.origin.dto.PageParams;
import ru.dfsystems.spring.origin.dto.instrument.InstrumentParams;
import ru.dfsystems.spring.origin.generated.tables.pojos.Instrument;

import java.util.List;

@Service
@AllArgsConstructor
public class InstrumentService {
    private InstrumentDaoImpl instrumentDao;

    public List<Instrument> getAllInstruments(){
        instrumentDao.fetchOneById(152);
        instrumentDao.getActiveByIdd(152);
        return instrumentDao.findAll();
    }

    public Page<Instrument> getInstrumentByParams(PageParams<InstrumentParams> pageParams){
        return instrumentDao.getInstrumentByParams(pageParams);
    }

}
