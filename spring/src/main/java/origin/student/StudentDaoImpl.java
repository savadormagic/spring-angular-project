package origin.student;

import lombok.AllArgsConstructor;
import lombok.val;
import lombok.var;
import org.jooq.*;
import org.springframework.stereotype.Repository;
import origin.tools.Checker;
import origin.tools.SQLer;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.generated.tables.daos.StudentDao;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Student;
import ru.dfsystems.spring.tutorial.generated.tables.records.StudentRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.dfsystems.spring.tutorial.generated.tables.Student.STUDENT;

@Repository
@AllArgsConstructor
public class StudentDaoImpl extends StudentDao {
    private final DSLContext jooq;

    public Page<Student> getStudentList(PageParams<StudentParams> pageParams) {
        val listQuery = getStudentSelect(pageParams);

        val count = jooq.selectCount()
                .from(listQuery)
                .fetchOne(0, Long.class);

        List<Student> list = listQuery.offset(pageParams.getStart())
                .limit(pageParams.getPage())
                .fetchInto(Student.class);

        return new Page<>(list, count);
    }

    private SelectSeekStepN<StudentRecord> getStudentSelect(PageParams<StudentParams> pageParams) {
        final StudentParams params = pageParams.getParams() == null ? new StudentParams() : pageParams.getParams();
        var condition = STUDENT.DELETE_DATE.isNull();
        String firstName = params.getFirstName();
        String lastName = params.getLastName();
        String middleName = params.getMiddleName();
        if (Checker.checkEmpty(firstName)) {
            condition = condition.and(STUDENT.FIRST_NAME.like(firstName));
        }
        if (Checker.checkEmpty(lastName)) {
            condition = condition.and(STUDENT.LAST_NAME.like(lastName));
        }
        if (Checker.checkEmpty(middleName)) {
            condition = condition.and(STUDENT.MIDDLE_NAME.like(middleName));
        }

        Map<String, TableField<? extends TableRecord, ?>> orderMap = new HashMap<>();
        orderMap.put("idd", STUDENT.IDD);
        orderMap.put("firstName", STUDENT.FIRST_NAME);
        orderMap.put("lastName", STUDENT.LAST_NAME);
        orderMap.put("middleName", STUDENT.MIDDLE_NAME);
        val sort = SQLer.getSortField(orderMap, STUDENT.IDD, pageParams.getOrderBy(), pageParams.getOrderDir());

        return jooq.selectFrom(STUDENT)
                .where(condition)
                .orderBy(sort);
    }

}
