package com.imooc.mapper;

import com.imooc.pojo.vo.ItemCommentVo;
import com.imooc.pojo.vo.SearchItemsVo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/** @author afu */
public interface ItemsMapperCustom {

  /**
   * 组装商品评价 vo
   *
   * @param map 参数列表
   * @return 评价列表
   */
  List<ItemCommentVo> queryItemComments(@Param("paramsMap") Map<String, Object> map);

  /**
   * 组装查询商品 vo
   *
   * @param map 参数列表
   * @return 商品列表
   */
  List<SearchItemsVo> searchItems(@Param("paramsMap") Map<String, Object> map);

  /**
   * 组装查询三级商品 vo
   *
   * @param map 参数列表
   * @return 商品列表
   */
  List<SearchItemsVo> searchItemsByThirdCat(@Param("paramsMap") Map<String, Object> map);
}
