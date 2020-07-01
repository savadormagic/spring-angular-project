create table INSTRUMENT_TO_ROOM(
    id serial primary key,
    instrument_idd int references INSTRUMENT (idd),
    room_idd int references  ROOM (idd)
)