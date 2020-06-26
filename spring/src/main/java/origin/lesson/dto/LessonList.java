package origin.lesson.dto;

import lombok.Getter;
import lombok.Setter;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;


@Getter
@Setter
public class LessonList extends BaseListDto {
    private String name;
}
