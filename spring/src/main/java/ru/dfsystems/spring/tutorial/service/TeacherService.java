package ru.dfsystems.spring.tutorial.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dfsystems.spring.tutorial.dao.TeacherDaoImpl;
import ru.dfsystems.spring.tutorial.dto.teacher.TeacherParams;
import ru.dfsystems.spring.tutorial.dto.teacher.TeacherDto;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Teacher;
import ru.dfsystems.spring.tutorial.mapping.MappingService;

import java.time.LocalDateTime;


@Service
@AllArgsConstructor

public class TeacherService {
    private TeacherDaoImpl teacherDao;
    private MappingService mappingService;

    public Page<BaseListDto> getTeacherList(PageParams<TeacherParams> pageParams) {
        Page<Teacher> page = teacherDao.getTeacherList(pageParams);
        return new Page<>(mappingService.mapList(page.getList(), BaseListDto.class), page.getTotalCount());
    }

    @Transactional
    public void create(TeacherParams teacherParams) {
        teacherDao.create(mappingService.map(teacherParams, Teacher.class));
    }

    public TeacherDto get(Integer idd) {
        return mappingService.map(teacherDao.getActiveByIdd(idd), TeacherDto.class);
    }

    @Transactional
    public TeacherDto update(Integer idd, TeacherParams teacherParams) {
        Teacher teacher = teacherDao.getActiveByIdd(idd);
        if (teacher == null){
            throw new RuntimeException("");
        }
        teacher.setDeleteDate(LocalDateTime.now());
        teacherDao.update(teacher);

        Teacher newTeacher = mappingService.map(teacherParams, Teacher.class);
        newTeacher.setIdd(teacher.getIdd());
        teacherDao.create(newTeacher);
        return mappingService.map(newTeacher, TeacherDto.class);
    }

    @Transactional
    public void delete(Integer idd) {
        Teacher teacher = teacherDao.getActiveByIdd(idd);
        teacher.setDeleteDate(LocalDateTime.now());
        teacherDao.update(teacher);
    }
}
