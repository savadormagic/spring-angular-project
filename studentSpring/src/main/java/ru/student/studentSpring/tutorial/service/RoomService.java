package ru.student.studentSpring.tutorial.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.student.studentSpring.tutorial.dao.RoomDaoimpl;
import ru.student.studentSpring.tutorial.generated.tables.daos.RoomsDao;
import ru.student.studentSpring.tutorial.generated.tables.pojos.Rooms;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomService {
    private RoomDaoimpl roomsDao;

    public List<Rooms> getAllRooms() {
        roomsDao.fetchOneById(12);
        roomsDao.getActiveByIdd(12);
        return roomsDao.findAll();
    }
}
