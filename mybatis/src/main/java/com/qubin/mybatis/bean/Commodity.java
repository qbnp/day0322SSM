package com.qubin.mybatis.bean;

import lombok.Data;

/**
 * 商品
 *
 * @author qubin
 * @date 2019-03-26 16:45
 */
@Data
public class Commodity {

    private Integer id;
    private String name;
    private Category category;
    private Merchant merchant;
    private Double price;
    private int inventory;

}
