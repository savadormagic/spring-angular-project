package origin.course;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Course;


@Service
@AllArgsConstructor

public class CourseService {
    private CourseDaoImpl courseDao;

    public Page<Course> getCourseList(PageParams<CourseParams> pageParams) {
        return courseDao.getCourseList(pageParams);
    }
}
