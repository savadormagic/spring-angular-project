package ru.dfsystems.spring.origin.service;

import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.dfsystems.spring.origin.dao.RoomDaoImpl;
import ru.dfsystems.spring.origin.dto.Page;
import ru.dfsystems.spring.origin.dto.PageParams;
import ru.dfsystems.spring.origin.dto.room.RoomDto;
import ru.dfsystems.spring.origin.dto.room.RoomHistoryDto;
import ru.dfsystems.spring.origin.dto.room.RoomListDto;
import ru.dfsystems.spring.origin.dto.room.RoomParams;
import ru.dfsystems.spring.origin.generated.Sequences;
import ru.dfsystems.spring.origin.generated.tables.daos.InstrumentToRoomDao;
import ru.dfsystems.spring.origin.generated.tables.daos.RoomDao;
import ru.dfsystems.spring.origin.generated.tables.pojos.InstrumentToRoom;
import ru.dfsystems.spring.origin.generated.tables.pojos.Room;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomService {
    private RoomDaoImpl roomDao;
    private RoomDao dao;
    private InstrumentToRoomDao instrumentToRoomDao;
    private final DSLContext jooq;

    public List<Room> getAllRooms() {
        roomDao.getActiveByIdd(152);
        return dao.findAll();
    }

    public Page<RoomListDto> getRoomsByParams(PageParams<RoomParams> pageParams) {
        Page <Room> page = roomDao.getRoomsByParams(pageParams);
        List<RoomListDto> list = mapper(page.getList());
        return new Page<>(list, page.getTotalCount());
    }

    public void create(RoomDto roomDto) {
        Room room = mapper(roomDto);
        room.setIdd(jooq.nextval(Sequences.ROOM_ID_SEQ));
        room.setCreateDate(LocalDateTime.now());
        room.setIdd(room.getIdd());
        dao.insert(room);
    }

    public RoomDto get(Integer idd) {
        return mapper(roomDao.getActiveByIdd(idd));
    }

    public List<RoomHistoryDto> getHistory(Integer idd) {
        return mapperHistory(roomDao.getHistory(idd));
    }

    public void delete(Integer idd) {
        Room room = roomDao.getActiveByIdd(idd);
        room.setDeleteDate(LocalDateTime.now());
        dao.update(room);
    }

    public void putInstrument(Integer idd, Integer instrumentIdd) {
        InstrumentToRoom link = new InstrumentToRoom();
        link.setRoomIdd(idd);
        link.setInstrumentIdd(instrumentIdd);
        instrumentToRoomDao.insert(link);
    }

    private List<RoomHistoryDto> mapperHistory(List<Room> allRooms) {
        ModelMapper mapper = new ModelMapper();
        return allRooms.stream()
                .map(r -> mapper.map(r, RoomHistoryDto.class))
                .collect(Collectors.toList());
    }

    private List<RoomListDto> mapper(List<Room> allRooms) {
        ModelMapper mapper = new ModelMapper();
        return allRooms.stream()
                .map(r -> mapper.map(r, RoomListDto.class))
                .collect(Collectors.toList());
    }
    private Room mapper(RoomDto dto){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Room.class);
    }
    private RoomDto mapper(Room entity){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, RoomDto.class);
    }
}
