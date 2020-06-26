package origin.lesson;

import lombok.AllArgsConstructor;
import lombok.val;
import org.jooq.*;
import org.springframework.stereotype.Repository;
import origin.tools.Checker;
import origin.tools.SQLer;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.generated.tables.daos.LessonDao;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Lesson;
import ru.dfsystems.spring.tutorial.generated.tables.records.LessonRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.dfsystems.spring.tutorial.generated.tables.Lesson.LESSON;

@Repository
@AllArgsConstructor
public class LessonDaoImpl extends LessonDao {
    private final DSLContext jooq;

    public Page<Lesson> getLessonList(PageParams<LessonParams> pageParams) {
        val listQuery = getLessonSelect(pageParams);

        val count = jooq.selectCount()
                .from(listQuery)
                .fetchOne(0, Long.class);

        List<Lesson> list = listQuery.offset(pageParams.getStart())
                .limit(pageParams.getPage())
                .fetchInto(Lesson.class);

        return new Page<>(list, count);
    }

    private SelectSeekStepN<LessonRecord> getLessonSelect(PageParams<LessonParams> pageParams) {
        final LessonParams params = pageParams.getParams() == null ? new LessonParams() : pageParams.getParams();
        Condition condition = null;
        String name = params.getName();
        if (Checker.checkEmpty(name)) {
            condition = LESSON.NAME.like(name);
        }

        Map<String, TableField<? extends TableRecord, ?>> orderMap = new HashMap<>();
        orderMap.put("idd", LESSON.ID);
        orderMap.put("name", LESSON.NAME);
        val sort = SQLer.getSortField(orderMap, LESSON.ID, pageParams.getOrderBy(), pageParams.getOrderDir());

        return jooq.selectFrom(LESSON)
                .where(condition)
                .orderBy(sort);
    }

}
