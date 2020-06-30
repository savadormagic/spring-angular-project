package ru.dfsystems.spring.tutorial.dao;

import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.dfsystems.spring.tutorial.generated.tables.daos.CourseDao;

@Repository
@AllArgsConstructor
public class CourseDaoImpl extends CourseDao {
    final DSLContext jooq;

}
