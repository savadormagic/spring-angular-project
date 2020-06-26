package origin.course.dto;

import lombok.Getter;
import lombok.Setter;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;


@Getter
@Setter
public class CourseList  extends BaseListDto {
    private String firstName;
    private String lastName;
    private String middleName;
    private String passport;
}
