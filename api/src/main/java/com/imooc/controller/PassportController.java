package com.imooc.controller;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;
import com.imooc.service.UserService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    value = "注册登录",
    tags = {"用于注册登录的相关接口"})
@RestController
@RequestMapping("passport")
public class PassportController {
  @Autowired private UserService userService;

  @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在", httpMethod = "GET")
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

  @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
  @PostMapping("/regist")
  public IMOOCJSONResult regist(
      @RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) {
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
    Users userResult = userService.createUser(userBO);
    setNullPropertirs(userResult);
    // 设置 cookie 值
    CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(userResult), true);
    // 不重复，返回成功
    return IMOOCJSONResult.ok();
  }

  @ApiOperation(value = "用户登陆", notes = "用户登陆", httpMethod = "POST")
  @PostMapping("/login")
  public IMOOCJSONResult login(
      @RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) {
    String username = userBO.getUsername();
    String password = userBO.getPassword();
    if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
      return IMOOCJSONResult.errorMsg("用户名密码不能为空");
    }
    Users userResult = null;
    try {
      userResult = userService.queryUserForLogin(username, MD5Utils.getMD5Str(password));
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (userResult == null) {
      return IMOOCJSONResult.errorMsg("用户名密码不正确");
    }
    setNullPropertirs(userResult);
    // 设置 cookie 值
    CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(userResult), true);
    // 不重复，返回成功
    return IMOOCJSONResult.ok(userResult);
  }

  /**
   * 过滤敏感信息
   *
   * @param userResult 用户结果集
   * @return 用户空结果集
   */
  private Users setNullPropertirs(Users userResult) {
    userResult.setPassword(null);
    userResult.setMobile(null);
    userResult.setEmail(null);
    userResult.setBirthday(null);
    userResult.setUpdatedTime(null);
    userResult.setCreatedTime(null);
    return userResult;
  }
}
