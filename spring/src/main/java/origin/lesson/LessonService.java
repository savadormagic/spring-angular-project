package origin.lesson;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Lesson;


@Service
@AllArgsConstructor

public class LessonService {
    private LessonDaoImpl lessonDao;

    public Page<Lesson> getLessonList(PageParams<LessonParams> pageParams) {
        return lessonDao.getLessonList(pageParams);
    }
}
