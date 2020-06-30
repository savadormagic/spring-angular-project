package ru.dfsystems.spring.origin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dfsystems.spring.origin.dao.TeacherDaoImpl;
import ru.dfsystems.spring.origin.dto.Page;
import ru.dfsystems.spring.origin.dto.PageParams;
import ru.dfsystems.spring.origin.dto.teacher.TeacherParams;
import ru.dfsystems.spring.origin.generated.tables.pojos.Teacher;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherService {

    private TeacherDaoImpl teacherDao;

    public List<Teacher> getAllTeachers() {
        return teacherDao.findAll();
    }

    public Page<Teacher> getTeachersByParams(PageParams<TeacherParams> pageParams) {
        return teacherDao.getTeachersByParams(pageParams);
    }
}