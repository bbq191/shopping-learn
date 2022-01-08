package com.imooc.mapper;

import com.imooc.pojo.vo.MyOrdersVo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/** @author afu */
public interface OrdersMapperCustom {

  /**
   * 查询用户订单
   *
   * @param map 订单详情子列表
   * @return 订单列表封装
   */
  List<MyOrdersVo> queryMyOrders(@Param("paramsMap") Map<String, Object> map);

  //    public int getMyOrderStatusCounts(@Param("paramsMap") Map<String, Object> map);

  //    public List<OrderStatus> getMyOrderTrend(@Param("paramsMap") Map<String, Object> map);

}
