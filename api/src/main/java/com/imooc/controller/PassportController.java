package com.imooc.controller;

import com.imooc.pojo.bo.UserBO;
import com.imooc.service.UserService;
import com.imooc.utils.IMOOCJSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** @author afu */
@RestController
@RequestMapping("passport")
public class PassportController {
  @Autowired private UserService userService;

  @GetMapping("/usernameIsExist")
  public IMOOCJSONResult usernameIsExist(@RequestParam String username) {
    // 判断传入参数是否为空
    if (StringUtils.isBlank(username)) {
      return IMOOCJSONResult.errorMsg("用户名不能为空");
    }
    // 查找用户是否存在
    boolean isExist = userService.queryUsernameIsExist(username);
    if (isExist) {
      return IMOOCJSONResult.errorMsg("用户名已存在");
    }
    // 不重复，返回成功
    return IMOOCJSONResult.ok();
  }

  @PostMapping("/regist")
  public IMOOCJSONResult regist(@RequestBody UserBO userBO) {
    String username = userBO.getUsername();
    String password = userBO.getPassword();
    String confirmPwd = userBO.getConfirmPassword();
    if (StringUtils.isBlank(username)
        || StringUtils.isBlank(password)
        || StringUtils.isBlank(confirmPwd)) {
      return IMOOCJSONResult.errorMsg("用户名密码不能为空");
    }
    // 查找用户是否存在
    boolean isExist = userService.queryUsernameIsExist(username);
    if (isExist) {
      return IMOOCJSONResult.errorMsg("用户名已存在");
    }
    if (password.length() < 6) {
      return IMOOCJSONResult.errorMsg("密码不能小于 6 位");
    }
    if (!password.equals(confirmPwd)) {
      return IMOOCJSONResult.errorMsg("两次密码必须一致");
    }
    userService.createUser(userBO);
    // 不重复，返回成功
    return IMOOCJSONResult.ok();
  }
}
