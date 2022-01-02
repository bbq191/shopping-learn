package com.imooc.service.impl;

import com.imooc.enums.Sex;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;
import com.imooc.service.UserService;
import com.imooc.utils.DateUtil;
import com.imooc.utils.MD5Utils;
import java.util.Date;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/** @author afu */
@Service
public class UserServiceImpl implements UserService {

  @Autowired private UsersMapper usersMapper;
  @Autowired private Sid sid;
  private static final String USER_FACE =
      "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";

  @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
  @Override
  public boolean queryUsernameIsExist(String username) {
    Example userExample = new Example(Users.class);
    Example.Criteria userCriteria = userExample.createCriteria();
    userCriteria.andEqualTo("username", username);
    Users result = usersMapper.selectOneByExample(userExample);
    return result != null;
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  @Override
  public Users createUser(UserBO userBO) {
    String userId = sid.nextShort();
    Users user = new Users();
    user.setId(userId);
    user.setUsername(userBO.getUsername());
    try {
      user.setPassword(MD5Utils.getMD5Str(userBO.getPassword()));
    } catch (Exception e) {
      e.printStackTrace();
    }
    user.setNickname(userBO.getUsername());
    user.setFace(USER_FACE);
    user.setBirthday(DateUtil.stringToDate("1900-01-01"));
    user.setSex(Sex.secret.type);
    user.setCreatedTime(new Date());
    user.setUpdatedTime(new Date());
    // 开始注册
    usersMapper.insert(user);
    return user;
  }

  @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
  @Override
  public Users queryUserForLogin(String username, String password) {
    Example userExample = new Example(Users.class);
    Example.Criteria userCriteria = userExample.createCriteria();
    userCriteria.andEqualTo("username", username);
    userCriteria.andEqualTo("password", password);
    return usersMapper.selectOneByExample(userExample);
  }
}
