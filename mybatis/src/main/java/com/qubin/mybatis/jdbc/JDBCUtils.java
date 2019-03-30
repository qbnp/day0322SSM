package com.qubin.mybatis.jdbc;

import com.qubin.mybatis.bean.User;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

/**
 * JDBC工具类
 *
 * @author qubin
 * @date 2019-03-22 10:12
 */
public class JDBCUtils {

    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    static {
        Properties properties=null;
        InputStream inputStream=null;
        try {
            properties=new Properties();
            inputStream= ClassLoader.getSystemResourceAsStream("jdbc.properties");
            properties.load(inputStream);
            driver=properties.getProperty("jdbc.driver");
            url=properties.getProperty("jdbc.url");
            username=properties.getProperty("jdbc.username");
            password=properties.getProperty("jdbc.password");
            //加载驱动
            Class.forName(driver);
        }catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }finally {
            if (null!=inputStream){
                try{
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *  连接
     * @return  Connection
     * @throws SQLException
     */
    private Connection getConnection() throws SQLException {

        return DriverManager.getConnection(url,username,password);

    }

    /***
     * 查询
     * @param sql
     * @param clazz
     * @return Collection
     */
    public Collection select(String sql,Class clazz){
            Collection collection=new ArrayList();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            if (sql == null) {
                return null;
            }
            ResultSet resultSet = statement.executeQuery(sql);
            Field[] declaredFields = clazz.getDeclaredFields();
            while (resultSet.next()){
                Object instance= clazz.newInstance();
                if (declaredFields != null) {
                    for (Field field : declaredFields) {
                        String name = field.getName();
                        Object value = resultSet.getObject(name);
                        field.setAccessible(true);
                        field.set(instance,value);
                    }
                }
                collection.add(instance);
            }
            close(connection,resultSet,statement);
        } catch (SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return collection;
    }

    /**
     * 添加
     */
    public Boolean insert(User user){
        if (user==null){
            return null;
        }
        Class<?> aClass = user.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        int i = 0;
        try {
            Connection connection=getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user (name, age) values(?,?)");
            if (null!=declaredFields){
                for (Field field : declaredFields) {
                    field.setAccessible(true);
                    if (null!=field.getName()){
                        if ("name".equals(field.getName())){
                            Object o = field.get(user);
                            preparedStatement.setObject(1,o);
                        }
                        if ("age".equals(field.getName())){
                            Object o = field.get(user);
                            preparedStatement.setObject(2,o);
                        }
                    }
                }
            }
            i=preparedStatement.executeUpdate();
            close(connection,null,preparedStatement);
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return i>0;
    }

    /**
     * 单个删除
     * @param name
     * @return
     */
    public Boolean deleteByName(String name){
        if (null==name){
            return null;
        }
        int i=0;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from user where name=?");
            preparedStatement.setObject(1,name);
            i=preparedStatement.executeUpdate();
            close(connection,null,preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i>0;
    }

    public Boolean updateByname(User user){
        if (null==user){
            return null;
        }
        int i=0;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update user set age=? where name=?");
            Class<? extends User> aClass = user.getClass();
            Field[] declaredFields = aClass.getDeclaredFields();
            if (null!=declaredFields){
                for (Field field : declaredFields) {
                    field.setAccessible(true);
                    if (null!=field.getName()){
                        if ("name".equals(field.getName())){
                            preparedStatement.setObject(2,field.get(user));
                        }
                        if ("age".equals(field.getName())){
                            preparedStatement.setObject(1,field.get(user));
                        }
                    }
                }
            }
            i=preparedStatement.executeUpdate();
            close(connection,null,preparedStatement);
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return i>0;
    }

    /**
     * 批量删除
     * @param collection
     * @return
     */
    public Boolean deleteBatchByName(Collection<String> collection){
        if (null==collection){
            return null;
        }
        int i=0;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from user where name=?");
            for (String name : collection) {
                preparedStatement.setObject(1,name);
                preparedStatement.addBatch();
            }
            int[] ints = preparedStatement.executeBatch();
            for (int anInt : ints) {
                i+=anInt;
            }
            close(connection,null,preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i>0;
    }

    public Boolean insertBatch(Collection<User> collection){
        if (null==collection){
            return null;
        }
        int i=0;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user (name, age) values (?,?)");
            Class<User> userClass = User.class;
            Field[] declaredFields = userClass.getDeclaredFields();
                if (null!=declaredFields){
                    for (User user : collection) {
                        for (Field field : declaredFields) {
                            field.setAccessible(true);
                            if ("name".equals(field.getName())){
                                preparedStatement.setObject(1,field.get(user));
                            }
                            if ("age".equals(field.getName())){
                                preparedStatement.setObject(2,field.get(user));
                            }
                        }
                        preparedStatement.addBatch();
                    }
                }
            int[] ints = preparedStatement.executeBatch();
            for (int anInt : ints) {
                i+=anInt;
            }
            close(connection,null,preparedStatement);
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return i>0;
    }

    /**
     * 关闭资源
     */
    private void close(Connection connection,ResultSet resultSet,Statement statement){
        if (null != resultSet){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (null!=statement){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (null!=connection){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
