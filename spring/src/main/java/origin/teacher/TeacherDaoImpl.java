package origin.teacher;

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
import ru.dfsystems.spring.tutorial.generated.tables.daos.TeacherDao;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Teacher;
import ru.dfsystems.spring.tutorial.generated.tables.records.TeacherRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.dfsystems.spring.tutorial.generated.tables.Teacher.TEACHER;

@Repository
@AllArgsConstructor
public class TeacherDaoImpl extends TeacherDao {
    private final DSLContext jooq;

    public Page<Teacher> getTeacherList(PageParams<TeacherParams> pageParams) {
        val listQuery = getTeacherSelect(pageParams);

        val count = jooq.selectCount()
                .from(listQuery)
                .fetchOne(0, Long.class);

        List<Teacher> list = listQuery.offset(pageParams.getStart())
                .limit(pageParams.getPage())
                .fetchInto(Teacher.class);

        return new Page<>(list, count);
    }

    private SelectSeekStepN<TeacherRecord> getTeacherSelect(PageParams<TeacherParams> pageParams) {
        final TeacherParams params = pageParams.getParams() == null ? new TeacherParams() : pageParams.getParams();
        var condition = TEACHER.DELETE_DATE.isNull();
        String firstName = params.getFirstName();
        String lastName = params.getLastName();
        String middleName = params.getMiddleName();
        if (Checker.checkEmpty(firstName)) {
            condition = condition.and(TEACHER.FIRST_NAME.like(firstName));
        }
        if (Checker.checkEmpty(lastName)) {
            condition = condition.and(TEACHER.LAST_NAME.like(lastName));
        }
        if (Checker.checkEmpty(middleName)) {
            condition = condition.and(TEACHER.MIDDLE_NAME.like(middleName));
        }

        Map<String, TableField<? extends TableRecord, ?>> orderMap = new HashMap<>();
        orderMap.put("idd", TEACHER.IDD);
        orderMap.put("firstName", TEACHER.FIRST_NAME);
        orderMap.put("lastName", TEACHER.LAST_NAME);
        orderMap.put("middleName", TEACHER.MIDDLE_NAME);
        val sort = SQLer.getSortField(orderMap, TEACHER.IDD, pageParams.getOrderBy(), pageParams.getOrderDir());

        return jooq.selectFrom(TEACHER)
                .where(condition)
                .orderBy(sort);
    }

}
