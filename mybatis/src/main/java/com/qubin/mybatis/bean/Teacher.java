package com.qubin.mybatis.bean;

import lombok.Data;

import java.util.List;

/**
 * @author qubin
 * @date 2019-03-27 16:50
 */
@Data
public class Teacher {

    private Integer id;
    private String name;
    private List<Student> students;
    private List<StudentClass> studentClasses;

}
