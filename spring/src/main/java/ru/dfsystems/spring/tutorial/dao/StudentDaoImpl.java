package ru.dfsystems.spring.tutorial.dao;

import lombok.AllArgsConstructor;
import lombok.val;
import lombok.var;
import org.jooq.DSLContext;
import org.jooq.SelectSeekStepN;
import org.jooq.SortField;
import org.springframework.stereotype.Repository;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.dto.room.RoomParams;
import ru.dfsystems.spring.tutorial.dto.student.StudentParams;
import ru.dfsystems.spring.tutorial.generated.tables.daos.StudentDao;
import ru.dfsystems.spring.tutorial.generated.tables.Student;
import ru.dfsystems.spring.tutorial.generated.tables.records.StudentRecord;

import java.util.ArrayList;
import java.util.List;

import static ru.dfsystems.spring.tutorial.generated.tables.Room.ROOM;

@Repository
@AllArgsConstructor
public class StudentDaoImpl extends StudentDao {
    private final DSLContext jooq;

    public Student getActiveByIdd(Integer idd) {
        return jooq.select(Student.STUDENT.fields())
                .from(Student.STUDENT)
                .where(Student.STUDENT.IDD.eq(idd).and(Student.STUDENT.DELETE_DATE.isNull()))
                .fetchOneInto(Student.class);
    }

    public Page<Student> getRoomsByParams(PageParams<StudentParams> pageParams) {
        final StudentParams params = pageParams.getParams() == null ? new StudentParams() : pageParams.getParams();
        val listQuery = getRoomSelect(params);

        val count = jooq.selectCount()
                .from(listQuery)
                .fetchOne(0, Long.class);

        List<Student> list = listQuery.offset(pageParams.getStart())
                .limit(pageParams.getPage())
                .fetchInto(Student.class);

        return new Page<>(list, count);
    }

    private SelectSeekStepN<StudentRecord> getStudentSelect(StudentParams params){
        params.
        var condition = Student.STUDENT.DELETE_DATE.isNull();
        if (!params.isEmpty()){
            condition = condition.and(ROOM.BLOCK.like(params.getBlock()));
        }
        if (!params.getNumber().isEmpty()){
            condition = condition.and(Student.STUDENT.NUMBER.like(params.getNumber()));
        }
        if (params.getCreateDateStart() != null && params.getCreateDateEnd() != null){
            condition = condition.and(Student.STUDENT.CREATE_DATE.between(params.getCreateDateStart(), params.getCreateDateEnd()));
        }

        val sort = getOrderBy(params.getOrderBy(), params.getOrderDir());

        return jooq.selectFrom(ROOM)
                .where(condition)
                .orderBy(sort);
    }

    private SortField[] getOrderBy(String orderBy, String orderDir){
        val asc = orderDir != null && orderDir.equalsIgnoreCase("asc");

        if (orderBy == null){
            return asc
                    ? new SortField[]{ROOM.IDD.asc()}
                    : new SortField[]{ROOM.IDD.desc()};
        }

        val orderArray = orderBy.split(",");

        List<SortField> listSortBy = new ArrayList<>();
        for (val order: orderArray){
            if (order.equalsIgnoreCase("idd")){
                listSortBy.add(asc ? ROOM.IDD.asc() : ROOM.IDD.desc());
            }
            if (order.equalsIgnoreCase("block")){
                listSortBy.add(asc ? ROOM.BLOCK.asc() : ROOM.BLOCK.desc());
            }
            if (order.equalsIgnoreCase("number")){
                listSortBy.add(asc ? ROOM.NUMBER.asc() : ROOM.NUMBER.desc());
            }
            if (order.equalsIgnoreCase("createDate")){
                listSortBy.add(asc ? ROOM.CREATE_DATE.asc() : ROOM.CREATE_DATE.desc());
            }
        }

        return listSortBy.toArray(new SortField[0]);
    }
}
