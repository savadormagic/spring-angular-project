package origin.teacher;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import origin.tools.Transformer;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Teacher;

import java.util.List;

@RestController
@RequestMapping(value = "/teacher", produces = "application/json; charset=UTF-8")
@AllArgsConstructor
public class TeacherController {
    private TeacherService teacherService;

    @PostMapping("/list")
    public Page<BaseListDto> getList(PageParams<TeacherParams> pageParams) {
        Page<Teacher> teacherList = teacherService.getTeacherList(pageParams);
        List<BaseListDto> resultList = Transformer.pojoToOrigin(teacherList.getList(), BaseListDto.class);
        return new Page<>(resultList, teacherList.getTotalCount());
    }

}
