package ru.dfsystems.spring.tutorial.tools;

public class Checker {

    public static boolean checkEmpty(String value){
        return value != null && !value.isEmpty();
    }
}
