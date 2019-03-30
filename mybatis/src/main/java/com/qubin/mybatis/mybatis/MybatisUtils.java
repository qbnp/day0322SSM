package com.qubin.mybatis.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * mybatis工具类
 * 通过双重验证，得到单例
 * @author qubin
 * @date 2019-03-25 10:32
 */
public class MybatisUtils {
    private static volatile SqlSessionFactory sqlSessionFactory;
    private MybatisUtils(){};
    public static SqlSessionFactory getSqlSessionFactoryInstance(){
        if (null==sqlSessionFactory){
            synchronized (SqlSessionFactory.class){
                if (null==sqlSessionFactory){
                    try {
                        sqlSessionFactory= new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return sqlSessionFactory;
    }

}
