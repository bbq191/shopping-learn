package com.imooc.service;

import com.imooc.pojo.Carousel;
import com.imooc.pojo.UserAddress;
import com.imooc.pojo.bo.AddressBo;
import java.util.List;

/** @author afu */
public interface AddressService {

  /**
   * 根据用户 id 查询用户地址列表
   *
   * @param userId 用户 id
   * @return 地址列表
   */
  List<UserAddress> queryAll(String userId);

  /**
   * 用户新增地址
   *
   * @param addressBo 地址业务参数
   */
  void addNewUserAddress(AddressBo addressBo);
}
