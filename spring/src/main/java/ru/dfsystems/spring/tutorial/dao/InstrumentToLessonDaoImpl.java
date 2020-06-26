package ru.dfsystems.spring.tutorial.dao;

import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.dfsystems.spring.tutorial.generated.tables.daos.InstrumentToLessonDao;

@Repository
@AllArgsConstructor
public class InstrumentToLessonDaoImpl extends InstrumentToLessonDao {
    private final DSLContext jooq;
}
