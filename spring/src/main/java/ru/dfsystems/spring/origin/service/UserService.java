package ru.dfsystems.spring.origin.service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dfsystems.spring.origin.dao.UserDaoImpl;
import ru.dfsystems.spring.origin.dto.Page;
import ru.dfsystems.spring.origin.dto.PageParams;
import ru.dfsystems.spring.origin.dto.student.StudentParams;
import ru.dfsystems.spring.origin.dto.user.UserParams;
import ru.dfsystems.spring.origin.generated.tables.pojos.Users;
import ru.dfsystems.spring.origin.generated.tables.pojos.Student;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserDaoImpl userDao;

    public List<Users> getAllUsers() {
        return userDao.findAll();
    }

    public Page<Users> getUsersByParams(PageParams<UserParams> pageParams) {
        return userDao.getUsersByParams(pageParams);
    }
}