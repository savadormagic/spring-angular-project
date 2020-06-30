package ru.dfsystems.spring.tutorial.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dfsystems.spring.tutorial.dao.ListDao.TeacherListDao;
import ru.dfsystems.spring.tutorial.dao.TeacherDaoImpl;
import ru.dfsystems.spring.tutorial.dao.TeacherDaoImpl;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.dto.teacher.TeacherDto;
import ru.dfsystems.spring.tutorial.dto.teacher.TeacherListDto;
import ru.dfsystems.spring.tutorial.dto.teacher.TeacherParams;
import ru.dfsystems.spring.tutorial.dto.teacher.TeacherParams;

import ru.dfsystems.spring.tutorial.generated.tables.pojos.Teacher;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Teacher;
import ru.dfsystems.spring.tutorial.mapping.MappingService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TeacherService {
    private TeacherDaoImpl teacherDao;
    private TeacherListDao teacherListDao;
    private MappingService mappingService;

    public Page<TeacherListDto> getTeachersByParams(PageParams<TeacherParams> pageParams) {
        Page<Teacher> page = teacherListDao.list(pageParams);
        List<TeacherListDto> list = mappingService.mapList(page.getList(), TeacherListDto.class);
        return new Page<>(list, page.getTotalCount());
    }

    @Transactional
    public void create(TeacherDto teacherDto) {
        teacherDao.create(mappingService.map(teacherDto, Teacher.class));
    }

    public TeacherDto get(Integer idd) {
        return mappingService.map(teacherDao.getActiveByIdd(idd), TeacherDto.class);
    }

    @Transactional
    public void delete(Integer idd) {
        Teacher teacher = teacherDao.getActiveByIdd(idd);
        teacher.setDeleteDate(LocalDateTime.now());
        teacherDao.update(teacher);
    }

    @Transactional
    public TeacherDto update(Integer idd, TeacherDto teacherDto) {
        Teacher teacher = teacherDao.getActiveByIdd(idd);
        if (teacher == null){
            throw new RuntimeException("");
        }
        teacher.setDeleteDate(LocalDateTime.now());
        teacherDao.update(teacher);

        Teacher newTeacher = mappingService.map(teacherDto, Teacher.class);
        newTeacher.setIdd(teacher.getIdd());
        teacherDao.create(newTeacher);
        return mappingService.map(newTeacher, TeacherDto.class);
    }}
