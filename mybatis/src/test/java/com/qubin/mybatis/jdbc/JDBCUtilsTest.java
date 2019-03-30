package com.qubin.mybatis.jdbc;

import com.qubin.mybatis.bean.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class JDBCUtilsTest {

    private JDBCUtils jdbcUtils;

    @Before
    public void init(){
        jdbcUtils=new JDBCUtils();
    }

    @Test
    public void select() {

        Collection collection = jdbcUtils.select("select *from user", User.class);
        for (Object o : collection) {
            System.out.println(o);
        }
    }

    @Test
    public void insert(){
        User user = new User();
        user.setName("老王");
        user.setAge(50);
        Boolean insert = jdbcUtils.insert(user);
        System.out.println(insert+"-----------");
    }

    @Test
    public void deleteByName(){
        Boolean lb = jdbcUtils.deleteByName("hh");
        System.out.println(lb+"----------");
    }

    @Test
    public void updateByname(){
        User user = new User();
        user.setName("老王");
        user.setAge(18);
        Boolean aBoolean = jdbcUtils.updateByname(user);
        System.out.println(aBoolean+"----------");
    }

    @Test
    public void deleteBatchByName(){
        Collection<String> objects = new ArrayList<>();
        objects.add("lb");
        objects.add("zs");
        objects.add("ls");
        objects.add("老王");
        Boolean aBoolean = jdbcUtils.deleteBatchByName(objects);
        System.out.println(aBoolean+"___________");
    }

    @Test
    public void insertBatch(){
        Collection<User> objects = new ArrayList<>();
        User user = null;
        for (int i=0;i<10;i++){
            user=new User();
            user.setName("老王"+i);
            user.setAge(i+10);
            objects.add(user);
        }
        Boolean aBoolean = jdbcUtils.insertBatch(objects);
        System.out.println(aBoolean+"==========");
    }

}