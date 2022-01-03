package com.imooc.service;

import com.imooc.pojo.Category;
import java.util.List;

/** @author afu */
public interface CategoryService {

  /**
   * 商品列表一级分类
   *
   * @return 图片列表
   */
  public List<Category> queryAllRootLevelCat();
}
