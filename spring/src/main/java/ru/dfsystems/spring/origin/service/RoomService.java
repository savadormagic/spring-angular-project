package ru.dfsystems.spring.origin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dfsystems.spring.origin.dao.RoomDaoImpl;
import ru.dfsystems.spring.origin.dto.Page;
import ru.dfsystems.spring.origin.dto.PageParams;
import ru.dfsystems.spring.origin.dto.room.RoomParams;
import ru.dfsystems.spring.origin.generated.tables.pojos.Room;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomService {
    private RoomDaoImpl roomDao;

    public List<Room> getAllRooms() {
        roomDao.fetchOneById(152);
        roomDao.getActiveByIdd(152);
        return roomDao.findAll();
    }

    public Page<Room> getRoomsByParams(PageParams<RoomParams> pageParams) {
        return roomDao.getRoomsByParams(pageParams);
    }
}
