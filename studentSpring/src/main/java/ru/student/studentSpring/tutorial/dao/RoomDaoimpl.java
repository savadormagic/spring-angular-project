package ru.student.studentSpring.tutorial.dao;

import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.student.studentSpring.tutorial.generated.tables.daos.RoomsDao;
import ru.student.studentSpring.tutorial.generated.tables.pojos.Rooms;

import static ru.student.studentSpring.tutorial.generated.tables.Rooms.ROOMS;


@Repository
@AllArgsConstructor
public class RoomDaoimpl extends RoomsDao {
    private final DSLContext jooq;

//    public RoomDaoimpl(DSLContext jooq) {
//        this.jooq = jooq;
//    }

    public Rooms getActiveByIdd(Integer idd) {
        return jooq.select(ROOMS.fields())
                .from(ROOMS)
                .where(ROOMS.IDD.eq(idd).and(ROOMS.DELETE_DATE.isNull()))
                .fetchOneInto(Rooms.class);
    }
}
