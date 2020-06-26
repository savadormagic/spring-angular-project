package origin.course;

import lombok.AllArgsConstructor;
import lombok.val;
import lombok.var;
import org.jooq.DSLContext;
import org.jooq.SelectSeekStepN;
import org.jooq.TableField;
import org.jooq.TableRecord;
import org.springframework.stereotype.Repository;
import origin.tools.Checker;
import origin.tools.SQLer;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.generated.tables.daos.CourseDao;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Course;
import ru.dfsystems.spring.tutorial.generated.tables.records.CourseRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.dfsystems.spring.tutorial.generated.tables.Course.COURSE;

@Repository
@AllArgsConstructor
public class CourseDaoImpl extends CourseDao {
    private final DSLContext jooq;

    public Page<Course> getCourseList(PageParams<CourseParams> pageParams) {
        val listQuery = getCourseSelect(pageParams);

        val count = jooq.selectCount()
                .from(listQuery)
                .fetchOne(0, Long.class);

        List<Course> list = listQuery.offset(pageParams.getStart())
                .limit(pageParams.getPage())
                .fetchInto(Course.class);

        return new Page<>(list, count);
    }

    private SelectSeekStepN<CourseRecord> getCourseSelect(PageParams<CourseParams> pageParams) {
        final CourseParams params = pageParams.getParams() == null ? new CourseParams() : pageParams.getParams();
        var condition = COURSE.DELETE_DATE.isNull();
        String name = params.getName();
        String status = params.getStatus();
        if (Checker.checkEmpty(name)) {
            condition = condition.and(COURSE.NAME.like(name));
        }
        if (Checker.checkEmpty(status)) {
            condition = condition.and(COURSE.STATUS.like(status));
        }

        Map<String, TableField<? extends TableRecord, ?>> orderMap = new HashMap<>();
        orderMap.put("idd", COURSE.IDD);
        orderMap.put("name", COURSE.NAME);
        orderMap.put("status", COURSE.STATUS);
        val sort = SQLer.getSortField(orderMap, COURSE.IDD, pageParams.getOrderBy(), pageParams.getOrderDir());

        return jooq.selectFrom(COURSE)
                .where(condition)
                .orderBy(sort);
    }

}
