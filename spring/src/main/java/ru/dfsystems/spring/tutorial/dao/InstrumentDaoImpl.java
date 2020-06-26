package ru.dfsystems.spring.tutorial.dao;

import lombok.AllArgsConstructor;
import lombok.val;
import lombok.var;
import org.jooq.*;
import org.springframework.stereotype.Repository;
import origin.tools.SQLer;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.dto.instrument.InstrumentParams;
import ru.dfsystems.spring.tutorial.generated.tables.daos.InstrumentDao;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Instrument;
import ru.dfsystems.spring.tutorial.generated.tables.records.InstrumentRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.dfsystems.spring.tutorial.generated.tables.Instrument.INSTRUMENT;
import static ru.dfsystems.spring.tutorial.generated.tables.Teacher.TEACHER;

@Repository
@AllArgsConstructor
public class InstrumentDaoImpl extends InstrumentDao {
    private final DSLContext jooq;

    public Page<Instrument> getInstrumentsByParams(PageParams<InstrumentParams> pageParams) {
        val listQuery = getInstrumentSelect(pageParams);

        val count = jooq.selectCount()
                .from(listQuery)
                .fetchOne(0, Long.class);

        List<Instrument> list = listQuery.offset(pageParams.getStart())
                .limit(pageParams.getPage())
                .fetchInto(Instrument.class);

        return new Page<>(list, count);
    }

    private SelectSeekStepN<InstrumentRecord> getInstrumentSelect(PageParams<InstrumentParams> pageParams){
        final InstrumentParams params = pageParams.getParams() == null ? new InstrumentParams() : pageParams.getParams();
        var condition = INSTRUMENT.DELETE_DATE.isNull();
        if (!params.getNumber().isEmpty()){
            condition = condition.and(INSTRUMENT.NUMBER.like(params.getNumber()));
        }
        if (params.getCreateDateStart() != null && params.getCreateDateEnd() != null){
            condition = condition.and(INSTRUMENT.CREATE_DATE.between(params.getCreateDateStart(), params.getCreateDateEnd()));
        }

        Map<String, TableField<? extends TableRecord, ?>> orderMap = new HashMap<>();
        orderMap.put("idd", INSTRUMENT.IDD);
        orderMap.put("number", INSTRUMENT.NUMBER);
        orderMap.put("createDate", INSTRUMENT.CREATE_DATE);
        val sort = SQLer.getSortField(orderMap, INSTRUMENT.IDD, pageParams.getOrderBy(), pageParams.getOrderDir());

        return jooq.selectFrom(INSTRUMENT)
                .where(condition)
                .orderBy(sort);
    }

}
