create table USERS (
    id serial primary key,
    idd int,
    login varchar(50),
    password varchar(50),
    student_idd int REFERENCES STUDENT (IDD),
    teacher_idd int REFERENCES TEACHER (IDD)
)