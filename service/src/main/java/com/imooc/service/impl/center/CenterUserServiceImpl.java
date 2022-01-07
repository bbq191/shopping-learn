package com.imooc.service.impl.center;

import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.service.center.CenterUserService;
import org.springframework.beans.factory.annotation.Autowired;

/** @author afu */
public class CenterUserServiceImpl implements CenterUserService {
  @Autowired private UsersMapper usersMapper;

  @Override
  public Users queryUserInfo(String userId) {
    Users user = usersMapper.selectByPrimaryKey(userId);
    user.setPassword(null);
    return user;
  }
}
