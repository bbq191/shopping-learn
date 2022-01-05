package com.imooc.controller;

import com.imooc.pojo.UserAddress;
import com.imooc.service.AddressService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author afu
 */
@Api(
    value = "地址相关",
    tags = {"地址相关的api接口"})
@RequestMapping("address")
@RestController
public class AddressController {
  @Autowired private AddressService addressService;

  @ApiOperation(value = "根据用户id查询收货地址列表", notes = "根据用户id查询收货地址列表", httpMethod = "POST")
  @PostMapping("/list")
  public IMOOCJSONResult list(@RequestParam String userId) {
    if (StringUtils.isBlank(userId)) {
      return IMOOCJSONResult.errorMsg("");
    }
    List<UserAddress> list = addressService.queryAll(userId);
    return IMOOCJSONResult.ok(list);
  }
}
