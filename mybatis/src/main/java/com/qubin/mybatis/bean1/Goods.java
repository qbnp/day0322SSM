package com.qubin.mybatis.bean1;

import lombok.Data;

@Data
public class Goods {
    private Long id;

    private String name;

    private Category category;

    private Merchant merchant;

    private Integer price;

    private Integer stock;

}