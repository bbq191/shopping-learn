package com.imooc.service.impl;

import com.imooc.mapper.UserAddressMapper;
import com.imooc.pojo.UserAddress;
import com.imooc.pojo.bo.AddressBo;
import com.imooc.service.AddressService;
import java.util.Date;
import java.util.List;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/** @author afu */
@Service
public class AddressServiceImpl implements AddressService {

  @Autowired private UserAddressMapper userAddressMapper;
  @Autowired private Sid sid;

  @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
  @Override
  public List<UserAddress> queryAll(String userId) {
    UserAddress ua = new UserAddress();
    ua.setUserId(userId);

    return userAddressMapper.select(ua);
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  @Override
  public void addNewUserAddress(AddressBo addressBo) {
    // 1. 判断当前用户是否存在地址，如果没有，则新增为‘默认地址’
    int isDefault = 0;
    List<UserAddress> addressList = this.queryAll(addressBo.getUserId());
    if (addressList == null || addressList.isEmpty()) {
      isDefault = 1;
    }
    String addressId = sid.nextShort();
    // 2. 保存地址到数据库
    UserAddress newAddress = new UserAddress();
    BeanUtils.copyProperties(addressBo, newAddress);
    newAddress.setId(addressId);
    newAddress.setIsDefault(isDefault);
    newAddress.setCreatedTime(new Date());
    newAddress.setUpdatedTime(new Date());
    userAddressMapper.insert(newAddress);
  }
}
