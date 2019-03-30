package com.qubin.mybatis.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * mybatis工具类
 * 通过双重验证，得到单例
 * @author qubin
 * @date 2019-03-25 10:32
 */
public class MybatisUtils1 {

    private MybatisUtils1(){};

    private static class SqlSessionFactoryInstance{
        private static InputStream inputStream;

        static {
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private final static SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);

    }

    public static SqlSessionFactory getSqlSessionFactoryInstance(){
        return SqlSessionFactoryInstance.sqlSessionFactory;
    }

}
