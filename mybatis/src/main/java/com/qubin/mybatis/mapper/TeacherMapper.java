package com.qubin.mybatis.mapper;

import com.qubin.mybatis.bean.Teacher;

import java.util.List;

/**
 * 老师mapper
 *
 * @author qubin
 * @date 2019-03-27 21:11
 */
public interface TeacherMapper {

    List<Teacher> selectAllTeacherInFo();

}
