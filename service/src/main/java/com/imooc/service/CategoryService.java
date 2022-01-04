package com.imooc.service;

import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVo;
import java.util.List;

/** @author afu */
public interface CategoryService {

  /**
   * 商品列表一级分类
   *
   * @return 图片列表
   */
  List<Category> queryAllRootLevelCat();

  /**
   * 获取懒加载商品列表子分类
   *
   * @param rootCatId 父级id
   * @return 商品列表 list
   */
  List<CategoryVo> getSubCatList(Integer rootCatId);
}
