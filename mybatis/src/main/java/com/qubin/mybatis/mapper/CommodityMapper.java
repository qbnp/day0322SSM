package com.qubin.mybatis.mapper;

import com.qubin.mybatis.bean.Commodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品mapper接口
 *
 * @author qubin
 * @date 2019-03-26 18:22
 */
public interface CommodityMapper {
    //通过商家名称获取商品信息
    List<Commodity> selectAllCommodityByMerchantName(String name);

    //查询所有的商品
    List<Commodity> selectAllCommodity();

    //通过类别查询商品
    List<Commodity> selectCommodityByCategory(String name);

    //添加商品
    int addCommodity(Commodity commodity);

    //删除商品
    int deleteCommodityByName(String name);

    //修改商品信息
    int updateCommodityPriceByName(@Param("name") String name,@Param("price") Integer price);

}
