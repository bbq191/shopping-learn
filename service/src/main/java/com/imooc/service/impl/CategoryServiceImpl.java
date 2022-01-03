package com.imooc.service.impl;

import com.imooc.mapper.CategoryMapper;
import com.imooc.pojo.Category;
import com.imooc.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/** @author afu */
@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired private CategoryMapper categoryMapper;

  @Override
  public List<Category> queryAllRootLevelCat() {
    Example example = new Example(Category.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("type", 1);

    return categoryMapper.selectByExample(example);
  }
}
