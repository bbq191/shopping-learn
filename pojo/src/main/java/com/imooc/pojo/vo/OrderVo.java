package com.imooc.pojo.vo;

/** @author afu */
public class OrderVo {

  private String orderId;
  private MerchantOrdersVo merchantOrdersVo;

  public String getOrderId() {
    return orderId;
  }

  public MerchantOrdersVo getMerchantOrdersVo() {
    return merchantOrdersVo;
  }

  public void setMerchantOrdersVo(MerchantOrdersVo merchantOrdersVo) {
    this.merchantOrdersVo = merchantOrdersVo;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }
}
