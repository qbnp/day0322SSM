package com.qubin.mybatis.bean;

import lombok.Data;

import java.util.List;

/**
 * 学生类
 *
 * @author qubin
 * @date 2019-03-27 16:46
 */
@Data
public class Student {

    private Integer id;
    private String name;
    private StudentClass studentClass;
    private List<Teacher> teachers;

}
