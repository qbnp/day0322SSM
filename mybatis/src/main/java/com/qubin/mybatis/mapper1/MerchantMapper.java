package com.qubin.mybatis.mapper1;

import com.qubin.mybatis.bean1.Merchant;
import java.util.List;

public interface MerchantMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Merchant record);

    Merchant selectByPrimaryKey(Long id);

    List<Merchant> selectAll();

    int updateByPrimaryKey(Merchant record);
}