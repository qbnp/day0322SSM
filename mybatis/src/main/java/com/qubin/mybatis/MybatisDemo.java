package com.qubin.mybatis;

import com.qubin.mybatis.bean.Student;
import com.qubin.mybatis.mapper.StudentMapper;
import com.qubin.mybatis.mybatis.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * 启动类
 *
 * @author qubin
 * @date 2019-03-22 16:21
 */
public class MybatisDemo {

    public static void main(String[] args) {

        SqlSessionFactory sqlSessionFactory = MybatisUtils.getSqlSessionFactoryInstance();
//        SqlSessionFactory sqlSessionFactory = MybatisUtils1.getSqlSessionFactoryInstance();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
//            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//            List<Map<String, Object>> maps = mapper.selectForNameAndAge();
//            for (Map<String, Object> map : maps) {
//                Set<String> strings = map.keySet();
//                for (String string : strings) {
//                    System.out.println(string+"======"+map.get(string));
//                }
//            }

//            MerchantMapper merchantMapper = sqlSession.getMapper(MerchantMapper.class);
//            List<Merchant> merchants = merchantMapper.selectAllMerchant();
//            for (Merchant merchant : merchants) {
//                System.out.println(merchant);
//            }

//            CommodityMapper commodityMapper = sqlSession.getMapper(CommodityMapper.class);
//            List<Commodity> commodities = commodityMapper.selectAllCommodityByMerchantName("伊利公司");
//            for (Commodity commodity : commodities) {
//                System.out.println(commodity);
//            }
//            List<Commodity> commodities = commodityMapper.selectCommodityByCategory("酸奶");
//            for (Commodity commodity : commodities) {
//                System.out.println(commodity);
//            }

            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
//            Student student = new Student();
//            student.setId(1);
//            student.setName("哈哈哈");
//            student.setStudentClass(new StudentClass());
//            student.getStudentClass().setId(2);
//            int i = mapper.updateStudentById(student);
//            sqlSession.commit();
//            System.out.println("受影响的行数"+i);
            List<Student> students = mapper.selectStudentBySomeInfo(null, "李白", 3);
//            List<Student> students = mapper.selectStudentByOfLikeName("阿");
            for (Student student : students) {
                System.out.println(student);
            }

//            TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
//            List<Teacher> teachers = mapper.selectAllTeacherInFo();
//            for (Teacher teacher : teachers) {
//                System.out.println(teacher);
//            }
        } finally {
            sqlSession.close();
        }
    }

}
