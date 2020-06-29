--liquibase formatted sql
--changeset samoylov:20200621-01
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0 select count(*) from (SELECT to_regclass('public.course') as res) as r where  res IS NOT NULL

create  table  course  (
    id          serial      not null
            primary key,
    idd         integer      not null,
    name  varchar(100)  not null,
    description   varchar(100)  not null,
    teacher_idd integer not null,
    max_count_student integer not null,
    start_date  timestamp(0) not null,
    end_date timestamp(0) not null,
    create_date timestamp(0) not null,
    delete_date timestamp(0),
    status      varchar(10)
);
--rollback drop table course;
--comment: Создание таблицы course