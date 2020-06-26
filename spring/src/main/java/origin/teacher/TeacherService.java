package origin.teacher;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Teacher;


@Service
@AllArgsConstructor

public class TeacherService {
    private TeacherDaoImpl teacherDao;

    public Page<Teacher> getTeacherList(PageParams<TeacherParams> pageParams) {
        return teacherDao.getTeacherList(pageParams);
    }
}
