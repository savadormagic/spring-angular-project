package ru.dfsystems.spring.tutorial.dao;

import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dfsystems.spring.tutorial.generated.tables.daos.InstrumentDao;

@Repository
@AllArgsConstructor
public class InstrumentDaoImpl extends InstrumentDao {
    private final DSLContext jooq;
}
