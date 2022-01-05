package com.imooc.controller;

import com.imooc.pojo.UserAddress;
import com.imooc.pojo.bo.AddressBo;
import com.imooc.service.AddressService;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.MobileEmailUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** @author afu */
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

  @ApiOperation(value = "用户新增地址", notes = "用户新增地址", httpMethod = "POST")
  @PostMapping("/add")
  public IMOOCJSONResult add(@RequestBody AddressBo addressBo) {
    IMOOCJSONResult checkRes = checkAddress(addressBo);
    if (checkRes.getStatus() != 200) {
      return checkRes;
    }
    addressService.addNewUserAddress(addressBo);
    return IMOOCJSONResult.ok();
  }

  /**
   * 验证用户地址数据
   *
   * @param addressBo 前端传入地址信息
   * @return 成功
   */
  private IMOOCJSONResult checkAddress(AddressBo addressBo) {
    String receiver = addressBo.getReceiver();
    if (StringUtils.isBlank(receiver)) {
      return IMOOCJSONResult.errorMsg("收货人不能为空");
    }
    if (receiver.length() > 12) {
      return IMOOCJSONResult.errorMsg("收货人姓名不能太长");
    }
    String mobile = addressBo.getMobile();
    if (StringUtils.isBlank(mobile)) {
      return IMOOCJSONResult.errorMsg("收货人手机号不能为空");
    }
    if (mobile.length() != 11) {
      return IMOOCJSONResult.errorMsg("收货人手机号长度不正确");
    }
    boolean isMobileOk = MobileEmailUtils.checkMobileIsOk(mobile);
    if (!isMobileOk) {
      return IMOOCJSONResult.errorMsg("收货人手机号格式不正确");
    }
    String province = addressBo.getProvince();
    String city = addressBo.getCity();
    String district = addressBo.getDistrict();
    String detail = addressBo.getDetail();
    if (StringUtils.isBlank(province)
        || StringUtils.isBlank(city)
        || StringUtils.isBlank(district)
        || StringUtils.isBlank(detail)) {
      return IMOOCJSONResult.errorMsg("收货地址信息不能为空");
    }
    return IMOOCJSONResult.ok();
  }
}
