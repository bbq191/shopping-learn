package com.imooc.controller;

import com.imooc.pojo.bo.ShopCartBo;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** @author afu */
@Api(
    value = "购物车接口",
    tags = {"用于购物车的相关接口"})
@RestController
@RequestMapping("shopcart")
public class ShopcartController {
  @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车", httpMethod = "POST")
  @PostMapping("/add")
  public IMOOCJSONResult add(
      @ApiParam(name = "userId", value = "用户 id", required = true) @RequestParam String userId,
      @ApiParam(name = "shopCartBo", value = "购物车 BO", required = true) @RequestBody
          ShopCartBo shopCartBo,
      HttpServletRequest request,
      HttpServletResponse response) {
    if (StringUtils.isBlank(userId)) {
      return IMOOCJSONResult.errorMsg("用户 id 为空");
    }
    System.out.println(shopCartBo);
    // todo 前端用户在登陆到情况下添加商品到购物车会同步添加到 redis
    return IMOOCJSONResult.ok();
  }
}
