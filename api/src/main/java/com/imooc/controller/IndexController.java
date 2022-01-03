package com.imooc.controller;

import com.imooc.enums.YesOrNo;
import com.imooc.pojo.Carousel;
import com.imooc.pojo.Category;
import com.imooc.service.CarouselService;
import com.imooc.service.CategoryService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author afu */
@Api(
    value = "首页",
    tags = {"首页展示的相关接口"})
@RestController
public class IndexController {
  @Autowired private CarouselService carouselService;
  @Autowired private CategoryService categoryService;

  @ApiOperation(value = "获取首页轮播图列表", notes = "获取首页轮播图列表", httpMethod = "GET")
  @GetMapping("/carousel")
  public IMOOCJSONResult carousel() {
    List<Carousel> list = carouselService.queryAll(YesOrNo.YES.type);
    return IMOOCJSONResult.ok(list);
  }

  @ApiOperation(value = "获取一级商品分类", notes = "获取一级商品分类", httpMethod = "GET")
  @GetMapping("/category")
  public IMOOCJSONResult category() {
    List<Category> list = categoryService.queryAllRootLevelCat();
    return IMOOCJSONResult.ok(list);
  }
}
