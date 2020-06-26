package origin.student;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import origin.tools.Transformer;
import ru.dfsystems.spring.tutorial.dto.BaseListDto;
import ru.dfsystems.spring.tutorial.dto.Page;
import ru.dfsystems.spring.tutorial.dto.PageParams;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Student;

import java.util.List;

@RestController
@RequestMapping(value = "/student", produces = "application/json; charset=UTF-8")
@AllArgsConstructor
public class StudentController {
    private StudentService studentService;

    @PostMapping("/list")
    public Page<BaseListDto> getList(PageParams<StudentParams> pageParams) {
        Page<Student> studentList = studentService.getStudentList(pageParams);
        List<BaseListDto> resultList = Transformer.pojoToOrigin(studentList.getList(), BaseListDto.class);
        return new Page<>(resultList, studentList.getTotalCount());
    }

}
