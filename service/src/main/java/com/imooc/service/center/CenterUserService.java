package com.imooc.service.center;

import com.imooc.pojo.Users;

/** @author afu */
public interface CenterUserService {
  /**
   * 根据用户id查询用户信息
   *
   * @param userId 用户id
   * @return 用户对象
   */
  Users queryUserInfo(String userId);
}
