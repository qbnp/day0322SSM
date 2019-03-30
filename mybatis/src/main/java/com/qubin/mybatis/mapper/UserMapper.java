package com.qubin.mybatis.mapper;

import com.qubin.mybatis.bean.User;

import java.util.List;
import java.util.Map;

/**
 * mapper接口
 *
 * @author qubin
 * @date 2019-03-25 11:48
 */
public interface UserMapper {

    List<User> getUserList();

    /**
     * 通过注解的方式
     * #{}相当于sql中的占位符？
     * @param name
     * @return
     */
    User selectUserByName(String name);

    int insert(User user);

    int delete(String name);

    int updateByName(User user);

    List<Map<String,Object>> selectForNameAndAge();

}
