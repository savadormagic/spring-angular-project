package origin.lesson;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import origin.tools.Transformer;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Lesson;

import java.util.List;

@RestController
@RequestMapping(value = "/lesson", produces = "application/json; charset=UTF-8")
@AllArgsConstructor
public class LessonController {
    private LessonService lessonService;

    @PostMapping("/list")
    public Page<BaseListDto> getList(PageParams<LessonParams> pageParams) {
        Page<Lesson> lessonList = lessonService.getLessonList(pageParams);
        List<BaseListDto> resultList = Transformer.pojoToOrigin(lessonList.getList(), BaseListDto.class);
        return new Page<>(resultList, lessonList.getTotalCount());
    }

}
