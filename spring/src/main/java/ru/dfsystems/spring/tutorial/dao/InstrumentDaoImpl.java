package ru.dfsystems.spring.tutorial.dao;

import lombok.val;
import lombok.var;
import org.jooq.DSLContext;
import org.jooq.SelectSeekStepN;
import org.jooq.SortField;
import org.springframework.stereotype.Repository;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.dto.instrument.InstrumentParams;
import ru.dfsystems.spring.tutorial.generated.Sequences;
import ru.dfsystems.spring.tutorial.generated.tables.daos.InstrumentDao;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Instrument;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Instrument;
import ru.dfsystems.spring.tutorial.generated.tables.records.InstrumentRecord;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static ru.dfsystems.spring.tutorial.generated.tables.Instrument.INSTRUMENT;
import static ru.dfsystems.spring.tutorial.generated.tables.Instrument.INSTRUMENT;
import static ru.dfsystems.spring.tutorial.generated.tables.InstrumentToRoom.INSTRUMENT_TO_ROOM;

@Repository
public class InstrumentDaoImpl extends InstrumentDao {
    final DSLContext jooq;

    public InstrumentDaoImpl(DSLContext jooq) {
        super(jooq.configuration());
        this.jooq = jooq;
    }

    public Instrument getActiveByIdd(Integer idd) {
        return jooq.select(INSTRUMENT.fields())
                .from(INSTRUMENT)
                .where(INSTRUMENT.IDD.eq(idd).and(INSTRUMENT.DELETE_DATE.isNull()))
                .fetchOneInto(Instrument.class);
    }

    public List<Instrument> getInstrumentsByRoomIdd(Integer idd) {
        return jooq.select(INSTRUMENT.fields())
                    .from(INSTRUMENT)
                        .join(INSTRUMENT_TO_ROOM)
                            .on(INSTRUMENT.IDD.eq(INSTRUMENT_TO_ROOM.INSTRUMENT_IDD))
                    .where(INSTRUMENT_TO_ROOM.ROOM_IDD.eq(idd))
                    .fetchInto(Instrument.class);

    }
    
    public List<Instrument> getHistory(Integer idd) {
        return jooq.selectFrom(INSTRUMENT)
                .where(INSTRUMENT.IDD.eq(idd))
                .fetchInto(Instrument.class);
    }

    public void create(Instrument instrument) {
        instrument.setId(jooq.nextval(Sequences.INSTRUMENT_ID_SEQ));
        if (instrument.getIdd() == null) {
            instrument.setIdd(instrument.getId());
        }
        instrument.setCreateDate(LocalDateTime.now());
        super.insert(instrument);
    }


}
