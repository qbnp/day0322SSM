package com.qubin.mybatis.bean;

import lombok.Data;

/**
 * 类别
 *
 * @author qubin
 * @date 2019-03-26 16:47
 */

@Data
public class Category {

    private Integer id;
    private String name;
    private TopCategory topCategory;

}