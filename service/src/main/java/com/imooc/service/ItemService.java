package com.imooc.service;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import java.util.List;

/** @author afu */
public interface ItemService {

  /**
   * 根据商品 id 查询商品
   *
   * @param itemId 商品 id
   * @return 商品对象
   */
  Items queryItemById(String itemId);

  /**
   * 根据商品 id 查询商品图片列表
   *
   * @param itemId 商品 id
   * @return 商品对象
   */
  List<ItemsImg> queryItemImgList(String itemId);
  /**
   * 根据商品 id 查询商品规格列表
   *
   * @param itemId 商品 id
   * @return 商品对象
   */
  List<ItemsSpec> queryItemSpecList(String itemId);
  /**
   * 根据商品 id 查询商品参数
   *
   * @param itemId 商品 id
   * @return 商品参数对象
   */
  ItemsParam queryItemParam(String itemId);
}
