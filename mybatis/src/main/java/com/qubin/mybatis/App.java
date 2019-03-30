package com.qubin.mybatis;


import com.github.pagehelper.PageHelper;
import com.qubin.mybatis.bean1.Goods;
import com.qubin.mybatis.mapper1.GoodsMapper;
import com.qubin.mybatis.mybatis.MybatisUtils1;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.List;

/**
 * 测试类
 *
 * @author qubin
 * @date 2019-03-28 15:35
 */
public class App {

    @Test
    public void mybatis(){

        SqlSessionFactory sqlSessionFactory = MybatisUtils1.getSqlSessionFactoryInstance();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
            PageHelper.startPage(1,8);
            List<Goods> goods = goodsMapper.selectAll();
            for (Goods good : goods) {
                System.out.println(good);
            }
        }finally {
            sqlSession.close();
        }
    }

}
