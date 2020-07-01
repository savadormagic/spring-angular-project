package ru.dfsystems.spring.tutorial.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageParams<T> implements Serializable {
    private int start;
    private int page;
    private T params;
}
