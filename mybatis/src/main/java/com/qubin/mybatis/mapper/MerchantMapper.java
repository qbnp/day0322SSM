package com.qubin.mybatis.mapper;

import com.qubin.mybatis.bean.Merchant;

import java.util.List;

/**
 * 商家mapper接口
 *
 * @author qubin
 * @date 2019-03-26 18:20
 */
public interface MerchantMapper {

    List<Merchant> selectAllMerchant();

    //查询库存小于多少的商家
    List<Merchant> selectMerchantByInventory(int inventory);

}
