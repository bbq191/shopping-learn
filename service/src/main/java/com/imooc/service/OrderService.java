package com.imooc.service;

import com.imooc.pojo.OrderStatus;
import com.imooc.pojo.bo.SubmitOrderBo;
import com.imooc.pojo.vo.OrderVo;

/** @author afu */
public interface OrderService {

  /**
   * 用于创建订单相关信息
   *
   * @param submitOrderBo 提交数据
   * @return 订单详情 vo
   */
  OrderVo createOrder(SubmitOrderBo submitOrderBo);

  /**
   * 修改订单状态
   *
   * @param orderId 订单 id
   * @param orderStatus 订单状态
   */
  void updateOrderStatus(String orderId, Integer orderStatus);

  /**
   * 查询订单状态
   *
   * @param orderId 订单id
   * @return 订单状态
   */
  OrderStatus queryOrderStatusInfo(String orderId);
  //  /** 关闭超时未支付订单 */
  //  public void closeOrder();
}
