package ru.dfsystems.spring.tutorial.dao;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.dfsystems.spring.tutorial.generated.tables.Course;
import ru.dfsystems.spring.tutorial.generated.tables.daos.CourseDao;

import static ru.dfsystems.spring.tutorial.generated.tables.Course.COURSE;

import static ru.dfsystems.spring.tutorial.generated.tables.StudentToCourse.STUDENT_TO_COURSE;

import java.util.List;

@Repository
public class CourseDaoImpl extends CourseDao {
    final DSLContext jooq;

    public CourseDaoImpl(DSLContext jooq) {
        super(jooq.configuration());
        this.jooq = jooq;
    }

    public List<Course> getCourseByStudentIdd(Integer idd) {
        return jooq.select(COURSE.fields())
                .from(COURSE)
                .join(STUDENT_TO_COURSE)
                .on(COURSE.IDD.eq(STUDENT_TO_COURSE.COURSE_IDD))
                .where(STUDENT_TO_COURSE.STUDENT_IDD.eq(idd))
                .fetchInto(Course.class);
    }

}
