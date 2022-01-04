package com.imooc.controller;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.pojo.vo.CommentLevelCountsVo;
import com.imooc.pojo.vo.ItemInfoVo;
import com.imooc.service.ItemService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** @author afu */
@Api(
    value = "商品接口",
    tags = {"商品信息展示的相关接口"})
@RestController
@RequestMapping("items")
public class ItemsController {
  @Autowired private ItemService itemService;

  @ApiOperation(value = "查询商品详情", notes = "查询商品详情", httpMethod = "GET")
  @GetMapping("/info/{itemId}")
  public IMOOCJSONResult itemInfo(
      @ApiParam(name = "itemId", value = "商品 id", required = true) @PathVariable String itemId) {
    if (StringUtils.isBlank(itemId)) {
      return IMOOCJSONResult.errorMsg("商品 id 不能为空");
    }
    Items item = itemService.queryItemById(itemId);
    List<ItemsImg> imgList = itemService.queryItemImgList(itemId);
    List<ItemsSpec> specList = itemService.queryItemSpecList(itemId);
    ItemsParam param = itemService.queryItemParam(itemId);

    ItemInfoVo itemInfoVo = new ItemInfoVo();
    itemInfoVo.setItem(item);
    itemInfoVo.setItemImgList(imgList);
    itemInfoVo.setItemSpecList(specList);
    itemInfoVo.setItemParams(param);
    return IMOOCJSONResult.ok(itemInfoVo);
  }

  @ApiOperation(value = "查询商品评价数量", notes = "查询商品评价数量", httpMethod = "GET")
  @GetMapping("/commentLevel")
  public IMOOCJSONResult commentLevel(
      @ApiParam(name = "itemId", value = "商品 id", required = true) @RequestParam String itemId) {
    if (StringUtils.isBlank(itemId)) {
      return IMOOCJSONResult.errorMsg("商品 id 不能为空");
    }
    CommentLevelCountsVo countsVo = itemService.queryCommentCounts(itemId);
    return IMOOCJSONResult.ok(countsVo);
  }
}
