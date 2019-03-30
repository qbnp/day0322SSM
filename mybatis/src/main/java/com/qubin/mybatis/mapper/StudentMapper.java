package com.qubin.mybatis.mapper;

import com.qubin.mybatis.bean.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学生mapper接口
 *
 * @author qubin
 * @date 2019-03-27 18:00
 */
public interface StudentMapper {

    List<Student> selectAllStuInFo();

    List<Student> selectStudentByOfLikeName(String name);

    int updateStudentById(Student student);

    List<Student> selectStudentBySomeInfo(@Param("id") Integer id,@Param("name") String name,@Param("class_id") Integer class_id);

}
