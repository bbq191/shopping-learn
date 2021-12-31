package com.imooc.service;

public interface UserService {

  /**
   * 判断是否存在相同用户名
   *
   * @param username 用户名
   * @return boolean
   */
  public boolean queryUsernameIsExist(String username);
}
