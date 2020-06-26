package origin.course;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import origin.tools.Transformer;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Course;

import java.util.List;

@RestController
@RequestMapping(value = "/course", produces = "application/json; charset=UTF-8")
@AllArgsConstructor
public class CourseController {
    private CourseService courseService;

    @PostMapping("/list")
    public Page<BaseListDto> getList(PageParams<CourseParams> pageParams) {
        Page<Course> courseList = courseService.getCourseList(pageParams);
        List<BaseListDto> resultList = Transformer.pojoToOrigin(courseList.getList(), BaseListDto.class);
        return new Page<>(resultList, courseList.getTotalCount());
    }

}
