package com.imooc.mapper;

import com.imooc.pojo.vo.CategoryVo;
import java.util.List;

/** @author afu */
public interface CategoryMapperCustom {

  /**
   * 自定义商品列表 vo
   *
   * @param rootCatId 根id
   * @return 商品列表
   */
  List<CategoryVo> getSubCatList(Integer rootCatId);
}
