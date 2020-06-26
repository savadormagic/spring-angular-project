package ru.dfsystems.spring.tutorial.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dfsystems.spring.tutorial.dao.InstrumentDaoImpl;

@Service
@AllArgsConstructor
public class InstrumentService {
    private InstrumentDaoImpl instrumentDao;
}
