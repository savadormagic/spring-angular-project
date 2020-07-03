package ru.dfsystems.spring.origin.dao;

import lombok.AllArgsConstructor;
import lombok.val;
import lombok.var;
import org.jooq.DSLContext;
import org.jooq.SelectSeekStepN;
import org.jooq.SortField;
import org.springframework.stereotype.Repository;
import ru.dfsystems.spring.origin.dto.Page;
import ru.dfsystems.spring.origin.dto.PageParams;
import ru.dfsystems.spring.origin.dto.user.UserParams;
import ru.dfsystems.spring.origin.generated.tables.daos.UsersDao;
import ru.dfsystems.spring.origin.generated.tables.pojos.Room;
import ru.dfsystems.spring.origin.generated.tables.pojos.Users;
import ru.dfsystems.spring.origin.generated.tables.records.UsersRecord;

import java.util.ArrayList;
import java.util.List;

import static ru.dfsystems.spring.origin.generated.tables.Room.ROOM;
import static ru.dfsystems.spring.origin.generated.tables.Users.USERS;

@Repository
@AllArgsConstructor
public class UserDaoImpl extends UsersDao {
    private final DSLContext jooq;

    public Users getActiveByIdd(Integer idd) {
        return jooq.select(USERS.fields())
                .from(USERS)
                .where(USERS.IDD.eq(idd).and(USERS.DELETE_DATE.isNull()))
                .fetchOneInto(Users.class);
    }

    public Page<Users> getUsersByParams(PageParams<UserParams> pageParams) {
        final UserParams params = pageParams.getParams() == null ? new UserParams() : pageParams.getParams();
        val listQuery = getUserSelect(params);

        val count = jooq.selectCount()
                .from(listQuery)
                .fetchOne(0, Long.class);

        List<Users> list = listQuery.offset(pageParams.getStart())
                .limit(pageParams.getPage())
                .fetchInto(Users.class);

        return new Page<>(list, count);
    }

    private SelectSeekStepN<UsersRecord> getUserSelect(UserParams params) {
        var condition = USERS.DELETE_DATE.isNull();
        if (!params.getFirstName().isEmpty()) {
            condition = condition.and(USERS.FIRST_NAME.like(params.getFirstName()));
        }
        if (!params.getLastName().isEmpty()) {
            condition = condition.and(USERS.LAST_NAME.like(params.getLastName()));
        }
        if (!params.getMiddleName().isEmpty()) {
            condition = condition.and(USERS.MIDDLE_NAME.like(params.getLastName()));
        }
        if (params.getCreateDateStart() != null && params.getDeleteDateStart() != null) {
            condition = condition.and(USERS.CREATE_DATE.between(params.getCreateDateStart(), params.getDeleteDateStart()));
        }

        val sort = getOrderBy(params.getOrderBy(), params.getOrderDir());

        return jooq.selectFrom(USERS)
                .where(condition)
                .orderBy(sort);
    }

    private SortField[] getOrderBy(String orderBy, String orderDir) {
        val asc = orderDir != null && orderDir.equalsIgnoreCase("asc");

        if (orderBy == null) {
            return asc
                    ? new SortField[]{USERS.IDD.asc()}
                    : new SortField[]{USERS.IDD.desc()};
        }

        val orderArray = orderBy.split(",");

        List<SortField> listSortBy = new ArrayList<>();
        for (val order : orderArray) {
            if (order.equalsIgnoreCase("idd")) {
                listSortBy.add(asc ? USERS.IDD.asc() : USERS.IDD.desc());
            }
            if (order.equalsIgnoreCase("firstName")) {
                listSortBy.add(asc ? USERS.FIRST_NAME.asc() : USERS.FIRST_NAME.desc());
            }
            if (order.equalsIgnoreCase("middleName")) {
                listSortBy.add(asc ? USERS.MIDDLE_NAME.asc() : USERS.MIDDLE_NAME.desc());
            }
            if (order.equalsIgnoreCase("lastName")) {
                listSortBy.add(asc ? USERS.LAST_NAME.asc() : USERS.LAST_NAME.desc());
            }
            if (order.equalsIgnoreCase("createDate")) {
                listSortBy.add(asc ? USERS.CREATE_DATE.asc() : USERS.CREATE_DATE.desc());
            }
        }

        return listSortBy.toArray(new SortField[0]);
    }

}