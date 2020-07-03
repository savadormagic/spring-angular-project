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
import ru.dfsystems.spring.origin.dto.room.RoomParams;
import ru.dfsystems.spring.origin.generated.tables.pojos.Room;
import ru.dfsystems.spring.origin.generated.tables.records.RoomRecord;

import java.util.ArrayList;
import java.util.List;

import static ru.dfsystems.spring.origin.generated.tables.Room.ROOM;

@Repository
@AllArgsConstructor
public class RoomDaoImpl {
    private final DSLContext jooq;

    public Room getActiveByIdd(Integer idd) {
        return jooq.select(ROOM.fields())
                .from(ROOM)
                .where(ROOM.IDD.eq(idd).and(ROOM.DELETE_DATE.isNull()))
                .fetchOneInto(Room.class);
    }

    public Page<Room> getRoomsByParams(PageParams<RoomParams> pageParams) {
        final RoomParams params = pageParams.getParams() == null ? new RoomParams() : pageParams.getParams();
        val listQuery = getRoomSelect(params);

        val count = jooq.selectCount()
                .from(listQuery)
                .fetchOne(0, Long.class);

        List<Room> list = listQuery.offset(pageParams.getStart())
                .limit(pageParams.getPage())
                .fetchInto(Room.class);

        return new Page<>(list, count);
    }

    private SelectSeekStepN<RoomRecord> getRoomSelect(RoomParams params){
        var condition = ROOM.DELETE_DATE.isNull();
        if (params.getBlock() != null){
            condition = condition.and(ROOM.BLOCK.like(params.getBlock()));
        }
        if (params.getNumber() != null){
            condition = condition.and(ROOM.NUMBER.like(params.getNumber()));
        }
        if (params.getCreateDateStart() != null && params.getCreateDateEnd() != null){
            condition = condition.and(ROOM.CREATE_DATE.between(params.getCreateDateStart(), params.getCreateDateEnd()));
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

    public List<Room> getHistory(Integer idd) {
        return jooq.selectFrom(ROOM)
                .where(ROOM.IDD.eq(idd))
                .fetchInto(Room.class);
    }
}